package com.my.course.model;

/**
 * Created by lin.pingbin on 2018/1/4.
 */
public class Teacher {
    private Integer id;
    private String teacherName;
    private String teacherEmail;
    private String teacherPhone; //办公电话
    private String teacherWorkingplace; //办公地点
    private String teacherWorkingtime; //办公时间
    private String teacherPassword;

    public Teacher() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public String getTeacherWorkingplace() {
        return teacherWorkingplace;
    }

    public void setTeacherWorkingplace(String teacherWorkingplace) {
        this.teacherWorkingplace = teacherWorkingplace;
    }

    public String getTeacherWorkingTime() {
        return teacherWorkingtime;
    }

    public void setTeacherWorkingTime(String teacherWorkingTime) {
        this.teacherWorkingtime = teacherWorkingTime;
    }

    public String getTeacherPassword() {
        return teacherPassword;
    }

    public void setTeacherPassword(String teacherPassword) {
        this.teacherPassword = teacherPassword;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", teacherName='" + teacherName + '\'' +
                ", teacherEmail='" + teacherEmail + '\'' +
                ", teacherPhone='" + teacherPhone + '\'' +
                ", teacherWorkingplace='" + teacherWorkingplace + '\'' +
                ", teacherWorkingtime='" + teacherWorkingtime + '\'' +
                ", teacherPassword='" + teacherPassword + '\'' +
                '}';
    }
}
