package com.taxi.taxiservice.DAO.interfaces;

import com.taxi.taxiservice.Models.DriverStatus;

import java.util.ArrayList;

public interface IDriverStatusDAO {
    ArrayList<DriverStatus> findAll();
    DriverStatus findByDriverID(long id);
    void save(long driverID, String status);
    void update(long driverID, String status);
    void delete(long id);
}
