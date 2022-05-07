package com.taxi.taxiservice.DAO;

import com.taxi.taxiservice.ConnectionPool;
import com.taxi.taxiservice.DAO.interfaces.IUsersDAO;
import com.taxi.taxiservice.Models.User.NewUser;
import com.taxi.taxiservice.Models.User.UserDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsersDaoImpl implements IUsersDAO {
    public Connection connection = null;

    public UsersDaoImpl() {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection("Users Data Source");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private UserDB getUser(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(com.taxi.taxiservice.DAO.dbColumns.UserDB.columnId);
        String name = resultSet.getString(com.taxi.taxiservice.DAO.dbColumns.UserDB.columnName);
        String surname = resultSet.getString(com.taxi.taxiservice.DAO.dbColumns.UserDB.columnSurname);
        String email = resultSet.getString(com.taxi.taxiservice.DAO.dbColumns.UserDB.columnEmail);
        String password = resultSet.getString(com.taxi.taxiservice.DAO.dbColumns.UserDB.columnPassword);
        String creation_date = resultSet.getString(com.taxi.taxiservice.DAO.dbColumns.UserDB.columnCreationDate);

        return new UserDB(id, name, surname, email, password, creation_date);
    }

    public ArrayList<UserDB> findAll() {
        String query = "select * from users";
        ArrayList<UserDB> userList = new ArrayList<UserDB>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                UserDB user = getUser(resultSet);
                userList.add(user);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return userList;
    }

    public UserDB findById(long user_id) {
        String query = "select * from users where id=" + user_id;

        UserDB user = null;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                user = getUser(resultSet);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return user;
    }

    public UserDB findByEmail(String email) {
        String query = "select * from users where email='" + email + "'";
        UserDB user = null;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                user = getUser(resultSet);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return user;
    }

    public UserDB save(NewUser user) {
        UserDB newUser = null;
        String query = "call create_user(null ,'" + user.getName() + "','" + user.getSurname() + "','" + user.getEmail() + "','" + user.getPassword() + "'," + user.getRoleId() + ")";
        System.out.println(query);
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                long ind = resultSet.getLong("ind");
                newUser = findById(ind);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return newUser;
    }

    public UserDB update(long ID, String field, String value) {
        UserDB newUser = null;
        String query = "call update_user_" + field + "(" + ID + "," + "'" + value + "');";

        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
            newUser = findById(ID);
        } catch (SQLException error) {
            error.printStackTrace();
        }

        return newUser;
    }

    public void delete(long ind) {
        String query = "call delete_user(" + ind + ")";

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
