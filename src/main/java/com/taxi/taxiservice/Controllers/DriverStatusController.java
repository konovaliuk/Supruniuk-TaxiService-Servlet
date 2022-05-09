package com.taxi.taxiservice.Controllers;

import com.google.gson.Gson;
import com.taxi.taxiservice.Models.DriverStatus;
import com.taxi.taxiservice.Services.DriverStatusService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class DriverStatusController {
    private DriverStatusService driverStatusService;
    Gson gson = new Gson();

    public DriverStatusController() {
        driverStatusService = new DriverStatusService();
    }

    public void getDriverStatuses(HttpServletRequest req, HttpServletResponse res) {
        try {
            ArrayList<DriverStatus> driverStatuses = driverStatusService.getAll();
            String json = new Gson().toJson(driverStatuses);
            res.getWriter().write(json);
            res.getWriter().flush();

        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void getDriverStatusById(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getPathInfo().substring(1);

        try {
            long driverStatusId = Long.parseLong(id);
            DriverStatus driverStatus = driverStatusService.getById(driverStatusId);
            if (driverStatus == null) {
                MessageController.badRequest(res, "Not found");
            } else {
                String json = new Gson().toJson(driverStatus);
                res.getWriter().write(json);
            }
            res.getWriter().flush();
        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void setDriverStatus(HttpServletRequest req, HttpServletResponse res) {
        try {
            String requestData = req.getReader().lines().collect(Collectors.joining());
            DriverStatus newDriverStatus = gson.fromJson(requestData, DriverStatus.class);
            if (newDriverStatus.checkValid()) {
                if (driverStatusService.getByDriver(newDriverStatus.getId()) == null) {
                    driverStatusService.setStatus(newDriverStatus);
                    MessageController.sendResponseMessage(res, "Driver status successfully setted");
                } else {
                    MessageController.badRequest(res, "Driver already has status");
                }
            } else {
                MessageController.badRequest(res, "Validation failed");
            }
        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void updateDriverStatusById(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getPathInfo().substring(1);

        try {
            String requestData = req.getReader().lines().collect(Collectors.joining());
            DriverStatus driverStatus = gson.fromJson(requestData, DriverStatus.class);
            if (driverStatus.getDriverStatus() != null
                    && (Objects.equals(driverStatus.getDriverStatus(), "offline")
                    || Objects.equals(driverStatus.getDriverStatus(), "available")
                    || Objects.equals(driverStatus.getDriverStatus(), "busy"))) {
                long driverId = Long.parseLong(id);
                if (driverStatusService.getByDriver(driverId) != null) {
                    driverStatusService.update(driverId, driverStatus.getDriverStatus());
                    MessageController.sendResponseMessage(res, "Driver status updated successfully");
                } else {
                    MessageController.badRequest(res, "Driver status not exist");
                }
            } else {
                MessageController.badRequest(res, "Validation failed");
            }
            res.getWriter().flush();
        } catch (Exception err) {
            MessageController.badRequest(res, "Validation failed");
        }
    }

    public void destroy() {
        driverStatusService.closeConnection();
    }
}
