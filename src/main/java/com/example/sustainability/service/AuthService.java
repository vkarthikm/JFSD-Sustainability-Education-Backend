package com.example.sustainability.service;

import com.example.sustainability.entity.User;
import com.example.sustainability.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public String signup(User user) {
        System.out.println("Received user: " + user.getEmail()); // Log the received email
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return "User already exists!";
        }
        user.setRegistrationDate(new Date());
        userRepository.save(user);
        return "User signed up successfully!";
    }


    public Object login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            // Remove sensitive data (like password) from the user object before sending it
            user.setPassword(null); // Clear password before returning user details
            return user; // Return user details (without password)
        }
        return "Invalid credentials!";
    }
}
