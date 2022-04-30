package com.taxi.taxiservice.Controllers;
import com.google.gson.Gson;
import com.taxi.taxiservice.DAO.RoleDaoImpl;
import com.taxi.taxiservice.Models.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class RoleController {
    private RoleDaoImpl roleDAO;
    Gson gson = new Gson();
    public RoleController() {
        roleDAO = new RoleDaoImpl();
    }

    public void getRoles(HttpServletRequest req, HttpServletResponse res) {
        try {
            ArrayList<Role> roles = roleDAO.findAll();
            //String r = roles.stream().map(role -> role.getRolename()).collect(Collectors.joining(", "));
            String json = new Gson().toJson(roles);
            res.getWriter().write(json);
            res.getWriter().flush();

        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public void getRoleById(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getPathInfo().substring(1);

        try {
            Long roleId = Long.parseLong(id);
            Role role = roleDAO.findById(roleId);
            if(role == null) {
                MessageController.badRequest(res,"Role not found");
            } else {
                String json = new Gson().toJson(role);
                res.getWriter().write(json);
            }
            res.getWriter().flush();
        } catch (Exception err) {
            System.out.println("Server error");
        }
    }

    public void getByRolename(HttpServletRequest req, HttpServletResponse res) {
        String [] path = req.getPathInfo().split("/");
        String rolename = path[2];

        try {
            Role role = roleDAO.findByRolename(rolename);
            if(role == null) {
                MessageController.badRequest(res,"Role not found");
            } else {
                String json = new Gson().toJson(role);
                res.getWriter().write(json);
            }
            res.getWriter().flush();
        } catch (Exception err) {
            System.out.println("Server error");
        }
    }

    public void createRole(HttpServletRequest req, HttpServletResponse res) {
        try {
            String requestData = req.getReader().lines().collect(Collectors.joining());
            Role newRole = gson.fromJson(requestData, Role.class);
            if(roleDAO.findByRolename((newRole.getRolename())) == null && newRole.getRolename() != null) {
                roleDAO.save(newRole.getRolename());
                MessageController.sendResponseMessage(res,"Role successfully created");
            } else {
                MessageController.badRequest(res, "Role already exist");
            }
        } catch (Exception err) {
            System.out.println("Server error");
        }
    }

    public void deleteRoleById(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getPathInfo().substring(1);

        try {
            Long roleId = Long.parseLong(id);
            if(roleDAO.findById(roleId) != null) {
                roleDAO.delete(roleId);
                MessageController.sendResponseMessage(res,"Role deleted successfully");
            } else {
                MessageController.badRequest(res, "Role doesn`t exist");
            }
            res.getWriter().flush();
        } catch (Exception err) {
            System.out.println("Server error");
        }
    }

    public void updateRoleById(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getPathInfo().substring(1);

        try {
            String requestData = req.getReader().lines().collect(Collectors.joining());
            Role updatedRole = gson.fromJson(requestData, Role.class);
            if(updatedRole.getRolename() != null) {
                Long roleId = Long.parseLong(id);
                if(roleDAO.findById(roleId) != null) {
                    roleDAO.update(roleId, updatedRole.getRolename());
                    MessageController.sendResponseMessage(res,"Role updated successfully");
                } else {
                    MessageController.badRequest(res, "Role doesn`t exist");
                }
            } else {
                MessageController.badRequest(res, "Invalid field");
            }
            res.getWriter().flush();
        } catch (Exception err) {
            System.out.println("Server error");
        }}

    public void destroy() {
        roleDAO.closeConnection();
    }
}
