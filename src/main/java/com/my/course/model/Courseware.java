package com.my.course.model;

/**
 * Created by lin.pingbin on 2018/1/9.
 */
public class Courseware {
    private Integer id;
    private String coursewareName;
    private String describes; //描述
    private String fileAddress; //文件在服务器的地址
    private Integer courseId; //课程id

    public Courseware() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCoursewareName() {
        return coursewareName;
    }

    public void setCoursewareName(String coursewareName) {
        this.coursewareName = coursewareName;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Courseware{" +
                "id=" + id +
                ", coursewareName='" + coursewareName + '\'' +
                ", describes='" + describes + '\'' +
                ", fileAddress='" + fileAddress + '\'' +
                ", courseId=" + courseId +
                '}';
    }
}
