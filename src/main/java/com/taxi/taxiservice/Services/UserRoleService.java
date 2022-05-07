package com.taxi.taxiservice.Services;

import com.taxi.taxiservice.DAO.UserRoleDaoImpl;
import com.taxi.taxiservice.Models.User.User;
import com.taxi.taxiservice.Models.User.UserRole;

import java.util.ArrayList;

public class UserRoleService {
    private UserRoleDaoImpl userRoleDao;

    public UserRoleService() {
        userRoleDao = new UserRoleDaoImpl();
    }

    public ArrayList<UserRole> getAll() {
        ArrayList<UserRole> connections = userRoleDao.findAll();
        return connections;
    }

    public UserRole getById(Long id) {
        UserRole connection = userRoleDao.findConnectionById(id);
        return connection;
    }

    public ArrayList<String> getByUser(long userId) {
        ArrayList<String> userRoles = userRoleDao.findUserRoles(userId);
        return userRoles;
    }

    public ArrayList<User> getByRole(long roleId) {
        ArrayList<User> users = userRoleDao.findConnectionByRole(roleId);
        return users;
    }

    public boolean checkRole(long userId, long roleId) {
        UserRole check = userRoleDao.checkRole(userId, roleId);
        if (check == null) {
            return true;
        }
        return false;
    }

    public void save(UserRole connection) {
        userRoleDao.save(connection.getUserID(), connection.getRoleID());
    }

    public void delete(long id) {
        userRoleDao.delete(id);
    }

    public void closeConnection() {
        userRoleDao.closeConnection();
    }
}
