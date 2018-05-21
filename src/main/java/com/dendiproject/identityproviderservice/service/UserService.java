package com.dendiproject.identityproviderservice.service;

import com.dendiproject.identityproviderservice.model.User;
import com.dendiproject.identityproviderservice.model.UserInfoDto;
import java.util.List;

public interface UserService {

  void save(User user);

  User findByUsername(String name);
  List<UserInfoDto> getAll();
  UserInfoDto getById(String userId);
  // void updateUserDetails(User user);
}
