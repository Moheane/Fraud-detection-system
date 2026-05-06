package com.frauddetectionapp.services.authentication;

import com.frauddetectionapp.Entities.user.User;
import com.frauddetectionapp.dto.auth.AuthResponse;
import com.frauddetectionapp.dto.user.AuthRequest;

import com.frauddetectionapp.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AuthenticationManager  authenticationManager;
    private final UserRepository         userRepository;
    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder        passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register(AuthRequest request, User.Role role) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();
        userRepository.save(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String token = jwtService.generateToken(userDetails);
        log.info("Registered new user: {} with role: {}", user.getUsername(), role);
        return new AuthResponse(token, role.name(), user.getUsername());
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtService.generateToken(userDetails);

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        log.info("User logged in: {}", request.getUsername());
        return new AuthResponse(token, user.getRole().name(), user.getUsername());
    }
}