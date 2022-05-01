package com.taxi.taxiservice.Models;

public class UpdateUser {
    private long id;
    private String field;
    private String value;

    public UpdateUser(long id, String field, String value) {
        this.id = id;
        this.field = field;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public String getField() {
        return field;
    }

    public String getValue() {
        return value;
    }
}
