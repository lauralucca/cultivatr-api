package com.cultivatr.api.controller;

import com.cultivatr.api.model.User;
import com.cultivatr.api.service.UserSignUpService;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.mockito.Mockito.*;


@WebMvcTest(UserSignUpController.class)
public class UserSignUpControllerTest {
    UserSignUpController userSignUpController;
    UserSignUpService userSignUpService;

    @Test
    void createsUser() throws Exception {
        String name = "Agatha";
        String email = "agatha@cultivatr.teste";
        User expectedUser = new User(name, email);

        userSignUpService = mock(UserSignUpService.class);
        userSignUpController = new UserSignUpController(userSignUpService);

        userSignUpController.saveUser(expectedUser);

        verify(userSignUpService, times(1)).createUser(expectedUser);

    }
}
