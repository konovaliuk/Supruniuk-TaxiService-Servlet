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
            MessageController.internal(res);
        }
    }

    public void getRoleById(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getPathInfo().substring(1);

        try {
            long roleId = Long.parseLong(id);
            Role role = roleDAO.findById(roleId);
            if(role == null) {
                MessageController.badRequest(res,"Role not found");
            } else {
                String json = new Gson().toJson(role);
                res.getWriter().write(json);
            }
            res.getWriter().flush();
        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void getByRolename(HttpServletRequest req, HttpServletResponse res) {
        String rolename = req.getParameter("role");

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
            MessageController.internal(res);
        }
    }

    public void createRole(HttpServletRequest req, HttpServletResponse res) {
        try {
            String requestData = req.getReader().lines().collect(Collectors.joining());
            Role newRole = gson.fromJson(requestData, Role.class);
            if(roleDAO.findByRolename((newRole.getRolename())) == null) {
                if(newRole.checkValid()){
                    roleDAO.save(newRole.getRolename());
                    MessageController.sendResponseMessage(res,"Role successfully created");
                } else {
                    MessageController.badRequest(res, "Validation failed");
                }
            } else {
                MessageController.badRequest(res, "Role already exists");
            }
        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void deleteRoleById(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getPathInfo().substring(1);

        try {
            long roleId = Long.parseLong(id);
            if(roleDAO.findById(roleId) != null) {
                roleDAO.delete(roleId);
                MessageController.sendResponseMessage(res,"Role deleted successfully");
            } else {
                MessageController.badRequest(res, "Role doesn`t exist");
            }
            res.getWriter().flush();
        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void updateRoleById(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getPathInfo().substring(1);

        try {
            String requestData = req.getReader().lines().collect(Collectors.joining());
            Role updatedRole = gson.fromJson(requestData, Role.class);
            if(updatedRole.checkValid()) {
                long roleId = Long.parseLong(id);
                if(roleDAO.findById(roleId) != null) {
                    roleDAO.update(roleId, updatedRole.getRolename());
                    MessageController.sendResponseMessage(res,"Role updated successfully");
                } else {
                    MessageController.badRequest(res, "Role doesn`t exist");
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
        roleDAO.closeConnection();
    }
}
