package com.saptarshi.HospitalManagement.controller;

import com.saptarshi.HospitalManagement.dto.JwtResponse;
import com.saptarshi.HospitalManagement.dto.LoginRequest;
import com.saptarshi.HospitalManagement.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> getJwt(@RequestBody LoginRequest request){
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        return ResponseEntity.ok(new JwtResponse(jwtService.GenerateToken(request.getEmail())));

    }

    @PostMapping("/validate")
    public Boolean validateToken(@RequestHeader("Authorization") String token){
        token = token.replace("Bearer ","");
        return jwtService.validateToken(token);
    }
    @GetMapping("/me")
    public ResponseEntity<String> getUserName(@RequestHeader("Authorization") String token){
        token = token.replace("Bearer ","");
        return ResponseEntity.ok(jwtService.getUserName(token));
    }
}
