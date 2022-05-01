package com.taxi.taxiservice.Models;

public class DriverStatus {
    private long driver_id;
    private String status;

    public DriverStatus(long driver_id, String status) {
        this.driver_id = driver_id;
        this.status = status;
    }

    public long getId() {
        return driver_id;
    }

    public String getDriverStatus() {
        return status;
    }

    public void setDriverStatus(String status) {
        this.status = status;
    }
}
