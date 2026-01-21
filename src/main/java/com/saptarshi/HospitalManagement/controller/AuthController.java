package com.saptarshi.HospitalManagement.controller;

import com.saptarshi.HospitalManagement.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;

    @PostMapping("/login")
    public String getJwt(@RequestBody String username){
        return jwtService.GenerateToken(username);
    }

    @PostMapping("/validate")
    public Boolean validateToken(@RequestHeader("Authorization") String token){
        token = token.replace("Bearer ","");
        return jwtService.validateToken(token);
    }
}
