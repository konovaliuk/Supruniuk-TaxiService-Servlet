package com.taxi.taxiservice.Models.User;

public class UserRole {
    private final long id;
    private final long userId;
    private final long roleId;

    public UserRole(long id, long userId, long roleId) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
    }

    public long getId() {
        return id;
    }

    public long getUserID() {
        return userId;
    }

    public long getRoleID() {
        return roleId;
    }
}
