package com.example.sustainability.controller;

//import com.example.sustainability.entity.Video;

//import com.example.sustainability.entity.Engagement;
import com.example.sustainability.entity.User;
//import com.example.sustainability.repository.EngagementRepository;
import com.example.sustainability.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {



    @Autowired
    private UserRepository userRepository;


   
}
