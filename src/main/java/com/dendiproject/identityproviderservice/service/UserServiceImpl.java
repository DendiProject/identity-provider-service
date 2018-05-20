package com.dendiproject.identityproviderservice.service;

import com.dendiproject.identityproviderservice.conversion.Conversion;
import com.dendiproject.identityproviderservice.model.User;
import com.dendiproject.identityproviderservice.model.UserInfoDto;
import com.dendiproject.identityproviderservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;
    @Autowired
  private Conversion convertor;

  @Override
  @Transactional
  public void save(User user) {
    userRepository.save(user);
  }

  @Override
  public User findByUsername(String name) {
    return userRepository.getOne(name);
  }
  
  @Override
    public List<UserInfoDto> getAll() {
        List<User> users=userRepository.findAll();
        return users.stream()
               .map(user->convertor.convertUserToUserInfoDtoDto(user))
               .collect(Collectors.toList());
    }

    @Override
    public UserInfoDto getById(String userId) {
        User user=userRepository.findById(userId);
        if (user!=null)
        {
            return convertor.convertUserToUserInfoDtoDto(user);
        }
        return null;
    }

//    @Override
//    public void updateUserDetails(User user) {
//        User u = userRepository.getOne(user.getId());
//        
//    }
}
