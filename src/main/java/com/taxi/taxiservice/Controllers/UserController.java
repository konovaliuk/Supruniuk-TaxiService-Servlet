package com.taxi.taxiservice.Controllers;

import com.google.gson.Gson;
import com.taxi.taxiservice.DAO.UsersDaoImpl;
import com.taxi.taxiservice.Models.NewUser;
import com.taxi.taxiservice.Models.UpdateField;
import com.taxi.taxiservice.Models.User.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class UserController {
    private UsersDaoImpl usersDao;

    Gson gson = new Gson();

    public UserController() {
        usersDao = new UsersDaoImpl();
    }

    public void getUsers(HttpServletRequest req, HttpServletResponse res) {
        try {
            ArrayList<User> users = usersDao.findAll();
            String json = new Gson().toJson(users);
            res.getWriter().write(json);
            res.getWriter().flush();

        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void getUserById(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getPathInfo().substring(1);

        try {
            Long userId = Long.parseLong(id);
            User user = usersDao.findById(userId);
            if(user == null) {
                MessageController.badRequest(res,"User not found");
            } else {
                String json = new Gson().toJson(user);
                res.getWriter().write(json);
            }
            res.getWriter().flush();
        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void createUser(HttpServletRequest req, HttpServletResponse res) {
        try {
            String requestData = req.getReader().lines().collect(Collectors.joining());
            NewUser newUser = gson.fromJson(requestData, NewUser.class);
            User checkExist = usersDao.findByEmail(newUser.getEmail());
            if(checkExist == null) {
                if(newUser.checkValid()) {
                    User user = usersDao.save(newUser, newUser.getRoleId());
                    String json = new Gson().toJson(user);
                    res.getWriter().write(json);
                } else {
                    MessageController.badRequest(res, "Validation failed");
                }
            } else {
                MessageController.badRequest(res, "User with this email already exists");
            }
        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void deleteUserById(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getPathInfo().substring(1);

        try {
            long userId = Long.parseLong(id);
            if(usersDao.findById(userId) != null) {
                usersDao.delete(userId);
                MessageController.sendResponseMessage(res,"User deleted successfully");
            } else {
                MessageController.badRequest(res, "User doesn`t exist");
            }
            res.getWriter().flush();
        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void updateUserById(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getPathInfo().substring(1);

        try {
            String requestData = req.getReader().lines().collect(Collectors.joining());
            UpdateField updatedUser = gson.fromJson(requestData, UpdateField.class);
            if(updatedUser.checkValid()) {
                long userId = Long.parseLong(id);
                if(usersDao.findById(userId) != null) {
                    usersDao.update(userId, updatedUser.getField(), updatedUser.getValue());
                    MessageController.sendResponseMessage(res,"User updated successfully");
                } else {
                    MessageController.badRequest(res, "User doesn`t exist");
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
        usersDao.closeConnection();
    }
}
