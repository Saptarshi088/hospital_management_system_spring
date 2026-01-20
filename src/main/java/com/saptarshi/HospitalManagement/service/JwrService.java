package com.saptarshi.HospitalManagement.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class JwrService {
    public String GenerateToken(String userName){
        return Jwts.builder()
                .subject(userName)
                .signWith(Keys.hmacShaKeyFor("iudhiajnjalhdauiodhsadjalksdnauoshddoaisdjosaidjaoihdpowd".getBytes()))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 600000))
                .compact();
    }
}
