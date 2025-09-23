package com.example.stockmarketspringapi.controller;

import com.example.stockmarketspringapi.model.dto.userDtos.LoginUserDto;
import com.example.stockmarketspringapi.model.dto.UserDto;
import com.example.stockmarketspringapi.model.dto.resp.LoginResponse;
import com.example.stockmarketspringapi.model.entity.User;
import com.example.stockmarketspringapi.service.JwtService;
import com.example.stockmarketspringapi.service.interfaces.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
  private final JwtService jwtService;
  private final AuthService authService;

  @Autowired
  public UserController(AuthService authService, JwtService jwtService) {
    this.jwtService = jwtService;
    this.authService = authService;
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody UserDto user) {
    return ResponseEntity.status(201).body(authService.register(user));
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginUserDto loginUserDto) {
    User authenticatedUser = authService.login(loginUserDto);

    String jwtToken = jwtService.generateToken(authenticatedUser);
    LoginResponse loginResponse = new LoginResponse();
    loginResponse.setToken(jwtToken);
    loginResponse.setExpiresIn(jwtService.getExpirationTime());

    return ResponseEntity.ok(loginResponse);
  }
}
