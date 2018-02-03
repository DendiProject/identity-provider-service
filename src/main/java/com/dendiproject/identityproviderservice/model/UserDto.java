/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dendiproject.identityproviderservice.model;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 *
 * @author ArtemShevelyukhin
 */
public class UserDto
{
    private String email;
    private String name;
    private String displayname;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }
    
} 