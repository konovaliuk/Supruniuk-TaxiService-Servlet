package com.taxi.taxiservice;

import com.taxi.taxiservice.Controllers.CarTypeController;
import com.taxi.taxiservice.Controllers.DriverStatusController;
import com.taxi.taxiservice.Controllers.RoleController;
import com.taxi.taxiservice.Controllers.UserController;

import java.io.*;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(urlPatterns = {"/roles", "/roles/*", "/car-types", "/car-types/*", "/driver-statuses", "/driver-statuses/*", "/users", "/users/*"})
public class Main extends HttpServlet {
    private String message;
    private RoleController roleController;
    private CarTypeController carTypeController;
    private DriverStatusController driverStatusController;

    private UserController userController;

    public void init() {
        roleController = new RoleController();
        carTypeController = new CarTypeController();
        driverStatusController = new DriverStatusController();
        userController = new UserController();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getServletPath();
        String pathInfo = request.getPathInfo();
        String uri = request.getRequestURI();
        System.out.println("GET");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            switch (action) {
                case "/roles":
                    if(pathInfo != null) {
                        String [] path = pathInfo.split("/");
                        if(Objects.equals(path[1], "rolename")) {
                            roleController.getByRolename(request, response);
                        } else {
                            roleController.getRoleById(request, response);
                        }
                    } else {
                        roleController.getRoles(request, response);
                    }
                    break;
                case "/car-types":
                    if(pathInfo != null) {
                        String [] path = pathInfo.split("/");
                        if(Objects.equals(path[1], "typename")) {
                            carTypeController.getCarTypeByTypename(request, response);
                        } else {
                            carTypeController.getCarTypeById(request, response);
                        }
                    } else {
                        carTypeController.getCarTypes(request, response);
                    }
                    break;
                case "/driver-statuses":
                    //statuses: offline, busy, available
                    if(pathInfo != null) {
                        driverStatusController.getDriverStatusById(request, response);
                    } else {
                        driverStatusController.getDriverStatuses(request, response);
                    }
                    break;
                case "/users":
                    if(pathInfo != null) {
                        userController.getUserById(request, response);
                    } else {
                        userController.getUsers(request, response);
                    }
                    break;
            }
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getServletPath();
        System.out.println("POST");

        try {
            switch (action) {
                case "/roles":
                    roleController.createRole(request, response);
                    break;
                case "/car-types":
                    carTypeController.createCarType(request, response);
                    break;
                case "/driver-statuses":
                    driverStatusController.setDriverStatus(request, response);
                    break;
                case "/users":
                    userController.createUser(request, response);
                    break;
            }
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        String pathInfo = request.getPathInfo();
        System.out.println("DELETE");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            switch (action) {
                case "/roles":
                    if(pathInfo != null) {
                        roleController.deleteRoleById(request, response);
                    }
                    break;
                case "/car-types":
                    if(pathInfo != null) {
                        carTypeController.deleteCarTypeById(request, response);
                    }
                    break;
                case "/users":
                    if(pathInfo != null) {
                        userController.deleteUserById(request, response);
                    }
                    break;

            }
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        String pathInfo = request.getPathInfo();
        System.out.println("POST");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            switch (action) {
                case "/roles":
                    if(pathInfo != null) {
                        roleController.updateRoleById(request, response);
                    }
                    break;
                case "/car-types":
                    if(pathInfo != null) {
                        carTypeController.updateCarTypeById(request, response);
                    }
                    break;
                case "/driver-statuses":
                    if(pathInfo != null) {
                        driverStatusController.updateDriverStatusById(request, response);
                    }
                case "/users":
                    if(pathInfo != null) {
                        userController.updateUserById(request, response);
                    }
            }
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public void destroy() {
        roleController.destroy();
        carTypeController.destroy();
        driverStatusController.destroy();
        userController.destroy();
    }
}