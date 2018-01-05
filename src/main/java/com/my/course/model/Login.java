package com.my.course.model;

/**
 * Created by lin.pingbin on 2018/1/4.
 */
public class Login {
    private Integer username;
    private String password;
    private String logintype;

    public Integer getUsername() {
        return username;
    }

    public void setUsername(Integer username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogintype() {
        return logintype;
    }

    public void setLogintype(String logintype) {
        this.logintype = logintype;
    }

    @Override
    public String toString() {
        return "Login{" +
                "username=" + username +
                ", password='" + password + '\'' +
                ", logintype='" + logintype + '\'' +
                '}';
    }
}
