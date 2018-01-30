/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dendiproject.identityproviderservice.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author ArtemShevelyukhin
 */
@Entity
@Table(name = "role_name")
public class Role {
    
    @Id
    @Column(name = "role_id")
    private int id;
    
    @Column(name = "role_name")
    private String role;

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
}
