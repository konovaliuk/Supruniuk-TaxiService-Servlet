package com.taxi.taxiservice.Services;

import com.taxi.taxiservice.DAO.RoleDaoImpl;
import com.taxi.taxiservice.Models.Role;

import java.util.ArrayList;

public class RoleService {
    private RoleDaoImpl roleDAO;

    public RoleService() {
        roleDAO = new RoleDaoImpl();
    }

    public ArrayList<Role> getAll() {
        ArrayList<Role> roles = roleDAO.findAll();
        return roles;
    }

    public Role getById(Long roleId) {
        Role role = roleDAO.findById(roleId);
        return role;
    }

    public Role getByRolename(String rolename) {
        Role role = roleDAO.findByRolename(rolename);
        return role;
    }

    public void create(Role role) {
        roleDAO.save(role.getRolename());
    }

    public void delete(Long id) {
        roleDAO.delete(id);
    }

    public void update(Long id, Role role) {
        roleDAO.update(id, role.getRolename());
    }

    public void closeConnection() {
        roleDAO.closeConnection();
    }
}
