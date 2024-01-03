package com.solvd.travelAgencyProject.persistence.utils;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private final static PropertyReader configReader = new PropertyReader("config.properties");
    private static BasicDataSource dataSource = null;

    static {
        dataSource = new BasicDataSource();
        dataSource.setUrl(configReader.getProperty("url"));
        dataSource.setUsername(configReader.getProperty("username"));
        dataSource.setPassword(configReader.getProperty("password"));

        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxTotal(25);
    }

    public static Connection getConnectionFromPool() throws SQLException {
        return dataSource.getConnection();
    }
}
