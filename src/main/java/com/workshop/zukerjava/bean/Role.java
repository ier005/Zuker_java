package com.workshop.zukerjava.bean;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * 权限信息java bean
 */
@Entity
@Document(collection = "role")
public class Role {


    private String id;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    
    private String role;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}