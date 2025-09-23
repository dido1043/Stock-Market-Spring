package com.example.stockmarketspringapi.service.interfaces;

import com.example.stockmarketspringapi.model.dto.userDtos.LoginUserDto;
import com.example.stockmarketspringapi.model.dto.UserDto;
import com.example.stockmarketspringapi.model.entity.User;

public interface AuthService {
    User register(UserDto user);
    User login(LoginUserDto loginUserDto);
}
