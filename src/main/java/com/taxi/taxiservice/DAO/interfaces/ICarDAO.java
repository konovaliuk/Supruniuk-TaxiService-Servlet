package com.taxi.taxiservice.DAO.interfaces;

import com.taxi.taxiservice.Models.Car.Car;

import java.util.ArrayList;

public interface ICarDAO {
    ArrayList<Car> findAll();
    Car findByID(long id);
    ArrayList<Car> findByDriverID(long driverID);
    long save(Car car);
    void update(long id, String field, String value);
    void update(long id, String field, long value);
    void delete(long id);
}
