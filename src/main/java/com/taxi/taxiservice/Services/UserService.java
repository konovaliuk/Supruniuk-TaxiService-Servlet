package com.taxi.taxiservice.Services;

import com.taxi.taxiservice.DAO.*;
import com.taxi.taxiservice.Models.Role;
import com.taxi.taxiservice.Models.User.NewUser;
import com.taxi.taxiservice.Models.UpdateField;
import com.taxi.taxiservice.Models.User.User;
import com.taxi.taxiservice.Models.User.UserDB;
import com.taxi.taxiservice.Models.User.UserRole;

import java.util.ArrayList;
import java.util.Iterator;

public class UserService {
    private UsersDaoImpl usersDao;
    private RoleDaoImpl roleDao;
    private UserRoleDaoImpl userRoleDao;
    private CarDaoImpl carDao;
    private DriverStatusDaoImpl driverStatusDao;
    private OrderDaoImpl orderDao;

    public UserService() {
        usersDao = new UsersDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
        roleDao = new RoleDaoImpl();
        carDao = new CarDaoImpl();
    }

    private User getUser(UserDB user) {
        long id = user.getId();
        String name = user.getName();
        String surname = user.getSurname();
        String email = user.getEmail();

        return new User(id, name, surname, email);
    }

    public ArrayList<User> getAll() {
        ArrayList<UserDB> usersDB = usersDao.findAll();
        ArrayList<User> users = new ArrayList<>();

        Iterator iter = usersDB.iterator();
        while (iter.hasNext()) {
            User user = getUser((UserDB) iter.next());
            users.add(user);
        }

        return users;
    }

    public User getById(Long id) {
        UserDB user = usersDao.findById(id);
        if (user == null) {
            return null;
        }
        return getUser(user);
    }

    public User getByEmail(String email) {
        UserDB user = usersDao.findByEmail(email);
        if (user == null) {
            return null;
        }
        return getUser(user);
    }

    public User create(NewUser newUser) {
        UserDB user = usersDao.save(newUser);
        return getUser(user);
    }

    public void update(Long id, UpdateField updatedUser) {
        usersDao.update(id, updatedUser.getField(), updatedUser.getValue());
    }

    public void delete(Long id) {
        Role role = roleDao.findByRolename("driver");
        UserRole connection = userRoleDao.checkRole(id, role.getId());
        if (connection != null) {
            carDao.deleteByDriver(id);
            driverStatusDao.delete(id);
        }
        orderDao.deleteByUser(id);
        usersDao.delete(id);
    }

    public void closeConnection() {
        usersDao.closeConnection();
    }
}
