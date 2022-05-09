package com.taxi.taxiservice.DAO;

import com.taxi.taxiservice.ConnectionPool;
import com.taxi.taxiservice.DAO.dbColumns.RoleColumnsDB;
import com.taxi.taxiservice.DAO.dbColumns.UserColumnsDB;
import com.taxi.taxiservice.DAO.dbColumns.UserRoleColumnsDB;
import com.taxi.taxiservice.DAO.interfaces.IUserRoleDAO;
import com.taxi.taxiservice.Models.ConnectionNames;
import com.taxi.taxiservice.Models.User.User;
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
            connection = connectionPool.getConnection(ConnectionNames.userRole);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private UserRole getUserRole(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(UserRoleColumnsDB.columnId);
        long userID = resultSet.getLong(UserRoleColumnsDB.columnUserId);
        long roleID = resultSet.getLong(UserRoleColumnsDB.columnRoleId);

        return new UserRole(id, userID, roleID);
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(UserRoleColumnsDB.columnId);
        String name = resultSet.getString(UserColumnsDB.columnName);
        String surname = resultSet.getString(UserColumnsDB.columnSurname);
        String email = resultSet.getString(UserColumnsDB.columnEmail);

        return new User(id, name, surname, email);
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

    public ArrayList<User> findConnectionByRole(long role_id) {
        String query = "select  users.id, users.name,  users.surname,  users.email from user_role \n" +
                "JOIN users ON users.id=user_role.user_id\n" +
                "where role_id=" + role_id;

        ArrayList<User> users = new ArrayList<>();
        User user = null;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                user = getUser(resultSet);
                users.add(user);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return users;
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

    public ArrayList<String> findUserRoles(long user_id) {
        String query = "select  roles.rolename from user_role \n" +
                "JOIN roles ON roles.id=user_role.role_id\n" +
                "where user_id=" + user_id;

        ArrayList<String> roles = new ArrayList<>();
        String role = null;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                role = resultSet.getString(RoleColumnsDB.columnRolename);
                roles.add(role);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return roles;
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
