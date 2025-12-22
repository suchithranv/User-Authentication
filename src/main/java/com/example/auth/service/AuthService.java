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
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return false;
        }

        return verifyPassword(password, user.getPassword());
    }

    private boolean verifyPassword(String raw, String stored) {
        return stored.equals(raw);
    }
}
