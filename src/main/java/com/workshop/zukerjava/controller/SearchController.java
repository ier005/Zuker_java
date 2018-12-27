package com.workshop.zukerjava.controller;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.workshop.zukerjava.bean.HousingInfo;
import com.workshop.zukerjava.bean.Msg;
import com.workshop.zukerjava.bean.User;
import com.workshop.zukerjava.util.MongoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/search")
public class SearchController{

    private static MongoTemplate mongoTemplate;

    public static MongoTemplate getMongoTemplate(String database) {
        String mongoUri = "mongodb://zuker:zuker@localhost/"+database;
        if (mongoTemplate == null) {
            if (mongoUri != null && !mongoUri.isEmpty()) {
                MongoClient mongoClient = new MongoClient(new MongoClientURI(mongoUri));
                mongoTemplate = new MongoTemplate(mongoClient, database);
                return mongoTemplate;
            } else {
                System.out.println("error");
                return null;
            }
        } else {
            return mongoTemplate;
        }
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public boolean add(@RequestParam("id") String id){
        List<User> result = getMongoTemplate("zuker").find(new Query(Criteria.where("id").is(id)), User.class);
        if (result.size() != 0) {
            return false;
        }

        List<HousingInfo> houses = new ArrayList<>();
        HousingInfo house = new HousingInfo();
        house.set_id(id);
        house.setAcreage(id);
        house.setBath(id);
        house.setDecoration(id);
        house.setDescription(id);
        house.setName(id);
        house.setRoom(id);

        houses.add(house);
        getMongoTemplate("zuker").insert(houses,HousingInfo.class);
        return true;
    }

    @RequestMapping(value = "/search_all", method = RequestMethod.GET)
    public List<HousingInfo> getAll() {
        return getMongoTemplate("zuker").find(
                new Query(),
                HousingInfo.class
        );
    }

    @RequestMapping(value = "/query_condition", method = RequestMethod.GET)
    public List<HousingInfo> getByCondition(
            @RequestParam(value="id") String id,
            @RequestParam(value="name",required=false) String name,
            @RequestParam(value="description",required=false) String description,
            @RequestParam(value="location",required=false) String location,
            @RequestParam(value="phoneNumber",required=false) String phoneNumber,
            @RequestParam(value="minPrice",required=false) String minPrice,
            @RequestParam(value="maxPrice",required=false) String maxPrice,
//            @RequestParam(value="availableTime_start",required=false) Date availableTime_start,
//            @RequestParam(value="availableTime_end",required=false) Date availableTime_end,
            @RequestParam(value="decoration",required=false) String decoration,
            @RequestParam(value="method",required=false) String method,
            @RequestParam(value="acreage",required=false) String acreage,
            @RequestParam(value="hall",required=false) String hall,
            @RequestParam(value="room",required=false) String room,
            @RequestParam(value="bath",required=false) String bath
    ) {

        Query query = new Query();
        Criteria m_criteria = new Criteria();

        m_criteria = m_criteria.where("_id").is(id);

        query.addCriteria(m_criteria);
        if(name != null){
//            System.out.println("name not null,name = "+name);
            query.addCriteria(Criteria.where("name").regex(".*?" +name+ ".*"));
        }else if(description != null){
            query.addCriteria(Criteria.where("description").regex(".*?" +description+ ".*"));
        }else if(location != null){
            query.addCriteria(Criteria.where("location").regex(".*?" +location+ ".*"));
        }else if(phoneNumber != null){
            query.addCriteria(Criteria.where("phoneNumber").regex(".*?" +phoneNumber+ ".*"));
        }else if(minPrice != null){
            query.addCriteria(Criteria.where("minPrice").regex(".*?" +minPrice+ ".*"));
        }else if(maxPrice != null){
            query.addCriteria(Criteria.where("maxPrice").regex(".*?" +maxPrice+ ".*"));
        }else if(decoration != null){
            query.addCriteria(Criteria.where("decoration").regex(".*?" +decoration+ ".*"));
        }else if(method != null){
            query.addCriteria(Criteria.where("method").regex(".*?" +method+ ".*"));
        }else if(acreage != null){
            query.addCriteria(Criteria.where("acreage").regex(".*?" +acreage+ ".*"));
        }else if(hall != null){
            query.addCriteria(Criteria.where("hall").regex(".*?" +hall+ ".*"));
        }else if(room != null){
            query.addCriteria(Criteria.where("room").regex(".*?" +room+ ".*"));
        }else if(bath != null){
            query.addCriteria(Criteria.where("bath").regex(".*?" +bath+ ".*"));
        }


        return getMongoTemplate("zuker").find(
                query,
                HousingInfo.class
        );
    }

}
