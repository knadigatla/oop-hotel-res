package com.itu.metadata;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public interface FlywayDataSource {

    DataSource getDataSource();
    Connection getConnection() throws SQLException;
    Connection getConnection(String user, String password) throws SQLException;

}
