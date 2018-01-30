/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dendiproject.identityproviderservice.controller;

import com.dendiproject.identityproviderservice.model.User;
import com.dendiproject.identityproviderservice.service.MyUserDetailsService;
import com.dendiproject.identityproviderservice.service.UserService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ArtemShevelyukhin
 */
@RestController
@RequestMapping("/rest")
public class ResourceController {
    
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private UserService userService;
   
    
    @RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
    public ResponseEntity<UserDetails> getUser(@PathVariable(value = "name")String name){
        UserDetails userdetails = userDetailsService.loadUserByUsername(name);
        if (userdetails == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } 
        else {
            return new ResponseEntity<>(userdetails, HttpStatus.OK);
        }
    }
    
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Void> registration(User user) {
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}