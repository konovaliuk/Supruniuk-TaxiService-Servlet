package com.taxi.taxiservice.DAO.interfaces;

import com.taxi.taxiservice.Models.Role;

import java.util.ArrayList;
import java.util.List;

public interface IRoleDAO {
    ArrayList<Role> findAll();
    Role findById(long id);
    Role findByRolename(String rolename);
    void save(String rolename);
    void update(long id, String rolename);
    void delete(long id);
}
