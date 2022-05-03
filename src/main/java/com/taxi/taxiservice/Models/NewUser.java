package com.taxi.taxiservice.Models;

public class NewUser {
    private String name;
    private String surname;
    private String email;
    private String password_hash;
    private long roleId;

    public NewUser(String name, String surname,
                String email, String password_hash, long roleId) {
        this.roleId = roleId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password_hash = password_hash;
    }

    public boolean checkValid() {
        if(this.getEmail() != null && this.getName() != null && this.getRoleId() != 0
            && this.getPasswordHash() != null && this.getSurname() != null) {
            return true;
        }
        return false;
    }

    public long getRoleId() {
        return roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return password_hash;
    }

    public void setPasswordHash(String password_hash) {
        this.password_hash = password_hash;
    }

}
