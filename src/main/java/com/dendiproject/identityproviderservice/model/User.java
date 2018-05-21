package com.dendiproject.identityproviderservice.model;

import java.util.Date;
import javax.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author ArtemShevelyukhin
 */
@Entity
@Table(name = "userdetails")
public class User implements UserDetails {

  @Column(name = "id")
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Id
  private String id;

  @Column(name = "email")
  private String email;

  @Column(name = "firstname")
  private String name;

  @Column(name = "lastname")
  private String lastname;

  @Column(name = "password")
  private String password;

  @Column(name = "displayname")
  private String displayname;

  @Column(name = "age",  columnDefinition = "DATE")
  private Date age;
  
  @Column(name = "info")
  private String info;
  
  @Column(name = "picture_id")
  private String picture_id;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "user_role", joinColumns =
//    @JoinColumn(name = "user_id"), inverseJoinColumns =
//    @JoinColumn(name = "role_id"))
//    private Set<Role> roles;
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

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public Date getAge() {
    return age;
  }

  public void setAge(Date age) {
    this.age = age;
  }

  public String getDisplayname() {
    return displayname;
  }

  public void setDisplayname(String displayname) {
    this.displayname = displayname;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public String getPicture_id() {
    return picture_id;
  }

  public void setPicture_id(String picture_id) {
    this.picture_id = picture_id;
  }
  
  
  
  
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
