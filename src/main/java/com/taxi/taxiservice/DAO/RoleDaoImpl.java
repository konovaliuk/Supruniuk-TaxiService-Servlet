package com.taxi.taxiservice.DAO;

import com.taxi.taxiservice.ConnectionPool;
import com.taxi.taxiservice.DAO.dbColumns.RoleColumnsDB;
import com.taxi.taxiservice.DAO.interfaces.IRoleDAO;
import com.taxi.taxiservice.Models.ConnectionNames;
import com.taxi.taxiservice.Models.Role;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements IRoleDAO {
    public Connection connection = null;

    public RoleDaoImpl() {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection(ConnectionNames.userRole);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private Role getRole(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(RoleColumnsDB.columnId);
        String rolename = resultSet.getString(RoleColumnsDB.columnRolename);

        return new Role(id, rolename);
    }

    public ArrayList<Role> findAll() {
        String query = "select * from roles;";
        ArrayList<Role> roles = new ArrayList<Role>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Role role = getRole(resultSet);
                roles.add(role);
            }

        } catch (Exception error) {
            error.printStackTrace();
        }

        return roles;
    }
    public Role findById(long roleID) {
        String query = "select * from roles where id=" + roleID;
        Role role = null;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                role = getRole(resultSet);
            }

        } catch (Exception error) {
            error.printStackTrace();
        }

        return role;
    }

    public Role findByRolename(String role_name) {
        String query = "select * from roles where rolename=" + "'" + role_name + "'";
        Role role = null;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                role = getRole(resultSet);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return role;
    }

    public void save(String rolename) {
        String query = "call create_role('" + rolename + "')";

        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void update(long id, String rolename) {
        String query = "call update_role(" + id + "," + "'" + rolename + "');";

        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void delete(long id) {
        String query = "call delete_role(" + id + ")";

        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException error) {
            System.err.println(error.getMessage());
        }
    }
}