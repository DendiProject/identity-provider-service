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

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.impl.crypto.MacProvider;
import io.jsonwebtoken.*;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CookieValue;

/**
 *
 * @author ArtemShevelyukhin
 */
@RestController

public class ResourceController {
    
//    @Autowired
//    private UserDetailsService userDetailsService;
   
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
    
    //    @Autowired
    //    private UserValidator userValidator;
    
    @RequestMapping(value = "/private", method = RequestMethod.GET)
    public String test(){
        System.out.println("TEST");
        return "TEST_RETURN";
    }
   
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getUser(
            @PathVariable(value = "id")String id) throws UsernameNotFoundException{
        
        User user = userService.findByUsername(id);//userDetailsService.loadUserByUsername(name);  
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            UserDto UserDto = conversion.convertToDto(user);
            return new ResponseEntity<>(UserDto, HttpStatus.OK);
        }        
    }
    
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Void> registration(@RequestBody UserDto userDto){
        
        
        User user = conversion.convertToEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.err.println(user);
        userService.save(user); 
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
      
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(
            @RequestBody UserDto userDto, @PathVariable(value = "id")String id){
                try{
                User user = conversion.convertToEntity(userDto);
                user.setId(id);
                User old = userRepository.getOne(id); 
                user.setPassword(old.getPassword());
                userService.save(user);
                return new ResponseEntity<>(HttpStatus.OK);
            }       
                catch(org.springframework.orm.jpa.JpaObjectRetrievalFailureException ex){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
   
    
    @RequestMapping(value = "/authorization", method = RequestMethod.POST)
    public ResponseEntity<String> authorization(@RequestBody UserDto userDto
            ,HttpServletResponse response){
        
        User user = conversion.convertToEntity(userDto);//user пришел с почтой и паролем
        User userDB = userRepository.findByEmail(user.getEmail());//userDetailsService.loadUserByUsername(name);  
        if (userDB != null){
            String subject;
            if (userDB.getEmail().equals("guest")){
                subject = "GUEST";
            }
            else{
                subject = "USER";
            }
            
            //Key key = MacProvider.generateKey();
            
            String jwtToken = handler
                                .createJWT("IDP", subject, userDB.getId(), userDB.getId(), "test");
          
//            String jwtToken = Jwts.builder()
//                    .setIssuer("IDP")
//                    .setSubject(subject)
//                    .setIssuedAt(Date.from(Instant.now()))
//                    .setExpiration(Date.from(Instant.now().plusSeconds(300)))
//                    .claim("id",userDB.getId())
//                    .claim("email",userDB.getId())
//                    .signWith(SignatureAlgorithm.HS512, "testkey")
//                    .compact();
            
            
            Cookie userInfo = new Cookie("userInfo", jwtToken);
            response.addCookie(userInfo);
            
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
          
            return new ResponseEntity<>("ERROR", HttpStatus.NOT_FOUND);
        }    
    }
    
  
}
