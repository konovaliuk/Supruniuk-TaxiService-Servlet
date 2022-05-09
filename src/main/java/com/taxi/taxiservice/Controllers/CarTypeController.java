package com.taxi.taxiservice.Controllers;

import com.google.gson.Gson;
import com.taxi.taxiservice.Models.CarType;
import com.taxi.taxiservice.Services.CarTypeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CarTypeController {
    private CarTypeService carTypeService;
    Gson gson = new Gson();

    public CarTypeController() {
        carTypeService = new CarTypeService();
    }

    public void getCarTypes(HttpServletRequest req, HttpServletResponse res) {
        try {
            ArrayList<CarType> carTypes = carTypeService.getAll();
            String json = new Gson().toJson(carTypes);
            res.getWriter().write(json);
            res.getWriter().flush();

        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void getCarTypeById(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getPathInfo().substring(1);

        try {
            long typeId = Long.parseLong(id);
            CarType carType = carTypeService.getById(typeId);
            if (carType == null) {
                MessageController.badRequest(res, "Car type not found");
            } else {
                String json = new Gson().toJson(carType);
                res.getWriter().write(json);
            }
            res.getWriter().flush();
        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void getCarTypeByTypename(HttpServletRequest req, HttpServletResponse res) {
        String typename = req.getParameter("type");

        try {
            CarType carType = carTypeService.getByTypename(typename);
            if (carType == null) {
                MessageController.badRequest(res, "Car type not found");
            } else {
                String json = new Gson().toJson(carType);
                res.getWriter().write(json);
            }
            res.getWriter().flush();
        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void createCarType(HttpServletRequest req, HttpServletResponse res) {
        try {
            String requestData = req.getReader().lines().collect(Collectors.joining());
            CarType newCarType = gson.fromJson(requestData, CarType.class);
            if (carTypeService.getByTypename((newCarType.getTypename())) == null) {
                if (newCarType.checkValid()) {
                    carTypeService.create(newCarType);
                    MessageController.sendResponseMessage(res, "Car type successfully created");
                } else {
                    MessageController.badRequest(res, "Validation failed");
                }
            } else {
                MessageController.badRequest(res, "Car type already exists");
            }
        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void deleteCarTypeById(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getPathInfo().substring(1);

        try {
            long carTypeId = Long.parseLong(id);
            if (carTypeService.getById(carTypeId) != null) {
                carTypeService.delete(carTypeId);
                MessageController.sendResponseMessage(res, "Car type deleted successfully");
            } else {
                MessageController.badRequest(res, "Car type doesn`t exist");
            }
            res.getWriter().flush();
        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void updateCarTypeById(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getPathInfo().substring(1);

        try {
            String requestData = req.getReader().lines().collect(Collectors.joining());
            CarType updatedCarType = gson.fromJson(requestData, CarType.class);
            if (updatedCarType.checkValid()) {
                long carTypeId = Long.parseLong(id);
                if (carTypeService.getById(carTypeId) != null) {
                    carTypeService.update(carTypeId, updatedCarType);
                    MessageController.sendResponseMessage(res, "Car type updated successfully");
                } else {
                    MessageController.badRequest(res, "Car type doesn`t exist");
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
        carTypeService.closeConnection();
    }
}
