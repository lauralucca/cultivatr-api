package com.cultivatr.api.service;

import com.cultivatr.api.IDataBase;
import com.cultivatr.api.model.User;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class UserSignUpService {

    private IDataBase dbConnection;

    public UserSignUpService(IDataBase dbConnection) {
        this.dbConnection = dbConnection;
    }


    public void createUser(User user) throws Exception {
        String sql = String.format( "INSERT INTO user_account(id, name, email) values(DEFAULT, '%s', '%s')",
                                    user.getName(),
                                    user.getEmail());

        dbConnection.executeCommand(sql);
    }

    public User getUserByEmail(String email) throws Exception
    {
        String sql = String.format("SELECT name, email FROM user_account WHERE email = '%s'", email);
        ResultSet resultSet = dbConnection.select(sql);

        User user = convertResultSetToUser(resultSet);
        return user;
    }

    private User convertResultSetToUser(ResultSet resultSet) throws SQLException {
        User user = null;
        while (resultSet.next()) {
            user = new User(resultSet.getString("name"), resultSet.getString("email"));
        }
        return user;
    }
}
