package com.my.course.model;

import java.util.Date;

/**
 * Created by lin.pingbin on 2018/1/4.
 */
public class Announcement {
    private Integer id;
    private Integer isStudent; //是否发给学生
    private Integer isTeacher; //是否发给老师
    private String context;
    private Date createTime;
    /*
    * 用于将createTime字段转化为字符串，方便前端显示
    */
    private String stringTime;

    public String getStringTime() {
        return stringTime;
    }

    public void setStringTime(String stringTime) {
        this.stringTime = stringTime;
    }

    public Announcement() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIsStudent() {
        return isStudent;
    }

    public void setIsStudent(Integer isStudent) {
        this.isStudent = isStudent;
    }

    public Integer getIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(Integer isTeacher) {
        this.isTeacher = isTeacher;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
