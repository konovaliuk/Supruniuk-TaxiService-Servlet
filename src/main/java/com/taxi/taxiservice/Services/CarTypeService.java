package com.taxi.taxiservice.Services;

import com.taxi.taxiservice.DAO.CarTypeDaoImpl;
import com.taxi.taxiservice.DAO.DriverStatusDaoImpl;
import com.taxi.taxiservice.Models.CarType;
import com.taxi.taxiservice.Models.DriverStatus;

import java.util.ArrayList;

public class CarTypeService {
    private CarTypeDaoImpl carTypeDAO;

    public CarTypeService() {
        carTypeDAO = new CarTypeDaoImpl();
    }

    public ArrayList<CarType> getAll() {
        ArrayList<CarType> carTypes = carTypeDAO.findAll();
        return carTypes;
    }

    public CarType getById(long id) {
        CarType carType = carTypeDAO.findByID(id);
        return carType;
    }

    public CarType getByTypename(String typename) {
        CarType carType = carTypeDAO.findByTypename(typename);
        return carType;
    }

    public void create(CarType carType) {
        carTypeDAO.save(carType.getTypename(), carType.getDescription());
    }

    public void delete(long id) {
        carTypeDAO.delete(id);
    }

    public void update(long id, CarType carType) {
        carTypeDAO.update(id, carType.getTypename(), carType.getDescription());
    }

    public void closeConnection() {
        carTypeDAO.closeConnection();
    }
}
