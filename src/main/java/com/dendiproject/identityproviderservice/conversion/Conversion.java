/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dendiproject.identityproviderservice.conversion;

import com.dendiproject.identityproviderservice.model.User;
import com.dendiproject.identityproviderservice.model.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 *
 * @author ArtemShevelyukhin
 */
@Configuration
public class Conversion {
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
}
    
    public UserDto convertToDto(UserDetails user){
        
        return modelMapper().map(user, UserDto.class); 
    }
    
    public User convertToEntity(UserDto userDto){
        
        return modelMapper().map(userDto, User.class);
    }
}
