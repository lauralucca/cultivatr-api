package com.cultivatr.api.service;

import com.cultivatr.api.PostgresConnection;
import com.cultivatr.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSignUpService {
    private PostgresConnection postgresConnection;

    public void createUser(User user) throws Exception {
        postgresConnection= new PostgresConnection();
        String sql = String.format( "INSERT INTO user_account(id, name, email) values(DEFAULT, '%s', '%s')",
                                    user.getName(),
                                    user.getEmail());

        postgresConnection.executeCommand(sql);
    }
}
