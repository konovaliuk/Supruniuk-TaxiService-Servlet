package com.taxi.taxiservice.Controllers;

import com.google.gson.Gson;
import com.taxi.taxiservice.DAO.CarDaoImpl;
import com.taxi.taxiservice.Models.Car.Car;
import com.taxi.taxiservice.Models.UpdateField;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CarController {
    private CarDaoImpl carDao;

    Gson gson = new Gson();

    public CarController() {
        carDao = new CarDaoImpl();
    }

    public void getCars(HttpServletRequest req, HttpServletResponse res) {
        try {
            ArrayList<Car> cars = carDao.findAll();
            String json = new Gson().toJson(cars);
            res.getWriter().write(json);
            res.getWriter().flush();

        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public void getCarById(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getPathInfo().substring(1);

        try {
            long carId = Long.parseLong(id);
            Car car = carDao.findByID(carId);
            if(car == null) {
                MessageController.badRequest(res,"Car not found");
            } else {
                String json = new Gson().toJson(car);
                res.getWriter().write(json);
            }
            res.getWriter().flush();
        } catch (Exception err) {
            System.out.println("Server error");
        }
    }

    public void getCarsByDriverId(HttpServletRequest req, HttpServletResponse res) {
        String [] path = req.getPathInfo().split("/");

        try {
            long driverId = Long.parseLong(path[2]);
            ArrayList<Car> cars = carDao.findByDriverID(driverId);
            if(cars.size() == 0) {
                MessageController.badRequest(res,"Cars not found");
            } else {
                String json = new Gson().toJson(cars);
                res.getWriter().write(json);
            }
            res.getWriter().flush();
        } catch (Exception err) {
            System.out.println("Server error");
        }
    }

    public void createCar(HttpServletRequest req, HttpServletResponse res) {
        try {
            String requestData = req.getReader().lines().collect(Collectors.joining());
            Car newCar = gson.fromJson(requestData, Car.class);
            if(newCar.getModel() != null && newCar.getColor() != null &&
                    newCar.getLicenseNumber() != null && newCar.getTypeID() != 0) {
                carDao.save(newCar);
                MessageController.sendResponseMessage(res,"Car successfully created");
            } else {
                MessageController.badRequest(res, "Incorrect field");
            }
        } catch (Exception err) {
            System.out.println("Server error");
        }
    }

    public void deleteCarById(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getPathInfo().substring(1);

        try {
            long carId = Long.parseLong(id);
            if(carDao.findByID(carId) != null) {
                carDao.delete(carId);
                MessageController.sendResponseMessage(res,"Car deleted successfully");
            } else {
                MessageController.badRequest(res, "Car doesn`t exist");
            }
            res.getWriter().flush();
        } catch (Exception err) {
            System.out.println("Server error");
        }
    }

    public void updateCarById(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getPathInfo().substring(1);

        try {
            String requestData = req.getReader().lines().collect(Collectors.joining());
            UpdateField newValue = gson.fromJson(requestData, UpdateField.class);
            if(newValue.getField() != null && newValue.getValue() != null) {
                long carId = Long.parseLong(id);
                if(carDao.findByID(carId) != null) {
                    carDao.update(carId, newValue.getField(), newValue.getValue());
                    MessageController.sendResponseMessage(res,"Car updated successfully");
                } else {
                    MessageController.badRequest(res, "Car doesn`t exist");
                }
            } else {
                MessageController.badRequest(res, "Invalid field");
            }
            res.getWriter().flush();
        } catch (Exception err) {
            System.out.println("Server error");
        }
    }

    public void destroy() {
        carDao.closeConnection();
    }
}
