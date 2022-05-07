package com.taxi.taxiservice.Services;

import com.taxi.taxiservice.DAO.UsersDaoImpl;
import com.taxi.taxiservice.Models.User.NewUser;
import com.taxi.taxiservice.Models.UpdateField;
import com.taxi.taxiservice.Models.User.User;
import com.taxi.taxiservice.Models.User.UserDB;

import java.util.ArrayList;
import java.util.Iterator;

public class UserService {
    private UsersDaoImpl usersDao;

    public UserService() {
        usersDao = new UsersDaoImpl();
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

        System.out.println(users);
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
        usersDao.delete(id);
    }

    public void closeConnection() {
        usersDao.closeConnection();
    }
}
