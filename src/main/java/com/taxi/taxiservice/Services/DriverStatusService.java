package com.taxi.taxiservice.Services;

import com.taxi.taxiservice.DAO.DriverStatusDaoImpl;
import com.taxi.taxiservice.DAO.RoleDaoImpl;
import com.taxi.taxiservice.DAO.UserRoleDaoImpl;
import com.taxi.taxiservice.Models.DriverStatus;
import com.taxi.taxiservice.Models.Role;
import com.taxi.taxiservice.Models.User.UserRole;

import java.util.ArrayList;

public class DriverStatusService {
    private DriverStatusDaoImpl driverStatusDao;
    private RoleDaoImpl roleDao;
    private UserRoleDaoImpl userRoleDao;

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
        Role role = roleDao.findByRolename("driver");
        UserRole connection = userRoleDao.checkRole(driverStatus.getId(), role.getId());
        if (connection != null) {
            driverStatusDao.save(driverStatus.getId(), driverStatus.getDriverStatus());
        }
    }

    public void update(long driverId, String status) {
        Role role = roleDao.findByRolename("driver");
        UserRole connection = userRoleDao.checkRole(driverId, role.getId());
        if (connection != null) {
            driverStatusDao.update(driverId, status);
        }
    }

    public void closeConnection() {
        driverStatusDao.closeConnection();
    }

}
