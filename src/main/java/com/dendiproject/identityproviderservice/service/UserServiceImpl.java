package com.dendiproject.identityproviderservice.service;

import com.dendiproject.identityproviderservice.model.User;
import com.dendiproject.identityproviderservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
   
    @Override
    @Transactional
    public void save(User user) {   
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String name) {
        return userRepository.getOne(name);
    }

//    @Override
//    public void updateUserDetails(User user) {
//        User u = userRepository.getOne(user.getId());
//        
//    }

}

