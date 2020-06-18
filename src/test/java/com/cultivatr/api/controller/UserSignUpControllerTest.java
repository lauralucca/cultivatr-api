package com.cultivatr.api.controller;

import com.cultivatr.api.model.User;
import com.cultivatr.api.service.UserSignUpService;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


import org.springframework.http.HttpStatus;

import static org.mockito.Mockito.*;

public class UserSignUpControllerTest {
    UserSignUpController userSignUpController;
    UserSignUpService userSignUpService;

    @Test
    void shouldSaveUser() throws Exception {
        String name = "Agatha";
        String email = "agatha@cultivatr.teste";
        int httpStatusCreated = 201;
        User expectedUser = new User(name, email);

        userSignUpService = mock(UserSignUpService.class);
        userSignUpController = new UserSignUpController(userSignUpService);

        HttpStatus httpStatus = userSignUpController.saveUser(expectedUser).getStatusCode();

        verify(userSignUpService).createUser(expectedUser);
        assertThat(httpStatus.value()).isEqualTo(httpStatusCreated);

    }
}
