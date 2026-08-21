package com.example.sahil.eventportal.service.JwtService;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(String email);
    Boolean validateToken(String token, UserDetails user);
    String getEmail(String token);
}
