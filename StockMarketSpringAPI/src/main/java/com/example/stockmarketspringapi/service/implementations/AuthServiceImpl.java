package com.example.stockmarketspringapi.service.implementations;

import com.example.stockmarketspringapi.model.dto.UserDto;
import com.example.stockmarketspringapi.model.entity.User;
import com.example.stockmarketspringapi.repository.UserRepository;
import com.example.stockmarketspringapi.service.interfaces.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }
    @Override
    public User register(UserDto user) {
        User usr = new User();
        usr.setUsername(user.getUsername());
        usr.setEmail(user.getEmail());
        usr.setpasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        return userRepository.save(usr);
    }
}
