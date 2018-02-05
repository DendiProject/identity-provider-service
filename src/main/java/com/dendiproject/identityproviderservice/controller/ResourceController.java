/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dendiproject.identityproviderservice.controller;

import com.dendiproject.identityproviderservice.conversion.Conversion;
import com.dendiproject.identityproviderservice.model.User;
import com.dendiproject.identityproviderservice.model.UserDto;
import com.dendiproject.identityproviderservice.service.MyUserDetailsService;
import com.dendiproject.identityproviderservice.service.UserService;
import com.dendiproject.identityproviderservice.validator.UserValidator;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

/**
 *
 * @author ArtemShevelyukhin
 */
@RestController

public class ResourceController {
    
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private UserService userService;
    @Autowired 
    private Conversion conversion;
    //    @Autowired
    //    private UserValidator userValidator;
   
   
    @RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getUser(
            @PathVariable(value = "name")String name) throws UsernameNotFoundException{
        
        UserDetails userdetails = userDetailsService.loadUserByUsername(name);  
        if (userdetails == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            UserDto UserDto = conversion.convertToDto(userdetails);
            return new ResponseEntity<>(UserDto, HttpStatus.OK);
        }        
    }
    
    
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Void> registration(@RequestBody UserDto userDto){//, BindingResult bindingResult) {
//        userValidator.validate(user, bindingResult);
//        
//        if (bindingResult.hasErrors()) {
//            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
//        }
        User user = conversion.convertToEntity(userDto);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
   
}
