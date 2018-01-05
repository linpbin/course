package com.my.course.model;

/**
 * Created by lin.pingbin on 2018/1/5.
 * 修改密码类
 */
public class PasswordDTO {
    private Integer userName;
    private String newPassword;
    private String oldPassword;
    private String comfirePassword;

    public String getComfirePassword() {
        return comfirePassword;
    }

    public void setComfirePassword(String comfirePassword) {
        this.comfirePassword = comfirePassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public Integer getUserName() {

        return userName;
    }

    public void setUserName(Integer userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "PasswordDTO{" +
                "userName=" + userName +
                ", newPassword='" + newPassword + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", comfirePassword='" + comfirePassword + '\'' +
                '}';
    }
}
