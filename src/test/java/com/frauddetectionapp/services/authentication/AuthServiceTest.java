package com.frauddetectionapp.services.authentication;

import com.frauddetectionapp.Entities.user.User;
import com.frauddetectionapp.dto.auth.AuthResponse;
import com.frauddetectionapp.dto.user.AuthRequest;
import com.frauddetectionapp.repositories.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock private AuthenticationManager authenticationManager;
    @Mock private UserRepository userRepository;
    @Mock private UserDetailsServiceImpl userDetailsService;
    @Mock private PasswordEncoder passwordEncoder;
    @Mock private JwtService jwtService;

    @InjectMocks
    private AuthService authService;

    private AuthRequest request;

    @BeforeEach
    void setUp() {
        request = new AuthRequest();
        request.setUsername("testuser");
        request.setPassword("pass123");
    }

    @Test
    void shouldRegisterUserSuccessfully() {
        User user = User.builder().username("testuser").role(User.Role.ROLE_USER).build();
        when(passwordEncoder.encode(any())).thenReturn("encodedPass");
        when(userRepository.save(any())).thenReturn(user);
        when(userDetailsService.loadUserByUsername(any())).thenReturn(mock(UserDetails.class));
        when(jwtService.generateToken(any())).thenReturn("jwt-token");

        AuthResponse response = authService.register(request, User.Role.ROLE_USER);

        assertNotNull(response);
        assertEquals("testuser", response.getUsername());
    }
}