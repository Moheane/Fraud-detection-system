package com.frauddetectionapp.controllers;

import com.frauddetectionapp.Entities.user.User;
import com.frauddetectionapp.dto.user.AuthResponse;
import com.frauddetectionapp.dto.user.AuthRequest;
import com.frauddetectionapp.services.authentication.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "🔐 Auth", description = "Authentication APIs")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Create a transaction", description = "Creates a new transaction for a user")
    @PostMapping("/register/user")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody AuthRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authService.register(request, User.Role.ROLE_USER));
    }

    @Operation(summary = "Login", description = "Login to system")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}