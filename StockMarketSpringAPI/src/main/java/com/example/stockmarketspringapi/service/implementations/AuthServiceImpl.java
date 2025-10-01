package com.example.stockmarketspringapi.service.implementations;

import com.example.stockmarketspringapi.model.dto.enums.ProviderEnum;
import com.example.stockmarketspringapi.model.dto.userDtos.LoginUserDto;
import com.example.stockmarketspringapi.model.dto.UserDto;
import com.example.stockmarketspringapi.model.entity.User;
import com.example.stockmarketspringapi.repository.UserRepository;
import com.example.stockmarketspringapi.service.interfaces.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }
    @Override
    public User register(UserDto user) {
        User usr = new User();
        usr.setUsername(user.getUsername());
        usr.setEmail(user.getEmail());
        usr.setpasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        return userRepository.save(usr);
    }

    @Override
    public User login(LoginUserDto loginUserDto) {

        String password = loginUserDto.getPassword();
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDto.getEmail(),
                        loginUserDto.getPassword()
                )
        );
        return userRepository.findByEmail(loginUserDto.getEmail())
                .orElseThrow();
    }

    @Override
    public User oAuthLogin(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        OAuth2User oAuth2User = oAuth2AuthenticationToken.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        String username = oAuth2User.getAttribute("name");

        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    User u = new User();
                    u.setUsername(username);
                    u.setEmail(email);
                    u.setProviderType(ProviderEnum.GOOGLE);
                    return userRepository.save(u);
                });

//        if (user == null) {
//            user = new User();
//            user.setUsername(username);
//            user.setEmail(email);
//            user.setProviderType(ProviderEnum.GOOGLE);
//            userRepository.save(user);
//        }
        return user;
    }
}
