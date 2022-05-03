package com.taxi.taxiservice.DAO.interfaces;

import com.taxi.taxiservice.Models.NewUser;
import com.taxi.taxiservice.Models.User.User;

import java.util.ArrayList;

public interface IUsersDAO {
    ArrayList<User> findAll();
    User findById(long id);
    User save(NewUser user, long roleID);
    User update(long id, String field, String value);
    void delete(long id);
}
