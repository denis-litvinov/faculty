package org.example.dao;



import org.example.db.DBManager;
import org.example.entity.Grade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class GradeDao {

    private static String getGrades = "SELECT * FROM users_has_courses";
    private static String updateGrade = "UPDATE users_has_courses SET grade = ? WHERE (users_id, courses_id) = (?,?);";
    private static String getGradesByUserSurname = "select users_id, courses_id from users_has_courses left join users on users_id = id where users.surname =";
    private static String addCourseToTeacher = "INSERT INTO users_has_courses (users_id, courses_id, grade) VALUES (?, ?, ?)";
    private static String getStudentsUserHasCourses = "select * from users_has_courses left join users u on users_has_courses.users_id = u.id where roles_id =1;";

    public static List<Grade> getGrades() {
        List<Grade> list = new ArrayList<>();
        try (Connection con = DBManager.getInstance().getConnection()) {
            try (Statement statement = con.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(getGrades)) {
                    while (resultSet.next()) {
                        Grade grade = new Grade();
                        grade.setUser_id(resultSet.getInt("users_id"));
                        grade.setCourse_id(resultSet.getInt("courses_id"));
                        grade.setGrade(resultSet.getInt("grade"));
                        list.add(grade);

                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public static void updateGrade(int grade, int users_id, int courses_id) {
        try (Connection con = DBManager.getInstance().getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(updateGrade)) {
                statement.setInt(1, grade);
                statement.setInt(2, users_id);
                statement.setInt(3, courses_id);
                statement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<Grade> getGradesByUserSurname(String surname) {
        List<Grade> list = new ArrayList<>();
        try (Connection con = DBManager.getInstance().getConnection()) {
            try (Statement statement = con.createStatement()) {
                try (ResultSet rs = statement.executeQuery(getGradesByUserSurname + "'" + surname + "';")) {
                    while (rs.next()) {
                        Grade grade = new Grade();
                        grade.setUser_id(rs.getInt("users_id"));
                        grade.setCourse_id(rs.getInt("courses_id"));
                        list.add(grade);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public static void addCourseToTeacher(int user_id, int course_id) {
        try (Connection con = DBManager.getInstance().getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(addCourseToTeacher)) {
                statement.setInt(1, user_id);
                statement.setInt(2, course_id);
                statement.setString(3, null);
                statement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<Grade> getStudentsUserHasCourses() {
        List<Grade> list = new ArrayList<>();
        try(Connection con = DBManager.getInstance().getConnection()){
            try(Statement statement = con.createStatement()){
                try(ResultSet rs = statement.executeQuery(getStudentsUserHasCourses)){
                    while (rs.next()){
                        Grade grade = new Grade();
                        grade.setUser_id(rs.getInt("users_id"));
                        grade.setCourse_id(rs.getInt("courses_id"));
                        list.add(grade);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }


}
