package com.taxi.taxiservice;


import com.taxi.taxiservice.Models.Connections;
import org.postgresql.ds.PGPoolingDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionPool {
    private static final String URL = "jdbc:postgresql://localhost:5432/taxi_service";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "timofey";
    private static Connections connections = new Connections();
    public ConnectionPool() {

    }

    private static ConnectionPool instance = null;

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection(String dataSourceName) {
        Connection connection = null;
        try {
            Connection conn = connections.getConnectionByField(dataSourceName);
            if(conn != null){
                return conn;
            }
            PGPoolingDataSource source = new PGPoolingDataSource();
            source.setDataSourceName(dataSourceName);
            source.setServerName("localhost");
            source.setDatabaseName("taxi_service");
            source.setUser(USERNAME);
            source.setPassword(PASSWORD);
            connection = source.getConnection();
            connections.setConnectionByField(dataSourceName, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
