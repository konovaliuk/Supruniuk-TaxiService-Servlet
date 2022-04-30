package com.taxi.taxiservice.Models;


public class Role {
    private long id;
    private String rolename;

    public Role(long id, String rolename) {
        this.id = id;
        this.rolename = rolename;
    }

    public Role(String rolename) {
        this.rolename = rolename;
    }

    public long getId() {
        return id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String name) {
        this.rolename = rolename;
    }

}