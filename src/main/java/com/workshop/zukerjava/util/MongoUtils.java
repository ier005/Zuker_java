package com.workshop.zukerjava.util;

import com.workshop.zukerjava.bean.FavoriteInfo;
import com.workshop.zukerjava.bean.HousingInfo;
import com.workshop.zukerjava.bean.Msg;
import com.workshop.zukerjava.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class MongoUtils {
    @Autowired
    private static MongoTemplate mongoTemplate;

    public static MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }


    public static User findUser(String user_id) {
        List<User> result = mongoTemplate.find(new Query(Criteria.where("user_id").is(user_id)), User.class);
        if (result.size() != 1) {
            return null;
        }
        return result.get(0);
    }

    public static List<Msg> getMessages(String user_id) {
        Criteria criteriaSend = Criteria.where("sender_id").is(user_id);
        Criteria criteriaRecv = Criteria.where("receive_id").is(user_id);
        return mongoTemplate.find(
                new Query(new Criteria().orOperator(criteriaSend, criteriaRecv)),
                Msg.class
        );
    }

    public static List<HousingInfo> getHousingInfoList(String user_id) {
        return mongoTemplate.find(
                new Query(Criteria.where("user_id").is(user_id)),
                HousingInfo.class
        );
    }

    public static HousingInfo getHousingInfo(String _id) {
        List<HousingInfo> list = mongoTemplate.find(
                new Query(Criteria.where("_id").is(_id)),
                HousingInfo.class
        );
        if (list.size() != 1) {
            return null;
        }
        return list.get(0);
    }

    public static int editHousingInfo(String _id, HousingInfo housingInfo) {
        //TODO
        return 0;
    }

    public static int insertMessage(Msg msg) {
        mongoTemplate.insert(msg, "messages");
        return 0;
    }

    public static List<FavoriteInfo> getFavoriteInfoList(String user_id) {
        return mongoTemplate.find(
                new Query(Criteria.where("user_id").is(user_id)),
                FavoriteInfo.class
        );
    }


    public static int removeFavoriteInfo(String favoriteInfoId) {
        mongoTemplate.remove(new Query(Criteria.where("_id").is(favoriteInfoId)), FavoriteInfo.class);
        return 0;
    }
}
