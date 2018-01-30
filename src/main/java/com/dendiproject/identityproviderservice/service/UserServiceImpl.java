package com.dendiproject.identityproviderservice.service;

import com.dendiproject.identityproviderservice.model.User;
import com.dendiproject.identityproviderservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
   
    

    @Override
    public void save(User user) {
       
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findOne(username);
    }
}
