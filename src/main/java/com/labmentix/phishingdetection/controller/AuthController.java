package com.labmentix.phishingdetection.controller;

import com.labmentix.phishingdetection.dto.AuthResponse;
import com.labmentix.phishingdetection.dto.LoginRequest;
import com.labmentix.phishingdetection.dto.RegisterRequest;
import com.labmentix.phishingdetection.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}