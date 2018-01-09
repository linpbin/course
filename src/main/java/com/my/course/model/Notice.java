package com.my.course.model;

import java.util.Date;

/**
 * Created by lin.pingbin on 2018/1/4.
 */
public class Notice {
    private Integer id;
    private String context;
    private Date createTime;
    private Course courseId;
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

    public Notice() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", context='" + context + '\'' +
                ", createTime=" + createTime +
                ", courseId=" + courseId +
                '}';
    }
}
