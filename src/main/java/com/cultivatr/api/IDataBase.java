package com.cultivatr.api;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDataBase {

    void executeCommand(String query) throws SQLException;

    ResultSet select(String sql) throws SQLException;
}
