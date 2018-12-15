package com.workshop.zukerjava.bean;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 房源信息java bean
 */
@Document(collection = "housing_info")
public class HousingInfo {
    @Id
    private String _id;

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

    @Field("imageUrl")
    private List<String> imageUrl = new ArrayList<>();

    @Field("others")
    private List<String> others = new ArrayList<>();

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

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

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getOthers() {
        return others;
    }

    public void setOthers(List<String> others) {
        this.others = others;
    }
}
