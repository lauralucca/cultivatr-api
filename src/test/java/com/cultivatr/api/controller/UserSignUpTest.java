package com.cultivatr.api.controller;

import com.cultivatr.api.model.User;
import com.cultivatr.api.service.UserSignUpService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

@WebMvcTest(UserSignUpController.class)
public class UserSignUpTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserSignUpService userSignUpService;

    @Test
    void createsUser() throws Exception {
        String name = "Agatha";
        String email = "agatha@cultivatr.teste";
        String userAsJson = "{ \"name\": \"" + name + "\", " + "\"email\": \"" + email + "\"" + "}";

        mockMvc.perform(
            post("/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(userAsJson))
            .andExpect(status().isCreated());

        User expectedUser = new User(name, email);

        verify(userSignUpService, times(1)).createUser(expectedUser);
    }
}
