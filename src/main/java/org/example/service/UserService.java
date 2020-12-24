package org.example.service;

import org.example.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface UserService {
    void registerUser(HttpServletRequest request, HttpServletResponse response) throws IOException;
    List<User> getListofLoginsAndPasswords();
    void addCourse(int idUser, int idCourse);
    List<User> getTeachersList();
    List<User> getListofStudentsByCoursesId(int id);
    List<User> getListOfTeachersByRolesId(int id);
    User getTeacherBySurname(String surname);
    List<User> getListOfAllStudents();
    List<User> getListOfStudentHasCourses();
}
