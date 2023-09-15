package com.example.springsecuritypoc.controller;

import com.example.springsecuritypoc.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class SecurityController {

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/secured")
    public String securedEndpoint() {
        return "This is a secured endpoint.";
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> loginRequest) {
        // Authenticate the user (e.g., validate credentials)
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        // For simplicity, assume successful authentication
        // Generate a JWT token and return it
        String userToken = jwtUtil.generateToken(username);
        return userToken;
        //return jwtUtil.generateToken(username);
    }
}

