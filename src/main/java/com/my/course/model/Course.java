package com.my.course.model;

import java.util.List;

/**
 * Created by lin.pingbin on 2018/1/4.
 */
public class Course {
    private Integer id;
    private Teacher teacherId;
    private String courseName;
    private String courseDescribe;
    private List<Clazz> clazzList;
    public Course() {
    }

    public List<Clazz> getClazzList() {
        return clazzList;
    }

    public void setClazzList(List<Clazz> clazzList) {
        this.clazzList = clazzList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Teacher getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Teacher teacherId) {
        this.teacherId = teacherId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescribe() {
        return courseDescribe;
    }

    public void setCourseDescribe(String courseDescribe) {
        this.courseDescribe = courseDescribe;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", teacherId=" + teacherId +
                ", courseName='" + courseName + '\'' +
                ", courseDescribe='" + courseDescribe + '\'' +
                ", clazzList=" + clazzList +
                '}';
    }
}
