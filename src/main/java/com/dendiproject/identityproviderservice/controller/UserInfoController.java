/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dendiproject.identityproviderservice.controller;

import com.dendiproject.identityproviderservice.model.UserInfoDto;
import com.dendiproject.identityproviderservice.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author eliza
 */
@RestController
public class UserInfoController {
    @Autowired
  private UserService userService;
    
    @RequestMapping(value = "/users/getinfo/{id}", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public ResponseEntity<?> getCatalogByName(@PathVariable ("id") String id)  {
        UserInfoDto user=userService.getById(id);
    
        if (user==null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
         else   return new ResponseEntity<>(user, HttpStatus.OK);            
    }  
    
    @RequestMapping(value = "/users/getall", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public ResponseEntity<?> getCatalogByName()  {
        List<UserInfoDto> users=userService.getAll();
    
        if (users.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
         else   return new ResponseEntity<>(users, HttpStatus.OK);            
    }  
    
}
