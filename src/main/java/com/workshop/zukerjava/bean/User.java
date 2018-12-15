package com.workshop.zukerjava.bean;

import java.io.Serializable;

/**
 * 用户个人信息java bean
 */
public class User implements Serializable {

    private String user_id;

    private String username;

    private String password;

    private String avatarPath; //上传头像：图像路径

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }
}
