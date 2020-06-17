package com.cultivatr.api.controller;

import com.cultivatr.api.DbConnectionFactory;
import com.cultivatr.api.IDataBase;
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


    private UserSignUpService userSignUpService;

    @Autowired
    public UserSignUpController()
    {
        this.userSignUpService = new UserSignUpService(DbConnectionFactory.createPostgresConnection());
    }

    public UserSignUpController(UserSignUpService userSignUpService)
    {
        this.userSignUpService = userSignUpService;
    }

    @PostMapping("/user")
    public ResponseEntity<Void> saveUser(@RequestBody User user) throws Exception {
        this.userSignUpService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
