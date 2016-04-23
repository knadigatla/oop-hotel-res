package com.itu.metadata;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;


public class FlywayDataSourceImpl implements FlywayDataSource {

    protected ComboPooledDataSource dataSource;

    final static Logger LOG = Logger.getLogger(FlywayDataSourceImpl.class);

    public FlywayDataSourceImpl() throws PropertyVetoException {
        this.dataSource = new ComboPooledDataSource();
        this.dataSource.setDriverClass("org.h2.Driver");
        this.dataSource.setJdbcUrl("jdbc:h2:file:test-db/oop-metadata");
        this.dataSource.setUser("test");
        this.dataSource.setPassword("");
    }


    public ComboPooledDataSource getDataSource() {
        return this.dataSource;
    }

    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }

    public Connection getConnection(String user, String password) throws SQLException {
        return this.dataSource.getConnection(user, password);
    }
}