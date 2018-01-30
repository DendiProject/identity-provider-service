/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dendiproject.identityproviderservice.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import javax.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
/**
 *
 * @author ArtemShevelyukhin
 */
@Entity
public class User implements UserDetails{
    
  
    
    
    @Column(name = "user_id")
    private int user_id;
    
    
    @Column(name = "email")
    private String email;
    
    
    @Column(name = "first_name")
    @Id
    private String name;
    
    @Column(name = "passwd")
    private String password;

    
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "user_role", joinColumns =
//    @JoinColumn(name = "user_id"), inverseJoinColumns =
//    @JoinColumn(name = "role_id"))
//    private Set<Role> roles;
    
    public int getUser_id() {
        return user_id;
    }

    public String getEmail() {
        return email;
    }
    
//    //ФИГНЯ
//        
//	public User() {
//	}
//
//    public User(int user_id, String email, String name, String password) {
//        this.user_id = user_id;
//        this.email = email;
//        this.name = name;
//        this.password = password;
//    }

    //ФИГНЯ
//    public void setUser_id(int user_id) {
//        this.user_id = user_id;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public void setPassword(String passwd) {
        this.password = passwd;
    }
    ///////////////////////
    @Override
    public List<GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
    
}
