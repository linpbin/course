package com.my.course.model;

import java.util.Date;

/**
 * Created by lin.pingbin on 2018/1/9.
 */
public class StudentTask {
    private Integer id;
    private Integer taskId;
    private Integer studentId;
    private Integer courseId;
    private Date uploadTime;
    private String stutaskAddress;

   private String taskName;
    public StudentTask() {
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getStutaskAddress() {
        return stutaskAddress;
    }

    public void setStutaskAddress(String stutaskAddress) {
        this.stutaskAddress = stutaskAddress;
    }
}
