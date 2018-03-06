package com.my.course.model;

import java.util.Date;

public class SignRecord {
    private Integer id;
    private Integer signruleId;
    private Date signTime;
    private Integer studentId;
    private String studentName;
    private String studentClazz;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSignruleId() {
        return signruleId;
    }

    public void setSignruleId(Integer signruleId) {
        this.signruleId = signruleId;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentClazz() {
        return studentClazz;
    }

    public void setStudentClazz(String studentClazz) {
        this.studentClazz = studentClazz;
    }
}
