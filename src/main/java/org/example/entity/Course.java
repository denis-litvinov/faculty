package org.example.entity;

import java.util.Date;

public class Course extends Entity {
    private String name;
    private Date startdate;
    private Date enddate;
    private int topicId;
    private int registered;

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public int getRegistered() {
        return registered;
    }

    public void setRegistered(int registered) {
        this.registered = registered;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", startdate=" + startdate +
                ", enddate=" + enddate +
                ", topicId=" + topicId +
                '}';
    }
}
