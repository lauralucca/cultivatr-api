package com.cultivatr.api.service;


import com.cultivatr.api.IDataBase;
import com.cultivatr.api.model.User;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UserSignUpServiceTest {
    @Test
    public void shouldCreateUser() throws Exception {
        String name = "Agatha";
        String email = "agatha@cultivatr.teste";
        User user = new User(name, email);
        String expectedDbQuery = String.format(
            "INSERT INTO user_account(id, name, email) values(DEFAULT, '%s', '%s')",
            name,
            email
        );

        IDataBase mockedDbConnection = mock(IDataBase.class);

        UserSignUpService userSignUpService = new UserSignUpService(mockedDbConnection);
        userSignUpService.createUser(user);

        verify(mockedDbConnection).executeCommand(expectedDbQuery);
    }
}
