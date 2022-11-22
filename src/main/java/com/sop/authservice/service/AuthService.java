package com.sop.authservice.service;

import com.sop.authservice.entities.AuthRequest;
import com.sop.authservice.entities.AuthResponse;
import com.sop.authservice.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final JwtUtil jwt;

    @Autowired
    public AuthService(final JwtUtil jwt){
        this.jwt = jwt;
    }

    public AuthResponse logIn(AuthRequest authRequest){
        // check email and pasword here
        User user = User.builder()
                .id("1")
                .email(authRequest.getEmail())
                .password(authRequest.getPassword())
                .role("admin")
                .build();
        String accessToken = jwt.generate(user, "ACCESS");
        String refreshToken = jwt.generate(user, "REFRESH");

        return new AuthResponse(accessToken, refreshToken);
    }
}