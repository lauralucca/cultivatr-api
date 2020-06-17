package com.cultivatr.api;

public class DbConnectionFactory {

    public static IDataBase createPostgresConnection()
    {
        return new PostgresConnection();
    }
}
