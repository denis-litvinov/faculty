package org.example.entity;

public enum Role {
    STUDENT, TEACHER, ADMIN;

    public static Role getRole(User user) {
        int roleId = user.getRoleId();
        return Role.values()[roleId];
    }

    public String getName(){
        return name().toLowerCase();
    }
}
