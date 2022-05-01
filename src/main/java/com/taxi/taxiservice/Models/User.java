package com.taxi.taxiservice.Models;

public class User {
    private long id;
    private String name;
    private String surname;
    private String email;
    private String password_hash;
    private String created_on;

    public User(long id, String name, String surname,
                String email, String password_hash, String created_on) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password_hash = password_hash;
        this.created_on = created_on;
    }

    public long getId() {
        return id;
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

    public String getFullName() {
        return name + " " + surname;
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

    public String getCreatedOn() {
        return created_on;
    }
}
