package com.my.course.model;

import java.util.List;

/**
 * Created by lin.pingbin on 2018/1/4.
 */
public class Clazz {
    private Integer id;
    private String clazzName;
    private String clazzFaculty; //班级所在院系
    private String clazzProfession; //班级所在专业
    private String clazzNumber; //班级人数
    private List<Course> courseList;//课程列表
    private List<Student> studentList;//学生列表
    public Clazz() {
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getClazzFaculty() {
        return clazzFaculty;
    }

    public void setClazzFaculty(String clazzFaculty) {
        this.clazzFaculty = clazzFaculty;
    }

    public String getClazzProfession() {
        return clazzProfession;
    }

    public void setClazzProfession(String clazzProfession) {
        this.clazzProfession = clazzProfession;
    }

    public String getClazzNumber() {
        return clazzNumber;
    }

    public void setClazzNumber(String clazzNumber) {
        this.clazzNumber = clazzNumber;
    }
}
