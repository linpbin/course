package com.my.course.model;

/**
 * Created by lin.pingbin on 2018/1/4.
 */
public class Administrators {
    private Integer id;
    private String adminPassword;

    public Administrators() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
}
