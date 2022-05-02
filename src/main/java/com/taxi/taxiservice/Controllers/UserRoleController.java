package com.taxi.taxiservice.Controllers;

import com.google.gson.Gson;
import com.taxi.taxiservice.DAO.UserRoleDaoImpl;

import com.taxi.taxiservice.Models.UserRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class UserRoleController {
    private UserRoleDaoImpl userRoleDao;

    Gson gson = new Gson();
    public UserRoleController() {
        userRoleDao = new UserRoleDaoImpl();
    }

    public void getRoleConnections(HttpServletRequest req, HttpServletResponse res) {
        try {
            ArrayList<UserRole> connection = userRoleDao.findAll();
            String json = gson.toJson(connection);
            res.getWriter().write(json);
            res.getWriter().flush();

        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public void getConnectionById(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getPathInfo().substring(1);

        try {
            Long connectionId = Long.parseLong(id);
            UserRole userRole = userRoleDao.findConnectionById(connectionId);
            if(userRole == null) {
                MessageController.badRequest(res,"Connection not found");
            } else {
                String json = new Gson().toJson(userRole);
                res.getWriter().write(json);
            }
            res.getWriter().flush();
        } catch (Exception err) {
            System.out.println("Server error");
        }
    }

    public void getUserRoles(HttpServletRequest req, HttpServletResponse res) {
        String [] path = req.getPathInfo().split("/");

        try {
            long userId = Long.parseLong(path[2]);
            ArrayList<UserRole> userRoles = userRoleDao.findUserRoles(userId);
            if(userRoles.size() == 0) {
                MessageController.badRequest(res,"Roles not found");
            } else {
                String json = new Gson().toJson(userRoles);
                res.getWriter().write(json);
            }
            res.getWriter().flush();
        } catch (Exception err) {
            System.out.println("Server error");
        }
    }

    public void getUsersByRole(HttpServletRequest req, HttpServletResponse res) {
        String [] path = req.getPathInfo().split("/");

        try {
            long roleId = Long.parseLong(path[2]);
            ArrayList<UserRole> userRoles = userRoleDao.findConnectionByRole(roleId);
            if(userRoles.size() == 0) {
                MessageController.badRequest(res,"Users not found");
            } else {
                String json = new Gson().toJson(userRoles);
                res.getWriter().write(json);
            }
            res.getWriter().flush();
        } catch (Exception err) {
            System.out.println("Server error");
        }
    }

    public void createConnection(HttpServletRequest req, HttpServletResponse res) {
        try {
            String requestData = req.getReader().lines().collect(Collectors.joining());
            UserRole connection = gson.fromJson(requestData, UserRole.class);
            if(userRoleDao.checkRole(connection.getUserID(), connection.getRoleID()) == null && connection.getUserID() != 0 && connection.getRoleID() != 0) {
                userRoleDao.save(connection.getUserID(), connection.getRoleID());
                MessageController.sendResponseMessage(res,"Connection successfully created");
            } else {
                MessageController.badRequest(res, "Connection already exists");
            }
        } catch (Exception err) {
            System.out.println("Server error");
        }
    }

    public void deleteConnectionById(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getPathInfo().substring(1);

        try {
            long connectionId = Long.parseLong(id);
            if(userRoleDao.findConnectionById(connectionId) != null) {
                userRoleDao.delete(connectionId);
                MessageController.sendResponseMessage(res,"Connection deleted successfully");
            } else {
                MessageController.badRequest(res, "Connection doesn`t exist");
            }
            res.getWriter().flush();
        } catch (Exception err) {
            System.out.println("Server error");
        }
    }

    public void destroy() {
        userRoleDao.closeConnection();
    }
}
