package com.taxi.taxiservice.Models;

import java.sql.Connection;

public class Connections {
    private Connection users;
    private Connection role;
    private Connection car;
    private Connection carType;
    private Connection orders;
    private Connection driverStatus;
    private Connection userRole;


    public Connections() {
    }

    public Connection getUserConnection() {
        return users;
    }

    public Connection getRoleConnection() {
        return role;
    }

    public Connection getCarConnection() {
        return car;
    }

    public Connection getOrderConnection() {
        return orders;
    }

    public Connection getCarTypeConnection() {
        return carType;
    }

    public Connection getDriverStatusConnection() {
        return driverStatus;
    }

    public Connection getUserRoleStatusConnection() {
        return userRole;
    }


    public void setNameConnection(Connection connection) {
        this.users = connection;
    }

    public void setCarConnection(Connection connection) {
        this.car = connection;
    }

    public void setOrderConnection(Connection connection) {
        this.orders = connection;
    }

    public void setRoleConnection(Connection connection) {
        this.role = connection;
    }

    public void setUserRoleConnection(Connection connection) {
        this.userRole = connection;
    }

    public void setCarTypeConnection(Connection connection) {
        this.carType = connection;
    }

    public void setDriverStatusConnection(Connection connection) {
        this.driverStatus = connection;
    }


    public Connection getConnectionByField(String field) {
        switch (field) {
            case "user":
                return getUserConnection();
            case "role":
                return getRoleConnection();
            case "order":
                return getOrderConnection();
            case "car":
                return getCarConnection();
            case "carType":
                return getCarTypeConnection();
            case "driverStatus":
                return getDriverStatusConnection();
            case "userRole":
                return getUserRoleStatusConnection();
        }
        return null;
    }

    public void setConnectionByField(String field, Connection connection) {
        switch (field) {
            case "user":
                setNameConnection(connection);
                break;
            case "role":
                setRoleConnection(connection);
                break;
            case "order":
                setOrderConnection(connection);
                break;
            case "car":
                setCarConnection(connection);
                break;
            case "carType":
                setCarTypeConnection(connection);
                break;
            case "driverStatus":
                setDriverStatusConnection(connection);
                break;
            case "userRole":
                setUserRoleConnection(connection);
                break;
        }
    }

}
