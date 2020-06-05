package com.cultivatr.api.service;

import com.cultivatr.api.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserSignUpService {
    public User createUser(User user) {
        return user;
    }
}
