package com.solvd.travelAgencyProject.persistence;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static BasicDataSource dataSource = null;

    static {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/?user=root");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");

        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxTotal(25);
    }

    public static Connection getConnectionFromPool() throws SQLException {
        return dataSource.getConnection();
    }
}
