package com.example.stockmarketspringapi.service.implementations;

import com.example.stockmarketspringapi.model.dto.UserDto;
import com.example.stockmarketspringapi.model.dto.enums.ProviderEnum;
import com.example.stockmarketspringapi.model.dto.resp.LoginResponse;
import com.example.stockmarketspringapi.model.dto.userDtos.LoginUserDto;
import com.example.stockmarketspringapi.model.entity.User;
import com.example.stockmarketspringapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class AuthServiceUnitTest {
    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    AuthenticationManager authenticationManager;

    @InjectMocks
    AuthServiceImpl authService;


    @BeforeEach
    void setUp() {
        // no-op
    }

    @Test
    void registerTest(){
        UserDto dto = new UserDto();
        dto.setUsername("test_user");
        dto.setEmail("test@testmil.com");
        dto.setPasswordHash("testpass");

        when(passwordEncoder.encode("testpass")).thenReturn("hashpass");

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        User saved = new User();
        saved.setId(1L);
        saved.setUsername("test_user");
        saved.setEmail("test@testmil.com");
        saved.setpasswordHash("hashpass");
        saved.setProviderType(ProviderEnum.EMAIL);
        when(userRepository.save(any(User.class))).thenReturn(saved);

        User result = authService.register(dto);

        verify(passwordEncoder).encode("testpass");
        verify(userRepository).save(captor.capture());

        User passedToSave = captor.getValue();
        assertThat(passedToSave.getUsername()).isEqualTo("test_user");
        assertThat(passedToSave.getEmail()).isEqualTo("test@testmil.com");
        assertThat(passedToSave.getpasswordHash()).isEqualTo("hashpass");
        assertThat(passedToSave.getProviderType()).isEqualTo(ProviderEnum.EMAIL);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getEmail()).isEqualTo("test@testmil.com");

    }

    @Test
    void loginTest(){
        LoginUserDto loginUser = new LoginUserDto();
        loginUser.setEmail("test@testmail.com");
        loginUser.setPassword("pass12");

        User user = new User();
        user.setEmail("test@testmail.com");
        user.setUsername("test_user");

        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        when(userRepository.findByEmail("test@testmail.com")).thenReturn(Optional.of(user));

        User result = authService.login(loginUser);

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userRepository).findByEmail("test@testmail.com");

        assertThat(result).isSameAs(user);

    }

    @Test
    void login_withEmptyPassword() {
        LoginUserDto login = new LoginUserDto();
        login.setEmail("test@testmail.com");
        login.setPassword("   "); // blank

        assertThatThrownBy(() -> authService.login(login))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Password cannot be empty");

        verifyNoInteractions(authenticationManager);
        verifyNoInteractions(userRepository);
    }

    @Test
    void oAuthLogin_existingUser() {
        OAuth2AuthenticationToken token = mock(OAuth2AuthenticationToken.class);
        OAuth2User principal = mock(OAuth2User.class);
        when(token.getPrincipal()).thenReturn(principal);
        when(principal.getAttribute("email")).thenReturn("test@testmail.com");
        when(principal.getAttribute("name")).thenReturn("Google user");

        User existing = new User();
        existing.setEmail("test@testmail.com");
        existing.setUsername("test_user");

        when(userRepository.findByEmail("test@testmail.com")).thenReturn(Optional.of(existing));

        User result = authService.oAuthLogin(token);

        verify(userRepository).findByEmail("test@testmail.com");
        verify(userRepository, never()).save(any());
        assertThat(result).isSameAs(existing);
    }

    @Test
    void oAuthLogin_newUser() {
        OAuth2AuthenticationToken token = mock(OAuth2AuthenticationToken.class);
        OAuth2User principal = mock(OAuth2User.class);
        when(token.getPrincipal()).thenReturn(principal);
        when(principal.getAttribute("email")).thenReturn("test@testmail.com");
        when(principal.getAttribute("name")).thenReturn("New user");

        when(userRepository.findByEmail("test@testmail.com")).thenReturn(Optional.empty());

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        User saved = new User();
        saved.setId(99L);
        saved.setEmail("test@testmail.com");
        saved.setUsername("New user");
        saved.setProviderType(ProviderEnum.GOOGLE);

        when(userRepository.save(any(User.class))).thenReturn(saved);

        User result = authService.oAuthLogin(token);

        verify(userRepository).findByEmail("test@testmail.com");
        verify(userRepository).save(captor.capture());

        User toSave = captor.getValue();
        assertThat(toSave.getEmail()).isEqualTo("test@testmail.com");
        assertThat(toSave.getUsername()).isEqualTo("New user");
        assertThat(toSave.getProviderType()).isEqualTo(ProviderEnum.GOOGLE);

        assertThat(result.getId()).isEqualTo(99L);
    }
}
