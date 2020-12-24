package org.example.service;

import org.example.dao.UserDao;
import org.example.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UserServiceImpl implements UserService{

    private UserDao userDao;

    @Override
    public void registerUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        List<User> list = userDao.getListofLoginsAndPasswords();
        /* Code below:
         1. checks if entered login/password by user EQUALS already created login/password by other users
        * and redirects them on SUCCESS or FAIL pages
         2. creates session with attribute of login */
        boolean flag = false;
        for (User user: list){
            if (!login.equals(user.getLogin()) && !password.equals(user.getPassword())){
                response.sendRedirect("views/regfail.jsp");
                flag = true;
                break;
            } else {
                continue;
            }
        }
        if (flag == false){
            User user = User.createUser(login, password, name, surname, 1);
            userDao.registerUser(user);
            HttpSession session = request.getSession();
            session.setAttribute("login", login);
            session.setAttribute("id", user.getId());
            response.sendRedirect("views/student/success.jsp");
        }
    }

    @Override
    public List<User> getListofLoginsAndPasswords() {
        return null;
    }

    @Override
    public void addCourse(int idUser, int idCourse) {

    }

    @Override
    public List<User> getTeachersList() {
        return null;
    }

    @Override
    public List<User> getListofStudentsByCoursesId(int id) {
        return null;
    }

    @Override
    public List<User> getListOfTeachersByRolesId(int id) {
        return null;
    }

    @Override
    public User getTeacherBySurname(String surname) {
        return null;
    }

    @Override
    public List<User> getListOfAllStudents() {
        return null;
    }

    @Override
    public List<User> getListOfStudentHasCourses() {
        return null;
    }
}
