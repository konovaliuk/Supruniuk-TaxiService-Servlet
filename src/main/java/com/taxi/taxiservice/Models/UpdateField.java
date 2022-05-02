package com.taxi.taxiservice.Models;

public class UpdateField {
    private String field;
    private String value;

    public UpdateField(String field, String value) {
        this.field = field;
        this.value = value;
    }

    public boolean checkValid() {
        if(this.getField() != null && this.getValue() != null) {
            return true;
        }
        return false;
    }

    public String getField() {
        return field;
    }

    public String getValue() {
        return value;
    }
}
