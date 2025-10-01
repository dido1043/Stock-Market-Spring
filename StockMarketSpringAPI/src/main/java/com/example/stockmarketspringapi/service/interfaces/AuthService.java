package com.example.stockmarketspringapi.service.interfaces;

import com.example.stockmarketspringapi.model.dto.userDtos.LoginUserDto;
import com.example.stockmarketspringapi.model.dto.UserDto;
import com.example.stockmarketspringapi.model.entity.User;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface AuthService {
    User register(UserDto user);
    User login(LoginUserDto loginUserDto);

    User oAuthLogin(OAuth2AuthenticationToken oAuth2AuthenticationToken);
}
