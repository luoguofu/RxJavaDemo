package com.example.smalldemo.rxjavademo;

import java.util.List;

/**
 * Created by lgf on 2017/12/19.
 */

public class Student {
    private String name;
    private List<Course> courseList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
