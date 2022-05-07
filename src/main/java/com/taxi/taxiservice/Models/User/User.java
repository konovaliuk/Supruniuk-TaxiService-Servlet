package com.taxi.taxiservice.Models.User;

public class User {
    private long id;
    private String name;
    private String surname;
    private String email;

    public User(long id, String name, String surname,
                String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }
}
