package com.example.auth.controller;

import com.example.auth.service.AuthService;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for authentication
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password) {

        if (authService.authenticate(username, password)) {
            return "Login successful";
        }
        return "Invalid credentials";
    }
}
