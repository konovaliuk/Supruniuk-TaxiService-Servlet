package com.taxi.taxiservice.Services;

import com.taxi.taxiservice.DAO.DriverStatusDaoImpl;
import com.taxi.taxiservice.Models.DriverStatus;

import java.util.ArrayList;

public class DriverStatusService {
    private DriverStatusDaoImpl driverStatusDao;

    public DriverStatusService() {
        driverStatusDao = new DriverStatusDaoImpl();
    }

    public ArrayList<DriverStatus> getAll() {
        ArrayList<DriverStatus> driverStatuses = driverStatusDao.findAll();
        return driverStatuses;
    }

    public DriverStatus getById(long id) {
        DriverStatus driverStatus = driverStatusDao.findByDriverID(id);
        return driverStatus;
    }

    public DriverStatus getByDriver(long id) {
        DriverStatus driverStatus = driverStatusDao.findByDriverID(id);
        return driverStatus;
    }

    public void setStatus(DriverStatus driverStatus) {
        driverStatusDao.save(driverStatus.getId(), driverStatus.getDriverStatus());
    }

    public void update(long driverId, String status) {
        driverStatusDao.update(driverId, status);
    }

    public void closeConnection() {
        driverStatusDao.closeConnection();
    }

}
