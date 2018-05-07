/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dendiproject.identityproviderservice.controller;

import com.dendiproject.identityproviderservice.JWTHandler;
import com.dendiproject.identityproviderservice.conversion.Conversion;
import com.dendiproject.identityproviderservice.model.User;
import com.dendiproject.identityproviderservice.model.UserDto;
import com.dendiproject.identityproviderservice.repository.UserRepository;
import com.dendiproject.identityproviderservice.service.UserService;
import com.dendiproject.identityproviderservice.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author ArtemShevelyukhin
 */
@RestController
@RequestMapping(value = "/idpsecure")
public class ResourceController {

//    @Autowired
//    private UserDetailsService userDetailsService;
  @Autowired
  private TokenStore tokenStore;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private UserService userService;
  @Autowired
  private Conversion conversion;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private JWTHandler handler;
  @Autowired
  private RestTemplate restTemplate;

//  @Autowired
//  private UserValidator userValidator;
  @RequestMapping(value = "/checkUiToken", method = RequestMethod.GET)
  public String checkUiToken() {
    System.out.println(tokenStore.findTokensByClientId("ui"));
    return "private";
  }

  @RequestMapping(value = "/checkCMToken", method = RequestMethod.GET)
  public String checkCMToken() {
    System.out.println(tokenStore.findTokensByClientId("contentmanager"));
    return "private";
  }

  @RequestMapping(value = "/private/someinfo", method = RequestMethod.GET)
  public String test() {
    System.out.println("private");
    return "private";
  }

  @RequestMapping(value = "/unprivate/someinfo", method = RequestMethod.GET)
  public String test2() {
    System.out.println("no_private");
    return "no_private";
  }
  /////

  @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
  public ResponseEntity<UserDto> getUser(
          @PathVariable(value = "id") String id) throws UsernameNotFoundException {

    User user = userService.findByUsername(id);//userDetailsService.loadUserByUsername(name);  
    if (user == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      UserDto UserDto = conversion.convertToDto(user);
      return new ResponseEntity<>(UserDto, HttpStatus.OK);
    }
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public ResponseEntity<Void> registration(@RequestBody UserDto userDto) {
    User user = conversion.convertToEntity(userDto);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    User check = userRepository.findByEmail(user.getEmail());// <--DELETE
    if (check == null) {
      userService.save(user);
      System.out.println("регистрация завершена");
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
  }

  @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Void> update(
          @RequestBody UserDto userDto,
          @PathVariable(value = "id") String id
  ) {
    try {
      User user = conversion.convertToEntity(userDto);
      user.setId(id);
      User old = userRepository.getOne(id);
      user.setPassword(old.getPassword());
      userService.save(user);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (org.springframework.orm.jpa.JpaObjectRetrievalFailureException ex) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(value = "/authorization", method = RequestMethod.POST)
  public ResponseEntity<String> authorization(@RequestBody UserDto userDto,
          HttpServletResponse response
  ) {

    User user = conversion.convertToEntity(userDto);//user пришел с почтой и паролем
    User userDB = userRepository.findByEmail(user.getEmail());
    if (userDB != null) {
      String subject;
      if (userDB.getEmail().equals("guest")) {
        subject = "GUEST";
      } else {
        subject = "USER";
      }
      //Key key = MacProvider.generateKey();
      String jwtToken = handler
              .createJWT("IDP", subject, userDB.getId(), "test");

      Cookie userInfo = new Cookie("userInfo", jwtToken);
      response.addCookie(userInfo);

      return new ResponseEntity<>(HttpStatus.OK);
    } else {

      return new ResponseEntity<>("ERROR", HttpStatus.CONFLICT);
    }
  }

//  @RequestMapping(value = "/verifyToken", method = RequestMethod.POST)
//  public ResponseEntity<String> verifyToken(
//          @RequestHeader ("secureToken") String secureToken,
//          @RequestHeader ("service") String service,
//          HttpServletResponse response){
//    String dbToken = tokenStore.findTokensByClientId(service).toString();
//    dbToken = dbToken.replace("[","");
//    dbToken = dbToken.replace("]","");
//    if(secureToken != null && dbToken != null && secureToken.equals(dbToken)){
//      System.out.println("**токен проверен**");
//      return new ResponseEntity<>(HttpStatus.OK);
//    }
//    else{
//      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//    }
//  }
  @RequestMapping(value = "/verifyToken", method = RequestMethod.POST)
  public ResponseEntity<String> verifyToken(@RequestHeader("secureToken") String secureToken, HttpServletResponse response
  ) {
    ResponseEntity result = null;

//    String url = "http://localhost:8181/idpsecure/checkToken" + "?access_token=" + secureToken;
//    ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.GET, result, String.class);
    try {
      URL url = new URL(
              "http://localhost:8181/idpsecure/checkToken" + "?access_token=" + secureToken);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
      con.setDoOutput(true);
      System.out.println("responseCode =  " + con.getResponseCode());
      int responseCode = con.getResponseCode();

      if (responseCode == 200) {
        result = new ResponseEntity<>(HttpStatus.OK);
      } else {
        result = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
      }

    } catch (MalformedURLException ex) {
      Logger.getLogger(ResourceController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(ResourceController.class.getName()).log(Level.SEVERE, null, ex);

    }
    return result;
  }

  @RequestMapping(value = "/checkToken", method = RequestMethod.GET)
  public ResponseEntity<Void> checkToken() {
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
