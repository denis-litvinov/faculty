package org.example.dao;


import org.example.db.DBManager;
import org.example.entity.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoursesDao {


    private static String listOfCourses = "select id, name, start_date, end_date, topics_id, registered" +
            " from users_has_courses left join courses on courses_id = id where users_has_courses.users_id = ";
    private static String getAllCourses = "SELECT * FROM courses;";
    private static String updateRegUsersAmount = "UPDATE courses set registered = ? where id = ?;";
    private static String getListofCoursesByTopicsId = "SELECT * FROM courses WHERE topics_id = ";
    private static String findTopicId = "SELECT * FROM topics WHERE name = ";
    private static String getListOfCoursesByCourseId = "SELECT * FROM courses WHERE id IN (";
    private static String getCourseByName = "SELECT * FROM courses WHERE name=";
    private static String addCourse = "INSERT INTO courses (name, start_date, end_date, topics_id, registered) VALUES (?, ?, ?, ?, ?)";
    private static String getListOfCoursesFromUsersHasCourses = "select id, name, start_date, end_date, topics_id, registered from users_has_courses left join courses on users_has_courses.courses_id = courses.id;";


    public static List<Course> getListOfCoursesByUserId(int id) {

        CourseMapper mapper = new CourseMapper();
        List<Course> list = new ArrayList<>();
        try (Connection con = DBManager.getInstance().getConnection()) {
            try (Statement statement = con.createStatement()) {
                try (ResultSet rs = statement.executeQuery(listOfCourses + id)) {
                    while (rs.next()) {
                        Course course = mapper.mapRow(rs);
                        list.add(course);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Course> getAllCourses() {
        CourseMapper mapper = new CourseMapper();
        List<Course> list = new ArrayList<>();
        try (Connection con = DBManager.getInstance().getConnection()) {
            try (Statement statement = con.createStatement()) {
                try (ResultSet rs = statement.executeQuery(getAllCourses)) {
                    while (rs.next()) {
                        Course course = mapper.mapRow(rs);
                        list.add(course);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public static void updateRegisteredUsersAmount(int registered, int idCourse) {

        try (Connection con = DBManager.getInstance().getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(updateRegUsersAmount)) {
                statement.setInt(1, registered);
                statement.setInt(2, idCourse);
                statement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<Course> getListofCoursesByTopicsId(int id) {
        List<Course> list = new ArrayList<>();
        CourseMapper mapper = new CourseMapper();
        try (Connection con = DBManager.getInstance().getConnection()) {
            try (Statement statement = con.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(getListofCoursesByTopicsId + id + ";")) {
                    while (resultSet.next()) {
                        Course course = mapper.mapRow(resultSet);
                        list.add(course);
                    }

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }


    public static int findTopicIdByName(String name) {
        int id = 0;
        try (Connection con = DBManager.getInstance().getConnection()) {
            try (Statement statement = con.createStatement()) {
                try (ResultSet rs = statement.executeQuery(findTopicId + "'" + name + "';")) {
                    while (rs.next()) {
                        id = rs.getInt("id");
                    }

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

    public static List<Course> getListOfCoursesByCourseId(List<Integer> ids) {
        String word = Arrays.toString(ids.toArray());
        word = word.substring(1, word.length() - 1);
        List<Course> list = new ArrayList<>();
        CourseMapper mapper = new CourseMapper();
        try (Connection con = DBManager.getInstance().getConnection()) {
            try (Statement statement = con.createStatement()) {
                try (ResultSet rs = statement.executeQuery(getListOfCoursesByCourseId + word + ");")) {
                    while (rs.next()) {
                        Course course = mapper.mapRow(rs);
                        list.add(course);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }


    public static Course getCourseByName(String name) {
        CourseMapper mapper = new CourseMapper();
        Course course = null;
        try (Connection con = DBManager.getInstance().getConnection()) {
            try (Statement statement = con.createStatement()) {
                try (ResultSet rs = statement.executeQuery(getCourseByName + "'" + name + "'" + ";")) {
                    if (rs.next()) {
                        course = mapper.mapRow(rs);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return course;
    }

    public static void addCourse(Course course) {
        try (Connection con = DBManager.getInstance().getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(addCourse, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, course.getName());
                statement.setDate(2, (Date) course.getStartdate());
                statement.setDate(3, (Date) course.getEnddate());
                statement.setInt(4, course.getTopicId());
                statement.setInt(5, 0);
                statement.executeUpdate();
                try (ResultSet rs = statement.getGeneratedKeys()) {
                    if (rs.next()) {
                        course.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<Course> getListOfCoursesFromUsersHasCourses() {
        CourseMapper mapper = new CourseMapper();
        List<Course> courseList = new ArrayList<>();
        try (Connection con = DBManager.getInstance().getConnection()) {
            try (Statement statement = con.createStatement()) {
                try (ResultSet rs = statement.executeQuery(getListOfCoursesFromUsersHasCourses)) {
                    while (rs.next()) {
                        Course course = mapper.mapRow(rs);
                        courseList.add(course);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return courseList;
    }


    private static class CourseMapper implements EntityMapper {

        @Override
        public Course mapRow(ResultSet rs) {
            Course course = new Course();
            try {
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setStartdate(rs.getDate("start_date"));
                course.setEnddate(rs.getDate("end_date"));
                course.setTopicId(rs.getInt("topics_id"));
                course.setRegistered(rs.getInt("registered"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return course;
        }
    }
}
