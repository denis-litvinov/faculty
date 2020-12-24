package org.example.dao;


import org.example.db.DBManager;
import org.example.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private static String registerUser = "INSERT INTO users (login, password, name, surname, roles_id) VALUES (?, ?, ?, ?, ?);";
    private static String equality = "SELECT id, login, password, roles_id from users;";
    private static String addCourse = "INSERT INTO users_has_courses (users_id, courses_id) VALUES (?, ?)";
    private static String getTeacherList = "SELECT * FROM users WHERE roles_id = 2";
    private static String getListOfStudents = "select id, login, password, name, surname, roles_id from users_has_courses left join users on users_id = id where users_has_courses.courses_id =";
    private static String getListOfTeachers = "select id, login, password, name, surname, roles_id from users_has_courses left join users on users_id = id where users.roles_id=";
    private static String getTeacherBySurname = "SELECT * FROM users WHERE surname=";
    private static String getListOfAllStudents = "SELECT * FROM users WHERE roles_id = 1;";
    private static String getListOfStudentHasCourses = "select id, login, password, name, surname, roles_id from users_has_courses left join users on users_id = id where roles_id = 1;";

    public void registerUser(User user) {
        try (Connection con = DBManager.getInstance().getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(registerUser, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, user.getLogin());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getFirstName());
                statement.setString(4, user.getSurname());
                statement.setInt(5, user.getRoleId());
                statement.executeUpdate();
                try (ResultSet rs = statement.getGeneratedKeys()) {
                    if (rs.next()) {
                        user.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getListofLoginsAndPasswords() {
        List<User> list = new ArrayList<>();
        try (Connection con = DBManager.getInstance().getConnection()) {
            try (Statement statement = con.createStatement()) {
                try (ResultSet rs = statement.executeQuery(equality)) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String login = rs.getString("login");
                        String password = rs.getString("password");
                        int roles_id = rs.getInt("roles_id");
                        User user = User.createUser(id, login, password, null, null, roles_id);
                        list.add(user);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void addCourse(int idUser, int idCourse) {
        try (Connection con = DBManager.getInstance().getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(addCourse)) {
                statement.setInt(1, idUser);
                statement.setInt(2, idCourse);
                statement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public List<User> getTeachersList() {
        UserMapper mapper = new UserMapper();
        List<User> list = new ArrayList<>();
        try (Connection con = DBManager.getInstance().getConnection()) {
            try (Statement statement = con.createStatement()) {
                try (ResultSet rs = statement.executeQuery(getTeacherList)) {
                    while (rs.next()) {
                        User user = mapper.mapRow(rs);
                        list.add(user);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public List<User> getListofStudentsByCoursesId(int id) {
        List<User> list = new ArrayList<>();
        UserMapper mapper = new UserMapper();
        try (Connection con = DBManager.getInstance().getConnection()) {
            try (Statement statement = con.createStatement()) {
                try (ResultSet rs = statement.executeQuery(getListOfStudents + id)) {
                    while (rs.next()) {
                        User user = mapper.mapRow(rs);
                        list.add(user);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public List<User> getListOfTeachersByRolesId(int id) {
        List<User> list = new ArrayList<>();
        UserMapper mapper = new UserMapper();
        try (Connection con = DBManager.getInstance().getConnection()) {
            try (Statement statement = con.createStatement()) {
                try (ResultSet rs = statement.executeQuery(getListOfTeachers + id + ";")) {
                    while (rs.next()) {
                        User user = mapper.mapRow(rs);
                        list.add(user);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public User getTeacherBySurname(String surname) {
        UserMapper mapper = new UserMapper();
        User user = null;
        try (Connection con = DBManager.getInstance().getConnection()) {
            try (Statement statement = con.createStatement()) {
                try (ResultSet rs = statement.executeQuery(getTeacherBySurname + "'" + surname + "'" + ";")) {
                    if (rs.next()) {
                        user = mapper.mapRow(rs);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public List<User> getListOfAllStudents() {
        UserMapper mapper = new UserMapper();
        List<User> list = new ArrayList<>();
        try (Connection con = DBManager.getInstance().getConnection()) {
            try (Statement statement = con.createStatement()) {
                try (ResultSet rs = statement.executeQuery(getListOfAllStudents)) {
                    while (rs.next()) {
                        User user = mapper.mapRow(rs);
                        list.add(user);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public List<User> getListOfStudentHasCourses (){
       List<User> list = new ArrayList<>();
       UserMapper mapper = new UserMapper();
       try(Connection con = DBManager.getInstance().getConnection()){
           try(Statement statement = con.createStatement()){
               try(ResultSet rs = statement.executeQuery(getListOfStudentHasCourses)){
                   while (rs.next()){
                       User user = mapper.mapRow(rs);
                       list.add(user);
                   }
               }
           }
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
       return list;
    }


    private static class UserMapper implements EntityMapper<User> {

        @Override
        public User mapRow(ResultSet rs) {
            User user = new User();
            try {
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setRoleId(rs.getInt("roles_id"));
                return user;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return user;
        }
    }
}
