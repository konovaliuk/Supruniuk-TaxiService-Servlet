package com.taxi.taxiservice.Controllers;

import com.google.gson.Gson;
import com.taxi.taxiservice.Models.Car.Car;
import com.taxi.taxiservice.Models.UpdateField;
import com.taxi.taxiservice.Services.CarService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CarController {
    private CarService carService;

    Gson gson = new Gson();

    public CarController() {
        carService = new CarService();
    }

    public void getCars(HttpServletRequest req, HttpServletResponse res) {
        try {
            ArrayList<Car> cars = carService.getAll();
            String json = new Gson().toJson(cars);
            res.getWriter().write(json);
            res.getWriter().flush();

        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void getCarById(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getPathInfo().substring(1);

        try {
            long carId = Long.parseLong(id);
            Car car = carService.getById(carId);
            if (car == null) {
                MessageController.badRequest(res, "Car not found");
            } else {
                String json = new Gson().toJson(car);
                res.getWriter().write(json);
            }
            res.getWriter().flush();
        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void getCarsByDriverId(HttpServletRequest req, HttpServletResponse res) {
        String[] path = req.getPathInfo().split("/");

        try {
            long driverId = Long.parseLong(path[2]);
            ArrayList<Car> cars = carService.getByDriver(driverId);
            if (cars.size() == 0) {
                MessageController.badRequest(res, "Cars not found");
            } else {
                String json = new Gson().toJson(cars);
                res.getWriter().write(json);
            }
            res.getWriter().flush();
        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void createCar(HttpServletRequest req, HttpServletResponse res) {
        try {
            String requestData = req.getReader().lines().collect(Collectors.joining());
            Car newCar = gson.fromJson(requestData, Car.class);
            if (newCar.checkValid()) {
                carService.create(newCar);
                MessageController.sendResponseMessage(res, "Car successfully created");
            } else {
                MessageController.badRequest(res, "Validation failed");
            }
        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void deleteCarById(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getPathInfo().substring(1);

        try {
            long carId = Long.parseLong(id);
            if (carService.getById(carId) != null) {
                carService.delete(carId);
                MessageController.sendResponseMessage(res, "Car deleted successfully");
            } else {
                MessageController.badRequest(res, "Car doesn`t exist");
            }
            res.getWriter().flush();
        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void updateCarById(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getPathInfo().substring(1);

        try {
            String requestData = req.getReader().lines().collect(Collectors.joining());
            UpdateField newValue = gson.fromJson(requestData, UpdateField.class);
            if (newValue.getField() != null && newValue.getValue() != null) {
                long carId = Long.parseLong(id);
                if (carService.getById(carId) != null) {
                    carService.update(carId, newValue);
                    MessageController.sendResponseMessage(res, "Car updated successfully");
                } else {
                    MessageController.badRequest(res, "Car doesn`t exist");
                }
            } else {
                MessageController.badRequest(res, "Validation failed");
            }
            res.getWriter().flush();
        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void destroy() {
        carService.closeConnection();
    }
}
