package com.taxi.taxiservice.DAO;

import com.taxi.taxiservice.ConnectionPool;
import com.taxi.taxiservice.DAO.dbColumns.DriverStatusDB;
import com.taxi.taxiservice.DAO.interfaces.IDriverStatusDAO;
import com.taxi.taxiservice.Models.DriverStatus;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DriverStatusDaoImpl implements IDriverStatusDAO {
    public Connection connection = null;

    public DriverStatusDaoImpl() {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection("Driver Status Data Source");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private DriverStatus getDriverStatus(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(DriverStatusDB.columnDriverId);
        String status = resultSet.getString(DriverStatusDB.columnStatus);

        return new DriverStatus(id, status);
    }

    public ArrayList<DriverStatus> findAll() {
        String query = "select * from driver_status;";
        ArrayList<DriverStatus> statuses = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                DriverStatus driverStatus = getDriverStatus(resultSet);
                statuses.add(driverStatus);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return statuses;
    }

    public DriverStatus findByDriverID(long driverID) {
        String query = "select * from driver_status where driver_id=" + driverID;
        DriverStatus driverStatus = null;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                driverStatus = getDriverStatus(resultSet);
            }

        } catch (Exception error) {
            error.printStackTrace();
        }

        return driverStatus;
    }

    public void save(long driverID, String status) {
        String query = "call set_driver_status(" + driverID + ",'" + status + "')";

        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void update(long driverID, String status) {
        String query = "call update_driver_status(" + driverID + "," + "'" + status + "');";

        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void delete(long id) {
        String query = "call delete_driver_status(" + id + ")";

        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException error) {
            System.err.println(error.getMessage());
        }
    }
}