package com.taxi.taxiservice.Controllers;

import com.google.gson.Gson;
import com.taxi.taxiservice.DAO.DriverStatusDaoImpl;
import com.taxi.taxiservice.Models.DriverStatus;
import com.taxi.taxiservice.Models.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class DriverStatusController {
    private DriverStatusDaoImpl driverStatusDAO;
    Gson gson = new Gson();

    public  DriverStatusController () {
        driverStatusDAO = new DriverStatusDaoImpl();
    }

    public void getDriverStatuses(HttpServletRequest req, HttpServletResponse res) {
        try {
            ArrayList<DriverStatus> driverStatuses = driverStatusDAO.findAll();
            String json = new Gson().toJson(driverStatuses);
            res.getWriter().write(json);
            res.getWriter().flush();

        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public void getDriverStatusById(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getPathInfo().substring(1);

        try {
            long driverStatusId = Long.parseLong(id);
            DriverStatus driverStatus = driverStatusDAO.findByDriverID(driverStatusId);
            if(driverStatus == null) {
                MessageController.badRequest(res,"Not found");
            } else {
                String json = new Gson().toJson(driverStatus);
                res.getWriter().write(json);
            }
            res.getWriter().flush();
        } catch (Exception err) {
            System.out.println("Server error");
        }
    }

    public void setDriverStatus(HttpServletRequest req, HttpServletResponse res) {
        try {
            String requestData = req.getReader().lines().collect(Collectors.joining());
            DriverStatus newDriverStatus = gson.fromJson(requestData, DriverStatus.class);
            if(driverStatusDAO.findByDriverID((newDriverStatus.getId())) == null && newDriverStatus.getDriverStatus() != null) {
                driverStatusDAO.save(newDriverStatus.getId(), newDriverStatus.getDriverStatus());
                MessageController.sendResponseMessage(res,"Driver status successfully setted");
            } else {
                MessageController.badRequest(res, "Driver already has status");
            }
        } catch (Exception err) {
            System.out.println("Server error");
        }
    }
    public void updateDriverStatusById(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getPathInfo().substring(1);

        try {
            String requestData = req.getReader().lines().collect(Collectors.joining());
            DriverStatus driverStatus = gson.fromJson(requestData, DriverStatus.class);
            if(driverStatus.getDriverStatus() != null
                    && (Objects.equals(driverStatus.getDriverStatus(), "offline")
                    || Objects.equals(driverStatus.getDriverStatus(), "available")
                    || Objects.equals(driverStatus.getDriverStatus(), "busy"))) {
                long driverId = Long.parseLong(id);
                if(driverStatusDAO.findByDriverID(driverId) != null) {
                    driverStatusDAO.update(driverId, driverStatus.getDriverStatus());
                    MessageController.sendResponseMessage(res,"Driver status updated successfully");
                } else {
                    MessageController.badRequest(res, "Driver status not exist");
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
        driverStatusDAO.closeConnection();
    }
}
