package com.cultivatr.api.service;

import com.cultivatr.api.IDataBase;
import com.cultivatr.api.model.User;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.sql.ResultSet;

import static org.mockito.Mockito.*;

public class UserSignUpServiceTest {
    User expectedUser;
    IDataBase mockedDbConnection;
    UserSignUpService userSignUpService;

    @Before
    public void initializeTestData() {
        this.expectedUser = new User("Agatha", "agatha@cultivatr.teste");
        this.mockedDbConnection = mock(IDataBase.class);
        this.userSignUpService = new UserSignUpService(mockedDbConnection);
    }

    @Test
    public void shouldCreateUser() throws Exception {
        String expectedDbQuery = String.format(
            "INSERT INTO user_account(id, name, email) values(DEFAULT, '%s', '%s')",
            this.expectedUser.getName(), this.expectedUser.getEmail()
        );

        this.userSignUpService.createUser(this.expectedUser);

        verify(this.mockedDbConnection).executeCommand(expectedDbQuery);
    }

    @Test
    public void shouldReturnUserByEmail() throws Exception {
        String expectedDbQuery = String.format(
            "SELECT name, email FROM user_account WHERE email = '%s'", this.expectedUser.getEmail());
        ResultSet mockedResultSet = mock(ResultSet.class);

        when(this.mockedDbConnection.select(expectedDbQuery)).thenReturn(mockedResultSet);
        when(mockedResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockedResultSet.getString("name")).thenReturn(this.expectedUser.getName());
        when(mockedResultSet.getString("email")).thenReturn(this.expectedUser.getEmail());

        User resultUser = this.userSignUpService.getUserByEmail(this.expectedUser.getEmail());

        verify(this.mockedDbConnection).select(expectedDbQuery);
        assertThat(resultUser).isEqualTo(this.expectedUser);
    }
}
