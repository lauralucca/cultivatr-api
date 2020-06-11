package com.cultivatr.api;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgresConnection {
    Connection connection = null;
    Statement statement = null;

    public PostgresConnection() {
        String host="localhost";
        String port="5432";
        String db_name="cultivatr";
        String username="postgres";
        String password="12345";

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://"+host+":"+port+"/"+db_name+"", ""+username+"", ""+password+""
            );
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
}
