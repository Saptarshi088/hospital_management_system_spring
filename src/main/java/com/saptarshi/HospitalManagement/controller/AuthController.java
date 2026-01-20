package com.saptarshi.HospitalManagement.controller;

import com.saptarshi.HospitalManagement.service.JwrService;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwrService jwrService;

    @PostMapping("/login")
    public String getJwt(@RequestBody String username){
        return jwrService.GenerateToken(username);
    }
}
