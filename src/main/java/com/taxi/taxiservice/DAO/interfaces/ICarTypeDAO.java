package com.taxi.taxiservice.DAO.interfaces;

import com.taxi.taxiservice.Models.CarType;

import java.util.ArrayList;

public interface ICarTypeDAO {
    ArrayList<CarType> findAll();
    CarType findByID(long id);
    CarType findByTypename(String typename);
    void save(String typename, String description);
    void update(long id, String typename, String description);
    void delete(long id);
}
