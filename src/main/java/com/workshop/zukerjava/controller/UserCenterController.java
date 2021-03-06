package com.workshop.zukerjava.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.workshop.zukerjava.bean.FavoriteInfo;
import com.workshop.zukerjava.bean.HousingInfo;
import com.workshop.zukerjava.bean.Msg;
import com.workshop.zukerjava.bean.User;
import com.workshop.zukerjava.util.MongoUtils;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/usercenter")
public class UserCenterController {

    @RequestMapping(value = "/getprofile", method = RequestMethod.GET)
    public String getProfile(@RequestParam("user_id") Long user_id) {
        /*@RequestParam("user_id") Long user_id) {*/
        //Long user_id = Long.valueOf(data.get("user_id").toString());
        //Long user_id = Long.valueOf(String.valueOf(data.get("user_id"))).longValue();
        JSONObject ret = new JSONObject();
        User user = MongoUtils.findUser(user_id);
        if (user == null) {
            return ret.toJSONString();
        }
        //ret.put("user_id", user.getUser_id());
        ret.put("username", user.getUsername());
        ret.put("email", user.getEmail());
        //ret.put("avatarPath", user.getAvatarPath());
        return ret.toJSONString();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public int updateProfile(@RequestParam("user_id") Long user_id,
                             @RequestBody JSONObject data) {
            /*@RequestParam("user_id") Long user_id,
                             @RequestParam("new_username") String new_username,
                             @RequestParam("new_email") String new_email) {*/
            //Long user_id = Long.valueOf(data.get("user_id").toString());
            //Long user_id = Long.valueOf(String.valueOf(data.get("user_id"))).longValue();
            String new_username = data.get("new_username").toString();
            String new_email = data.get("new_email").toString();
        return MongoUtils.updateUser(user_id,new_username,new_email);
    }

    @RequestMapping(value = "/update/image", method = RequestMethod.POST)
    public int updateProfileImage(@RequestParam("user_id") Long user_id,
                                  @RequestBody JSONObject data) {
            /*(@RequestParam("user_id") Long user_id,
                                  @RequestParam("new_avatar_path") List <String> new_avatar_path) {*/
        //JSONObject obj = JSONObject.parseObject(data);
        //Long user_id = Long.valueOf(data.get("user_id").toString());
        //Long user_id = Long.valueOf(String.valueOf(data.get("user_id"))).longValue();

        List <String> new_avatar_path = (List<String>) data.get("new_avatar_name");
        return MongoUtils.updateProfileImage( user_id, new_avatar_path);
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public String getMessages(@RequestParam("user_id") Long user_id) {
        User user = MongoUtils.findUser(user_id);
        if (user == null) {
            JSONObject ret = new JSONObject();
            ret.put("error", "can not find user");
            return ret.toJSONString();
        }

        List<Msg> msgs = MongoUtils.getMessages(user_id);

        JSONArray ret = new JSONArray();

        Map<String, List<Msg>> map = new HashMap<>();
        Map<String, String> nameMap = new HashMap<>();
        for (Msg m : msgs) {
            List<Msg> l;
            if (m.getSender_id().equals(user_id)) {
                l = map.computeIfAbsent(Long.toString(m.getReceiver_id()), k -> new ArrayList<>());
                nameMap.putIfAbsent(Long.toString(m.getReceiver_id()), m.getReceiver_name());
            } else {
                l = map.computeIfAbsent(Long.toString(m.getSender_id()), k -> new ArrayList<>());
                nameMap.putIfAbsent(Long.toString(m.getSender_id()), m.getSender_name());
            }
            l.add(m);
        }

        for (Map.Entry<String, List<Msg>> e : map.entrySet()) {
            JSONObject obj = new JSONObject();
            obj.put("user_id", e.getKey());
            obj.put("username", nameMap.getOrDefault(e.getKey(), ""));
            obj.put("messages", e.getValue());
            ret.add(obj);
        }

        return ret.toJSONString();
    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public int sendMsg(@RequestParam("user_id") Long user_id,
                       @RequestBody JSONObject data) {
                       //@RequestParam("content") String content) {
        String content = data.getString("content");
        Long recver_id = Long.valueOf(data.getString("user_id"));
        Msg msg = new Msg();
        User sender = MongoUtils.findUser(user_id);
        User recver = MongoUtils.findUser(recver_id);
        if (sender == null || recver == null) {
            return 1;
        }
        msg.setContent(content);
        msg.setSend_time(new Date().toString());
        msg.setReceiver_id(recver.getUser_id());
        msg.setReceiver_name(recver.getUsername());
        msg.setSender_id(sender.getUser_id());
        msg.setSender_name(sender.getUsername());
        return MongoUtils.insertMessage(msg);
    }

    @RequestMapping(value = "/housing/view", method = RequestMethod.GET)
    public String viewHousing(@RequestParam("user_id") Long user_id) {
        List<HousingInfo> housingInfos = MongoUtils.getHousingInfoList(user_id);
        JSONArray array = new JSONArray();
        array.addAll(housingInfos);
        return array.toJSONString();
    }

    @RequestMapping(value = "/housing/{_id}", method = RequestMethod.DELETE)
    public int editHousing(@RequestParam("user_id") Long user_id, @PathVariable("_id") String _id) {
        return MongoUtils.editHousingInfo(_id);
    }

    @RequestMapping(value = "/favorite/list", method = RequestMethod.GET)
    public String getFavoriteList(@RequestParam("user_id") Long user_id) {
        List<FavoriteInfo> favoriteInfos = MongoUtils.getFavoriteInfoList(user_id);
        JSONArray array = new JSONArray();
        array.addAll(favoriteInfos);
        return array.toJSONString();
    }

    @RequestMapping(value = "/favorite/{_id}", method = RequestMethod.DELETE)
    public int removeFavoriteInfo(@RequestParam("user_id") Long user_id, @PathVariable("_id") String _id) {
        return MongoUtils.removeFavoriteInfo(_id);
    }

    @RequestMapping(value = "/housing", method = RequestMethod.POST)
    public int addHousing(
            @RequestBody JSONObject data,
            @RequestParam("user_id") Long user_id) throws Exception {
        HousingInfo housingInfo = new HousingInfo();
        housingInfo.setUser_id(user_id);
        housingInfo.setFromJson(data);
        return MongoUtils.addHousingInfo(housingInfo);
    }
}
