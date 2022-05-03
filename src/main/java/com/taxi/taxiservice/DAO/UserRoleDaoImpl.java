package com.taxi.taxiservice.DAO;

import com.taxi.taxiservice.ConnectionPool;
import com.taxi.taxiservice.DAO.dbColumns.UserRoleDB;
import com.taxi.taxiservice.DAO.interfaces.IUserRoleDAO;
import com.taxi.taxiservice.Models.User.UserRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserRoleDaoImpl implements IUserRoleDAO {
    public Connection connection = null;

    public UserRoleDaoImpl() {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection("Role Connection Data Source");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private UserRole getUserRole(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(UserRoleDB.columnId);
        long userID = resultSet.getLong(UserRoleDB.columnUserId);
        long roleID = resultSet.getLong(UserRoleDB.columnRoleId);

        return new UserRole(id, userID, roleID);
    }

    public ArrayList<UserRole> findAll() {
        String query = "select * from user_role;";
        ArrayList<UserRole> userRoleList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                UserRole userRole = getUserRole(resultSet);
                userRoleList.add(userRole);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return userRoleList;
    }

    public UserRole findConnectionById(long connection_id) {
        String query = "select * from user_role where id=" + connection_id;
        UserRole userRole = null;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                userRole = getUserRole(resultSet);
            }

        } catch (Exception error) {
            error.printStackTrace();
        }

        return userRole;
    }

    public  ArrayList<UserRole> findConnectionByRole(long role_id) {
        String query = "select * from user_role where role_id=" + role_id;
        ArrayList<UserRole> userRoleList = new ArrayList<>();
        UserRole userRole = null;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                userRole = getUserRole(resultSet);
                userRoleList.add(userRole);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return userRoleList;
    }

    public UserRole checkRole(long user_id, long role_id) {
        String query = "select * from user_role where user_id=" + user_id + " and role_id=" + role_id;
        UserRole userRole = null;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                userRole = getUserRole(resultSet);
            }

        } catch (Exception error) {
            error.printStackTrace();
        }

        return userRole;
    }
    public ArrayList<UserRole> findUserRoles(long user_id) {
        String query = "select * from user_role where user_id=" + user_id;
        ArrayList<UserRole> userRoleList = new ArrayList<>();
        UserRole userRole = null;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                userRole = getUserRole(resultSet);
                userRoleList.add(userRole);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return userRoleList;
    }

    public void save(long user_id, long role_id) {
        String query = "call connect_user_role(" + user_id + "," + role_id + ")";

        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void delete(long id) {
        String query = "call delete_user_role_connection(" + id + ")";

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
