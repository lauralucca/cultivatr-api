package com.cultivatr.api.controller;

import com.cultivatr.api.model.User;
import com.cultivatr.api.service.UserSignUpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserSignUpController {

    private UserSignUpService userSignUpService;

    public UserSignUpController(UserSignUpService userSignUpService)
    {
        this.userSignUpService = userSignUpService;
    }

    @PostMapping("/user")
    public ResponseEntity<Void> saveUser(@RequestBody User user) throws Exception {
        this.userSignUpService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/user")
    public String getUserByEmail(@RequestParam(value = "email") String email) throws Exception {
        User user = this.userSignUpService.getUserByEmail(email);
        ObjectMapper objectMapper = new ObjectMapper();
        return  objectMapper.writeValueAsString(user);
    }

}
