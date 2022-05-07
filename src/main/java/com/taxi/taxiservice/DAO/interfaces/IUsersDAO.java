package com.taxi.taxiservice.DAO.interfaces;

import com.taxi.taxiservice.Models.User.NewUser;
import com.taxi.taxiservice.Models.User.UserDB;

import java.util.ArrayList;

public interface IUsersDAO {
    ArrayList<UserDB> findAll();

    UserDB findById(long id);

    UserDB save(NewUser user);

    UserDB update(long id, String field, String value);

    void delete(long id);
}
