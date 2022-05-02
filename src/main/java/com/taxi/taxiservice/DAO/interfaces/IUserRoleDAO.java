package com.taxi.taxiservice.DAO.interfaces;

import com.taxi.taxiservice.Models.UserRole;

import java.util.ArrayList;

public interface IUserRoleDAO {
    ArrayList<UserRole> findAll();
    UserRole findConnectionById(long id);
    UserRole checkRole(long user_id, long role_id);
    ArrayList<UserRole> findConnectionByRole(long roleID);
    ArrayList<UserRole> findUserRoles(long userID);
    void save(long userID, long roleID);
    void delete(long id);
}
