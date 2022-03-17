package com.example.springsecurityexample1.controller;

import com.example.springsecurityexample1.Dto.ApiResponse;
import com.example.springsecurityexample1.Dto.LoginDto;
import com.example.springsecurityexample1.Dto.UserDto;
import com.example.springsecurityexample1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping("/register")
  public HttpEntity<?> addUser(@RequestBody UserDto userDto) {
    ApiResponse apiResponse = userService.addUser(userDto);
    return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
  }

  @PostMapping("/login")
  public HttpEntity<?> loginToSystem(@RequestBody LoginDto loginDto) {
    ApiResponse apiResponse = userService.loginToSystem(loginDto);
    return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
  }
}
