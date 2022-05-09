package com.taxi.taxiservice.DAO;

import com.taxi.taxiservice.ConnectionPool;
import com.taxi.taxiservice.DAO.dbColumns.CarColumnsDB;
import com.taxi.taxiservice.DAO.interfaces.ICarDAO;
import com.taxi.taxiservice.Models.Car.Car;
import com.taxi.taxiservice.Models.ConnectionNames;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CarDaoImpl implements ICarDAO {
    public Connection connection = null;

    public CarDaoImpl() {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection(ConnectionNames.carDao);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private Car getCar(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(CarColumnsDB.columnId);
        long driver_id = resultSet.getLong(CarColumnsDB.columnDriverId);
        String license_number = resultSet.getString(CarColumnsDB.columnLisenceNumber);
        String model = resultSet.getString(CarColumnsDB.columnModel);
        String color = resultSet.getString(CarColumnsDB.columnColor);
        long type_id = resultSet.getLong(CarColumnsDB.columnTypeId);
        String creation_date = resultSet.getString(CarColumnsDB.columnCreationDate);

        return new Car(id, driver_id, license_number, model, color, type_id, creation_date);
    }

    public ArrayList<Car> findAll() {
        String query = "select * FROM cars";
        ArrayList<Car> cars = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Car car = getCar(resultSet);
                cars.add(car);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return cars;
    }

    public Car findByID(long carID) {
        String query = "select * from cars where id=" + carID;
        Car car = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                car = getCar(resultSet);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return car;
    }

    public ArrayList<Car> findByDriverID(long driverID) {
        String query = "select * from cars where driver_id=" + driverID;
        ArrayList<Car> cars = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Car car = getCar(resultSet);
                cars.add(car);
            }

        } catch (Exception error) {
            error.printStackTrace();
        }

        return cars;
    }

    public long save(Car car) {
        long newCarID = 0;
        String query = "call create_car(null ,'"
                + car.getDriverID() + "','"
                + car.getLicenseNumber() + "','"
                + car.getModel() + "','"
                + car.getColor() + "',"
                + car.getTypeID() + ")";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                long ind = resultSet.getLong("ind");
                newCarID = ind;
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return newCarID;
    }

    public void update(long carID, String field, String value) {
        String query = "call update_car_" + field + "(" + carID + "," + "'" + value + "');";

        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void update(long carID, String field, long value) {
        String query = "call update_car_" + field + "(" + carID + "," + value + ");";

        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void delete(long carID) {
        String query = "call delete_car(" + carID + ")";

        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void deleteByDriver(long driverId) {
        String query = "DELETE FROM cars WHERE driver_id=" + driverId;

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
