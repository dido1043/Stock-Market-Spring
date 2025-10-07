package com.example.stockmarketspringapi.service.implementations;

import com.example.stockmarketspringapi.model.dto.UserDto;
import com.example.stockmarketspringapi.model.dto.enums.ProviderEnum;
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
import org.springframework.security.crypto.password.PasswordEncoder;

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
}
