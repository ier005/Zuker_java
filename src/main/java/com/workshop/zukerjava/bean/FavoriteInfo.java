package com.workshop.zukerjava.bean;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

/**
 * 收藏信息java bean
 */
@Document(collection = "favorite_info")
public class FavoriteInfo {
    @Id
    private String _id;

    @Field("user_id")
    private String user_id;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    //TODO other member variables & functions
}
