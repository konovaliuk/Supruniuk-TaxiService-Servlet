package com.taxi.taxiservice.Models.User;

public class UserRole {
    private final long id;
    private final long user_id;
    private final long role_id;

    public UserRole(long id, long userId, long roleId) {
        this.id = id;
        this.user_id = userId;
        this.role_id = roleId;
    }

    public long getId() {
        return id;
    }

    public long getUserID() {
        return user_id;
    }

    public long getRoleID() {
        return role_id;
    }
}
