package com.taxi.taxiservice;


import org.postgresql.ds.PGPoolingDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static final String URL = "jdbc:postgresql://localhost:5432/taxi_service";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "timofey";

    public ConnectionPool() {

    }

    private static ConnectionPool instance = null;

    public static ConnectionPool getInstance() {
        if (instance == null) {
            System.out.println("here");
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection(String dataSourceName) {
        Connection connection = null;
        try {
            PGPoolingDataSource source = new PGPoolingDataSource();
            source.setDataSourceName(dataSourceName);
            source.setServerName("localhost");
            source.setDatabaseName("taxi_service");
            source.setUser(USERNAME);
            source.setPassword(PASSWORD);
            //source.setMaxConnections(10);
            connection = source.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
