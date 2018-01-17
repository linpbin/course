package com.my.course.model;

/**
 * Created by lin.pingbin on 2018/1/17.
 */
public class StudentDTO {
    private Integer id; //学号 and 用户名

    private String studentName;

    private String studentFaculty; //系

    private String studentProfession; //专业

    private String studentPassword;

    private String sex;

    private String studentEnrollmentyear; //入学年份
    private String clazzId;
    private String courseId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentFaculty() {
        return studentFaculty;
    }

    public void setStudentFaculty(String studentFaculty) {
        this.studentFaculty = studentFaculty;
    }

    public String getStudentProfession() {
        return studentProfession;
    }

    public void setStudentProfession(String studentProfession) {
        this.studentProfession = studentProfession;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStudentEnrollmentyear() {
        return studentEnrollmentyear;
    }

    public void setStudentEnrollmentyear(String studentEnrollmentyear) {
        this.studentEnrollmentyear = studentEnrollmentyear;
    }

    public String getClazzId() {
        return clazzId;
    }

    public void setClazzId(String clazzId) {
        this.clazzId = clazzId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", studentFaculty='" + studentFaculty + '\'' +
                ", studentProfession='" + studentProfession + '\'' +
                ", studentPassword='" + studentPassword + '\'' +
                ", sex='" + sex + '\'' +
                ", studentEnrollmentyear='" + studentEnrollmentyear + '\'' +
                ", clazzId='" + clazzId + '\'' +
                ", courseId='" + courseId + '\'' +
                '}';
    }
}
