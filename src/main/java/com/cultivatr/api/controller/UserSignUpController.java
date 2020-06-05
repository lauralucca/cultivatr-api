package com.cultivatr.api.controller;

import com.cultivatr.api.model.User;
import com.cultivatr.api.service.UserSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserSignUpController {

    @Autowired
    private UserSignUpService userSignUpService;

    @PostMapping("/user")
    public ResponseEntity<Void> saveUser(@RequestBody User user) throws Exception {
        this.userSignUpService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
