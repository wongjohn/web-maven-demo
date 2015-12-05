package com.aly.security;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 修改密码时，用户输入的新旧密码.
 */
@XmlRootElement
public class PasswordContainer {
    /** 旧密码。*/
    private String oldPassword;
    /** 新密码。*/
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
