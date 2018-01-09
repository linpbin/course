package com.my.course.model;

import java.util.Date;

/**
 * Created by lin.pingbin on 2018/1/9.
 */
public class TeacherTask {
    private Integer id;
    private String taskName;
    private String taskDescribe;
    private Date taskDeadline; //上交截止时间
    private String teataskAddress;
    private Integer courseId;

    public TeacherTask() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescribe() {
        return taskDescribe;
    }

    public void setTaskDescribe(String taskDescribe) {
        this.taskDescribe = taskDescribe;
    }

    public Date getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskDeadline(Date taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public String getTeataskAddress() {
        return teataskAddress;
    }

    public void setTeataskAddress(String teataskAddress) {
        this.teataskAddress = teataskAddress;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
