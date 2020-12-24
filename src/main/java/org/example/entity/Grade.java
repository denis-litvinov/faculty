package org.example.entity;

import java.util.Objects;

public class Grade {

    private int user_id;
    private int course_id;
    private int grade;


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade1 = (Grade) o;
        return getUser_id() == grade1.getUser_id() &&
                getCourse_id() == grade1.getCourse_id() &&
                getGrade() == grade1.getGrade();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser_id(), getCourse_id(), getGrade());
    }

    @Override
    public String toString() {
        return "Grade{" +
                "user_id=" + user_id +
                ", course_id=" + course_id +
                ", grade=" + grade +
                '}';
    }
}
