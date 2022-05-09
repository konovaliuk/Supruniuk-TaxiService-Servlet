package com.taxi.taxiservice.Services;

import com.taxi.taxiservice.DAO.CarDaoImpl;
import com.taxi.taxiservice.Models.Car.Car;
import com.taxi.taxiservice.Models.UpdateField;

import java.util.ArrayList;

public class CarService {
    private CarDaoImpl carDao;

    public CarService() {
        carDao = new CarDaoImpl();
    }

    public ArrayList<Car> getAll() {
        ArrayList<Car> cars = carDao.findAll();
        return cars;
    }

    public Car getById(long id) {
        Car car = carDao.findByID(id);
        return car;
    }

    public ArrayList<Car> getByDriver(long id) {
        ArrayList<Car> cars = carDao.findByDriverID(id);
        return cars;
    }

    public void create(Car newCar) {
        carDao.save(newCar);
    }

    public void update(long id, UpdateField car) {
        carDao.update(id, car.getField(), car.getValue());
    }

    public void delete(long id) {
        carDao.delete(id);
    }

    public void closeConnection() {
        carDao.closeConnection();
    }

}
