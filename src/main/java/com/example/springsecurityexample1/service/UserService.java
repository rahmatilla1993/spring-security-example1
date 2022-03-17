package com.example.springsecurityexample1.service;

import com.example.springsecurityexample1.Dto.ApiResponse;
import com.example.springsecurityexample1.Dto.LoginDto;
import com.example.springsecurityexample1.Dto.UserDto;
import com.example.springsecurityexample1.entity.Role;
import com.example.springsecurityexample1.entity.User;
import com.example.springsecurityexample1.entity.enums.RoleName;
import com.example.springsecurityexample1.entity.enums.Status;
import com.example.springsecurityexample1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  AuthenticationManager authenticationManager;

  public ApiResponse addUser(UserDto userDto) {
    if (userRepository.existsByEmail(userDto.getEmail())) {
      return new ApiResponse("This email already exists", false);
    }
    User user = new User();
    user.setEmail(userDto.getEmail());
    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    user.setFirstName(userDto.getFirstName());
    user.setLastName(userDto.getLastName());
    user.setRole(new Role(RoleName.USER));
    user.setStatus(Status.ACTIVE);
    userRepository.save(user);
    return new ApiResponse("User added", true, user);
  }

  public ApiResponse loginToSystem(LoginDto loginDto) {
    try {
      Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        loginDto.getEmail(), loginDto.getPassword()));

      User user = (User) authenticate.getPrincipal();
      return new ApiResponse("User entered system", true, user);
    } catch (Exception e) {
      return new ApiResponse("User not found!", false);
    }
  }
}
