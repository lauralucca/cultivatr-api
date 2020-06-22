package com.cultivatr.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.sql.*;

@Component
public class PostgresConnection implements IDataBase {
    Connection connection = null;
    Statement statement = null;

    public PostgresConnection(
        @Value("${db.host}") String host,
        @Value("${db.port}") String port,
        @Value("${db.name}") String db_name,
        @Value("${db.username}") String username,
        @Value("${db.password}") String password,
        @Value("${db.driver}") String driver

    ) {

        try {
            connection = DriverManager.getConnection(driver + "://"+host+":"+port+"/"+db_name+"", ""+username+"", ""+password+"");
            if (connection != null) {
                System.out.println("Connection OK");
            } else {
                System.out.println("Connection Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeCommand(String query) throws SQLException {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet select(String sql) throws SQLException {
        statement = connection.createStatement();
        return statement.executeQuery(sql);
    }
}
