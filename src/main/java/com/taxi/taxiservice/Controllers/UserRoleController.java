package com.taxi.taxiservice.Controllers;

import com.google.gson.Gson;

import com.taxi.taxiservice.Models.User.User;
import com.taxi.taxiservice.Models.User.UserRole;
import com.taxi.taxiservice.Services.UserRoleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class UserRoleController {
    private UserRoleService userRoleService;
    Gson gson = new Gson();

    public UserRoleController() {
        userRoleService = new UserRoleService();
    }

    public void getRoleConnections(HttpServletRequest req, HttpServletResponse res) {
        try {
            ArrayList<UserRole> connection = userRoleService.getAll();
            String json = gson.toJson(connection);
            res.getWriter().write(json);
            res.getWriter().flush();

        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void getConnectionById(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getPathInfo().substring(1);

        try {
            Long connectionId = Long.parseLong(id);
            UserRole userRole = userRoleService.getById(connectionId);
            if (userRole == null) {
                MessageController.badRequest(res, "Connection not found");
            } else {
                String json = new Gson().toJson(userRole);
                res.getWriter().write(json);
            }
            res.getWriter().flush();
        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void getUserRoles(HttpServletRequest req, HttpServletResponse res) {
        String[] path = req.getPathInfo().split("/");

        try {
            long userId = Long.parseLong(path[2]);
            ArrayList<String> users = userRoleService.getByUser(userId);
            if (users.size() == 0) {
                MessageController.badRequest(res, "Roles not found");
            } else {
                String json = new Gson().toJson(users);
                res.getWriter().write(json);
            }
            res.getWriter().flush();
        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void getUsersByRole(HttpServletRequest req, HttpServletResponse res) {
        String[] path = req.getPathInfo().split("/");

        try {
            long roleId = Long.parseLong(path[2]);
            ArrayList<User> userRoles = userRoleService.getByRole(roleId);
            if (userRoles.size() == 0) {
                MessageController.badRequest(res, "Users not found");
            } else {
                String json = new Gson().toJson(userRoles);
                res.getWriter().write(json);
            }
            res.getWriter().flush();
        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void createConnection(HttpServletRequest req, HttpServletResponse res) {
        try {
            String requestData = req.getReader().lines().collect(Collectors.joining());
            UserRole connection = gson.fromJson(requestData, UserRole.class);
            if (connection.getUserID() != 0 && connection.getRoleID() != 0) {
                if (userRoleService.checkRole(connection.getUserID(), connection.getRoleID())) {
                    userRoleService.save(connection);
                    MessageController.sendResponseMessage(res, "Connection successfully created");
                } else {
                    MessageController.badRequest(res, "Connection already exists");
                }
            } else {
                MessageController.badRequest(res, "Validation failed");
            }
        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void deleteConnectionById(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getPathInfo().substring(1);

        try {
            long connectionId = Long.parseLong(id);
            if (userRoleService.getById(connectionId) != null) {
                userRoleService.delete(connectionId);
                MessageController.sendResponseMessage(res, "Connection deleted successfully");
            } else {
                MessageController.badRequest(res, "Connection doesn`t exist");
            }
            res.getWriter().flush();
        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void destroy() {
        userRoleService.closeConnection();
    }
}
