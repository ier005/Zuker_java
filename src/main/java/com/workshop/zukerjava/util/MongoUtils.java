package com.workshop.zukerjava.util;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.workshop.zukerjava.bean.FavoriteInfo;
import com.workshop.zukerjava.bean.HousingInfo;
import com.workshop.zukerjava.bean.Msg;
import com.workshop.zukerjava.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MongoUtils {
    private static final Logger log = LoggerFactory.getLogger(MongoUtils.class);

    @Value("${spring.data.mongodb.uri}")
    private static String mongoUri = "mongodb://zuker:zuker@localhost:27017/zuker";

    private static MongoTemplate mongoTemplate;

    public static MongoTemplate getMongoTemplate() {
        if (mongoTemplate == null) {
            if (mongoUri != null && !mongoUri.isEmpty()) {
                log.info("init mongo utils");
                MongoClient mongoClient = new MongoClient(new MongoClientURI(mongoUri));
                mongoTemplate = new MongoTemplate(mongoClient, "zuker");
                return mongoTemplate;
            } else {
                log.error("can not init mongo utils: mongo uri is empty");
                return null;
            }
        } else {
            return mongoTemplate;
        }
    }

    public static User findUser(Long user_id) {
        List<User> result = getMongoTemplate().find(new Query(Criteria.where("user_id").is(user_id)), User.class);
        if (result.size() != 1) {
            return null;
        }
        return result.get(0);
    }

    public static User findUserByName(String username) {
        List<User> result = getMongoTemplate().find(new Query(Criteria.where("username").is(username)), User.class);
        if (result.size() != 1) {
            return null;
        }
        return result.get(0);
    }

    public static List<Msg> getMessages(Long user_id) {
        Criteria criteriaSend = Criteria.where("sender_id").is(user_id);
        Criteria criteriaRecv = Criteria.where("receiver_id").is(user_id);
        return getMongoTemplate().find(
                new Query(new Criteria().orOperator(criteriaSend, criteriaRecv)),
                Msg.class
        );
    }

    public static List<HousingInfo> getHousingInfoList(Long user_id) {
        return getMongoTemplate().find(
                new Query(Criteria.where("user_id").is(user_id)),
                HousingInfo.class
        );
    }

    public static HousingInfo getHousingInfo(String _id) {
        List<HousingInfo> list = getMongoTemplate().find(
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
        getMongoTemplate().insert(msg, "messages");
        return 0;
    }

    public static List<FavoriteInfo> getFavoriteInfoList(Long user_id) {
        return getMongoTemplate().find(
                new Query(Criteria.where("user_id").is(user_id)),
                FavoriteInfo.class
        );
    }


    public static int removeFavoriteInfo(String favoriteInfoId) {
        getMongoTemplate().remove(new Query(Criteria.where("_id").is(favoriteInfoId)), FavoriteInfo.class);
        return 0;
    }

    public static int updateUser(Long user_id, String newUsername, String newEmail) {
        //TODO
        User user = findUser(user_id);
        if (user!=null) {
            user.setUsername(newUsername);
            user.setEmail(newEmail);
            return 1;
        }
        return 0;
    }

    public static int updateProfileImage(Long user_id, List <String> new_avatar_path){
        //TODO
        User user = findUser(user_id);
        if (user!=null){
            user.setAvatarPath(new_avatar_path);
            return 1;
        }
        return 0;
    }

    public static int updatePassword(Long user_id,String password){
        User user = findUser(user_id);
        if (user!=null){
            user.setPassword(password);
            return 1;
        }
        return 0;
    }

    public static int register(User user){

        getMongoTemplate().insert(user, "user");
        log.debug("insert register!!!!");
        return 1;
    }

    public static int forget(Long user_id, String newpassword){
        User user = findUser(user_id);
        if (user!=null){
            user.setPassword(newpassword);
            return 1;
        }
        return 0;
    }



}
