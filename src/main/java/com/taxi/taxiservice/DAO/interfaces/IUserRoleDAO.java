package com.taxi.taxiservice.DAO.interfaces;

import com.taxi.taxiservice.Models.User.User;
import com.taxi.taxiservice.Models.User.UserRole;

import java.util.ArrayList;

public interface IUserRoleDAO {
    ArrayList<UserRole> findAll();

    UserRole findConnectionById(long id);

    UserRole checkRole(long user_id, long role_id);

    ArrayList<User> findConnectionByRole(long roleID);

    ArrayList<String> findUserRoles(long userID);

    void save(long userID, long roleID);

    void delete(long id);
}
