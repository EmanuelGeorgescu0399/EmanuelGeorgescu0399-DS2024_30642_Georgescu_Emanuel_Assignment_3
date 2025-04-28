package com.example.energy_management.Config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtils {

    private static final String SECRET_KEY = "my-super-secret-2025-my-super-secret-2025"; // 🔥 256 bits (32 caractere)

    private static final long EXPIRATION_TIME = 3600_000; // 1 oră (în milisecunde)

    private static final Key KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // Generează token
    public static String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }
}
