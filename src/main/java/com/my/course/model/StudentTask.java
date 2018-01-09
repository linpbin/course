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
    private int dr; //是否提交成功
    public StudentTask() {
    }

    public int getDr() {
        return dr;
    }

    public void setDr(int dr) {
        this.dr = dr;
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
