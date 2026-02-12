package com.saptarshi.HospitalManagement.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;

@Service
public class JwtService {

    private static final Duration TOKEN_TTL = Duration.ofHours(1);

    @Value("${jwt.secret}")
    private String secret;

    public String GenerateToken(String userName){
        return Jwts.builder()
                .subject(userName)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + TOKEN_TTL.toMillis()))
                .compact();
    }

    public Boolean validateToken(String token){
        return getPayload(token)
                .getExpiration().after(new Date());
    }

    private Claims getPayload(String token) {
        return Jwts
                .parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getUserName(String token){
        return getPayload(token).getSubject();
    }
}
