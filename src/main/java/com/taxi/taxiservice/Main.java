package com.taxi.taxiservice;

import com.taxi.taxiservice.Controllers.*;

import java.io.*;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(urlPatterns = {"/roles", "/roles/*", "/car-types", "/car-types/*",
        "/driver-statuses", "/driver-statuses/*", "/users", "/users/*",
        "/role-connections", "/role-connections/*", "/cars", "/cars/*",
        "/orders", "/orders/*"})
public class Main extends HttpServlet {
    private RoleController roleController;
    private CarTypeController carTypeController;
    private DriverStatusController driverStatusController;

    private UserController userController;
    private UserRoleController userRoleController;
    private CarController carController;
    private OrderController orderController;

    public void init() {
        roleController = new RoleController();
        carTypeController = new CarTypeController();
        driverStatusController = new DriverStatusController();
        userController = new UserController();
        userRoleController = new UserRoleController();
        carController = new CarController();
        orderController = new OrderController();
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
                case "/role-connections":
                    if(pathInfo != null) {
                        String [] path = pathInfo.split("/");
                        if(Objects.equals(path[1], "user")) {
                            userRoleController.getUserRoles(request, response);
                        } else if (Objects.equals(path[1], "role")) {
                            userRoleController.getUsersByRole(request, response);
                        } else {
                            userRoleController.getConnectionById(request, response);
                        }
                    } else {
                        userRoleController.getRoleConnections(request, response);
                    }
                    break;
                case "/cars":
                    if(pathInfo != null) {
                        String [] path = pathInfo.split("/");
                        if(Objects.equals(path[1], "driver")) {
                            carController.getCarsByDriverId(request, response);
                        } else {
                            carController.getCarById(request, response);
                        }
                    } else {
                        carController.getCars(request, response);
                    }
                    break;
                case "/orders":
                    if(pathInfo != null) {
                        orderController.getOrderById(request, response);
                    } else {
                        String role = request.getParameter("role");
                        String id = request.getParameter("id");
                        if(role!=null && id!=null) {
                            orderController.getOrdersByRole(request, response);
                        }
                        orderController.getOrders(request, response);
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
                case "/role-connections":
                    userRoleController.createConnection(request, response);
                    break;
                case "/cars":
                    carController.createCar(request, response);
                    break;
                case "/orders":
                    orderController.createOrder(request, response);
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
                case "/role-connections":
                    if(pathInfo != null) {
                        userRoleController.deleteConnectionById(request, response);
                    }
                    break;
                case "/cars":
                    if(pathInfo != null) {
                        carController.deleteCarById(request, response);
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
        System.out.println("PUT");

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
                case "/cars":
                    if(pathInfo != null) {
                        carController.updateCarById(request, response);
                    }
                case "/orders":
                    if(pathInfo != null) {
                        String [] path = pathInfo.split("/");
                        if (Objects.equals(path[1], "dispatcher")) {
                            orderController.updateByDispatcher(request, response);
                        } else if (Objects.equals(path[1], "driver")) {
                            orderController.updateByDriver(request, response);
                        } else if (Objects.equals(path[1], "feedback")) {
                            orderController.leaveFeedback(request, response);
                        }
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
        userRoleController.destroy();
        carController.destroy();
        orderController.destroy();
    }
}