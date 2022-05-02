package com.taxi.taxiservice.Models.Car;

public class Car {
    private long id;
    private long driver_id;
    private String license_number;
    private String model;
    private String color;
    private long type_id;
    private String creation_date;


    public Car(long id, long driver_id, String license_number,
               String model, String color, long type_id,
               String creation_date) {
        this.id = id;
        this.driver_id = driver_id;
        this.license_number = license_number;
        this.model = model;
        this.color = color;
        this.type_id = type_id;
        this.creation_date = creation_date;
    }

    public Car(long driver_id, String license_number,
               String model, String color, long type_id) {
        this.driver_id = driver_id;
        this.license_number = license_number;
        this.model = model;
        this.color = color;
        this.type_id = type_id;
    }

    public long getId() {
        return id;
    }

    public long getDriverID() {
        return driver_id;
    }

    public void setDriverID(long driver_id) {
        this.driver_id = driver_id;
    }

    public String getLicenseNumber() {
        return license_number;
    }

    public void setLicenseNumber(String license_number) {
        this.license_number = license_number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getTypeID() {
        return type_id;
    }

    public void setColor(long type_id) {
        this.type_id = type_id;
    }

    public String getCreationDate() {
        return creation_date;
    }
}
