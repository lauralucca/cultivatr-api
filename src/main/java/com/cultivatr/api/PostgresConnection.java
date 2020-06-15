package com.cultivatr.api;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgresConnection {
    Connection connection = null;
    Statement statement = null;

    public PostgresConnection() {

        String host="ec2-35-174-127-63.compute-1.amazonaws.com";
        String port="5432";
        String db_name="db7j1of8o5pl51";
        String username="hbxtpazwblqqcv";
        String password="31ec45037b7380c7595d8a681b35c8efe05acd2d34fd2e099a1be68e1bdd2a7c";
        String driver="jdbc:postgresql";

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
}
