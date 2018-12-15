package com.workshop.zukerjava.bean;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
/**
 * 用户个人信息java bean
 */

@Entity
@Document(collection = "user")
public class User implements Serializable {

    @Id
    private String user_id;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)

    private String username;

    private String password;

    private String email; //为了找回密码，加上邮箱

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


    //参考https://github.com/shuangbofu/ustate/

    @Override
    public String toString() {
        return "User [id=" + user_id + ", username=" + username + ", password="
                + password + ", avatarPath=" + avatarPath + "]";
    }
}
