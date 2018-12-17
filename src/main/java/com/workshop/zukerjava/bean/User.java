package com.workshop.zukerjava.bean;

import javax.persistence.*;

/**
 * 用户个人信息java bean
 */

@Entity
@Table(name = "users")
public class User{
    //user_id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String user_id;
    //@Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email; //为了找回密码，加上邮箱

    @Column(name = "avatarPath")
    private String avatarPath; //上传头像：图像路径

    //@Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
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

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    @Override
    public String toString() {
        return "User [id=" + user_id + ", username=" + username + ", password="
                + password + ", avatarPath=" + avatarPath + "]";
    }
}
