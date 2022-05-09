package com.taxi.taxiservice.DAO;

import com.taxi.taxiservice.ConnectionPool;
import com.taxi.taxiservice.DAO.dbColumns.CarTypeColumnsDB;
import com.taxi.taxiservice.DAO.interfaces.ICarTypeDAO;
import com.taxi.taxiservice.Models.CarType;
import com.taxi.taxiservice.Models.ConnectionNames;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CarTypeDaoImpl implements ICarTypeDAO {
    public Connection connection = null;

    public CarTypeDaoImpl() {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection(ConnectionNames.carTypeDao);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private CarType getCarType(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(CarTypeColumnsDB.columnId);
        String type = resultSet.getString(CarTypeColumnsDB.columnTypename);
        String description = resultSet.getString(CarTypeColumnsDB.columnDescription);

        return new CarType(id, type, description);
    }

    public ArrayList<CarType> findAll() {
        String query = "select * from car_types;";
        ArrayList<CarType> carTypes = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                CarType carType = getCarType(resultSet);
                carTypes.add(carType);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return carTypes;
    }

    public CarType findByID(long id) {
        String query = "select * from car_types where id=" + id;
        CarType carType = null;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                carType = getCarType(resultSet);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return carType;
    }

    public CarType findByTypename(String typename) {
        String query = "select * from car_types where typename='" + typename + "'";
        CarType carType = null;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                carType = getCarType(resultSet);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return carType;
    }

    public void save(String typename, String description) {
        String query = "call create_type('" + typename + "','" + description + "')";

        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void update(long id, String typename, String description) {
        String query = "call update_type(" + id + ",'" + typename + "','" + description + "');";

        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void delete(long id) {
        String query = "call delete_type(" + id + ")";

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
