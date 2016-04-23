package com.itu.metadata;

import com.itu.util.PropertyFetcher;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;


public class FlywayDataSourceImpl implements FlywayDataSource {

    protected ComboPooledDataSource dataSource;

    public FlywayDataSourceImpl() throws PropertyVetoException {
        this.dataSource = new ComboPooledDataSource();
        PropertyFetcher metadataProperties = new PropertyFetcher(new File("/Users/kiran/Documents/github/oop-hotel-res/src/main/resources/db.properties"));
        this.dataSource.setDriverClass(metadataProperties.getProperty("DB_DRIVER_CLASS"));
        this.dataSource.setJdbcUrl(metadataProperties.getProperty("DB_URL"));
        this.dataSource.setUser(metadataProperties.getProperty("DB_USERNAME"));
        this.dataSource.setPassword(metadataProperties.getProperty("DB_PASSWORD"));
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