package com.workshop.zukerjava.bean;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

/**
 * 用户个人信息java bean
 */

@Entity
@Document(collection = "user")
public class User{
    //user_id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String user_id;
    //@Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)

    @Field("username")
    private String username;

    @Field("password")
    private String password;

    @Field("email")
    private String email; //为了找回密码，加上邮箱

    @Field("avatarPath")
    private List<String> avatarPath = new ArrayList<>(); //上传头像：图像路径

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

    public List<String> getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(List<String> avatarPath) {
        this.avatarPath = avatarPath;
    }

    @Override
    public String toString() {
        return "User [id=" + user_id + ", username=" + username + ", password="
                + password + ", avatarPath=" + avatarPath + "]";
    }
}
