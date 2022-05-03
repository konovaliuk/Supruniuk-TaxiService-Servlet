package com.taxi.taxiservice.Models;

public class CarType {
    private long id;
    private String typename;
    private String description;

    public CarType(long id, String typename, String description) {
        this.id = id;
        this.typename = typename;
        this.description = description;
    }

    public boolean checkValid() {
        if(this.getTypename() != null && this.getDescription() != null) {
            return true;
        } else {
            return false;
        }
    }

    public CarType(String typename, String description) {
        this.typename = typename;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
