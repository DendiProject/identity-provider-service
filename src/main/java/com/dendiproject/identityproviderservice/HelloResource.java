/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dendiproject.identityproviderservice;

/**
 *
 * @author ArtemShevelyukhin
 */


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/rest/hello")
public class HelloResource {
 
    @GetMapping("/principal")
    public Principal user(Principal principal) {
        return principal;
    }
    @GetMapping
    public String hello() {
        return "Hello World";
    }

}