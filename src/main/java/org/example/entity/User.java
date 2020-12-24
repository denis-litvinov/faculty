package org.example.entity;

import java.util.Objects;

public class User extends Entity {
    private int id;

    private String login;

    private String password;

    private String name;

    private String surname;

    private int roleId;

    public User(){

    }

    public User(String login, String password, int roleId) {
        this.login = login;
        this.password = password;
        this.roleId = roleId;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return name;
    }

    public void setFirstName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", roleId=" + roleId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return roleId == user.roleId &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, name, surname, roleId);
    }

    public static User createUser(int id, String login, String password, String name, String surname, int roleId){
        User user = new User();
        user.setId(id);
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(name);
        user.setSurname(surname);
        user.setRoleId(roleId);
        return user;
    }

    public static User createUser(String login, String password, String name, String surname, int roleId){
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(name);
        user.setSurname(surname);
        user.setRoleId(roleId);
        return user;
    }
}
