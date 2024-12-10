package com.example.sustainability.controller;

import com.example.sustainability.entity.User;
import com.example.sustainability.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    // Sign up endpoint
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        String responseMessage = authService.signup(user);
        if (responseMessage.equals("User signed up successfully!")) {
            return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);  // 201 Created
        }
        return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);  // 400 Bad Request
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user) {
        // Validate input
        if (user == null || user.getEmail() == null || user.getPassword() == null) {
            return new ResponseEntity<>("Email or Password cannot be null!", HttpStatus.BAD_REQUEST);  // 400 Bad Request
        }

        // Call service to login
        Object response = authService.login(user.getEmail(), user.getPassword());
        if (response.equals("Invalid credentials!")) {
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);  // 401 Unauthorized
        }

        return new ResponseEntity<>(response, HttpStatus.OK);  // 200 OK
    }
}
