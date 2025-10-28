package com.example.stockmarketspringapi.controller;

import com.example.stockmarketspringapi.model.dto.userDtos.LoginUserDto;
import com.example.stockmarketspringapi.model.dto.UserDto;
import com.example.stockmarketspringapi.model.dto.resp.LoginResponse;
import com.example.stockmarketspringapi.model.dto.userDtos.RegisterUserDto;
import com.example.stockmarketspringapi.model.entity.User;
import com.example.stockmarketspringapi.service.JwtService;
import com.example.stockmarketspringapi.service.interfaces.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
  public ResponseEntity<User> register(@RequestBody UserDto user) {
    return ResponseEntity.status(201).body(authService.register(user));
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDto loginUserDto) {
    User authenticatedUser = authService.login(loginUserDto);

    String jwtToken = jwtService.generateToken(authenticatedUser);
    LoginResponse loginResponse = new LoginResponse();
    loginResponse.setToken(jwtToken);
    loginResponse.setExpiresIn(jwtService.getExpirationTime());

    return ResponseEntity.ok(loginResponse);
  }

  @GetMapping("/oauth2/login")
  public ResponseEntity<?> oAuthLogin(HttpServletResponse httpServletResponse) throws IOException {
    httpServletResponse.sendRedirect("/oauth2/authorization/google");
    return ResponseEntity.ok("Redirecting...");
  }

    @GetMapping("/loginSuccess")
    public ResponseEntity<?> handleGoogleSuccess(OAuth2AuthenticationToken token) throws IOException {
        User user = authService.oAuthLogin(token);
        return ResponseEntity.status(200).body(user);
    }
}
