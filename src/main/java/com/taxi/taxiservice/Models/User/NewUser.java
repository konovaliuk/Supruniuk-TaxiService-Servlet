package com.taxi.taxiservice.Models.User;

public class NewUser {
    private String name;
    private String surname;
    private String email;
    private String password;
    private long roleId;

    public NewUser(String name, String surname,
                   String email, String password, long roleId) {
        this.roleId = roleId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public boolean checkValid() {
        if (this.getEmail() != null && this.getName() != null && this.getRoleId() != 0
                && this.getPassword() != null && this.getSurname() != null) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password_hash) {
        this.password = password_hash;
    }

}
