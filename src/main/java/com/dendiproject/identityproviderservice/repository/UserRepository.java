/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dendiproject.identityproviderservice.repository;



import com.dendiproject.identityproviderservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *s
 * @author ArtemShevelyukhin
 */
public interface UserRepository extends JpaRepository <User, String> {

}
