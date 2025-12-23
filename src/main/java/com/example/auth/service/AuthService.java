package com.example.auth.service;

import com.example.auth.model.User;
import com.example.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Authentication business logic
 */
@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Authenticate user against PostgreSQL
     */
    public boolean authenticate(String username, String password) {

        // Username validations
        if (username == null || username.trim().isEmpty()) {
            return false;
        }

        if (username.length() < 3 || username.length() > 20) {
            return false;
        }

        // Password validations
        if (password == null || password.trim().isEmpty()) {
            return false;
        }

        if (password.length() < 6) {
            return false;
        }

        // Database lookup
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return false;
        }

        // Password verification
        return verifyPassword(password, user.getPassword());
    }

    private boolean verifyPassword(String raw, String stored) {
        return stored.equals(raw);
    }
}
