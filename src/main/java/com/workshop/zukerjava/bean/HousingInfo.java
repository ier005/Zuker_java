package com.workshop.zukerjava.bean;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 房源信息java bean
 */
@Document(collection = "housing")
public class HousingInfo {
    @Id
    private String _id;

    @Field("user_id")
    private Long user_id;

    @Field("name")
    private String name;

    @Field("description")
    private String description;

    @Field("location")
    private String location;

    @Field("phoneNumber")
    private String phoneNumber;

    @Field("minPrice")
    private String minPrice;

    @Field("maxPrice")
    private String maxPrice;

    @Field("availableTime_start")
    private Date availableTime_start;

    @Field("availableTime_end")
    private Date availableTime_end;

    @Field("acreage")
    private String acreage;

    @Field("decoration")
    private String decoration;

    @Field("method")
    private String method;

    @Field("hall")
    private String hall;

    @Field("room")
    private String room;

    @Field("bath")
    private String bath;

    @Field("images")
    private List<String> images = new ArrayList<>();

    @Field("others")
    private List<String> others = new ArrayList<>();

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Long getUser_id() {return user_id; }

    public void setUser_id(Long user_id) {this.user_id = user_id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Date getAvailableTime_start() {
        return availableTime_start;
    }

    public void setAvailableTime_start(Date availableTime_start) {
        this.availableTime_start = availableTime_start;
    }

    public Date getAvailableTime_end() {
        return availableTime_end;
    }

    public void setAvailableTime_end(Date availableTime_end) {
        this.availableTime_end = availableTime_end;
    }

    public String getAcreage() {
        return acreage;
    }

    public void setAcreage(String acreage) {
        this.acreage = acreage;
    }

    public String getDecoration() {
        return decoration;
    }

    public void setDecoration(String decoration) {
        this.decoration = decoration;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getBath() {
        return bath;
    }

    public void setBath(String bath) {
        this.bath = bath;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getOthers() {
        return others;
    }

    public void setOthers(List<String> others) {
        this.others = others;
    }

    public int setFromJson(JSONObject data) throws Exception {
        this.setAcreage(data.getString("acreage"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        if (!data.getString("availableTime_start").isEmpty()) {
            this.setAvailableTime_start(format.parse(data.getString("availableTime_start")));
        }
        if (!data.getString("availableTime_end").isEmpty()) {
            this.setAvailableTime_end(format.parse(data.getString("availableTime_end")));
        }
        this.setBath(data.getString("bath"));
        this.setDecoration(data.getString("decoration"));
        this.setDescription(data.getString("description"));
        this.setHall(data.getString("hall"));
        this.setLocation(data.getString("location"));
        this.setMaxPrice(data.getString("maxPrice"));
        this.setMinPrice(data.getString("minPrice"));
        this.setMethod(data.getString("method"));
        this.setName(data.getString("name"));
        this.setPhoneNumber(data.getString("phoneNumber"));
        this.setRoom(data.getString("room"));
        this.setImages((List<String>) data.get("imageBase64"));
        this.setOthers((List<String>) data.get("others"));

        return 0;
    }
}
