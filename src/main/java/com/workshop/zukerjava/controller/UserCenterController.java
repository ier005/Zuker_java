package com.workshop.zukerjava.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.workshop.zukerjava.bean.FavoriteInfo;
import com.workshop.zukerjava.bean.HousingInfo;
import com.workshop.zukerjava.bean.Msg;
import com.workshop.zukerjava.bean.User;
import com.workshop.zukerjava.util.MongoUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/usercenter")
public class UserCenterController {

//    @RequestMapping(value = "/getprofile", method = RequestMethod.GET)
//    public String getProfile(@RequestParam("user_id") String user_id) {
//        User user = MongoUtils.findUser(user_id);
//        return JSONObject.toJSONString(user);
//    }
//
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public int updateProfile(@RequestParam("data") String data) {
//        User user = (User) JSONObject.parse(data);
//        return MongoUtils.updateUser(user);
//    }
//
//    @RequestMapping(value = "/update/image", method = RequestMethod.POST)
//    public int updateProfileImage(@RequestParam("data") String data) {
//        JSONObject obj = JSONObject.parseObject(data);
//
//        return 0;
//    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public String getMessages(@RequestParam("user_id") String user_id) {
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
                l = map.computeIfAbsent(m.getReceiver_id(), k -> new ArrayList<>());
                nameMap.putIfAbsent(m.getReceiver_id(), m.getReceiver_name());
            } else {
                l = map.computeIfAbsent(m.getSender_id(), k -> new ArrayList<>());
                nameMap.putIfAbsent(m.getSender_id(), m.getSender_name());
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

    @RequestMapping(value = "/messages/send", method = RequestMethod.POST)
    public int sendMsg(@RequestParam("user_id") String user_id, @RequestParam("content") String content) {
        Msg msg = new Msg();
        User sender = MongoUtils.findUser("");
        User recver = MongoUtils.findUser(user_id);
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
    public String viewHousing(@RequestParam("user_id") String user_id) {
        List<HousingInfo> housingInfos = MongoUtils.getHousingInfoList(user_id);
        JSONArray array = new JSONArray();
        array.addAll(housingInfos);
        return array.toJSONString();
    }

    @RequestMapping(value = "/housing/edit/{_id}", method = RequestMethod.POST)
    public int editHousing(@RequestParam("user_id") String user_id, @PathVariable("_id") String _id,
                           @RequestParam("data") String data) {
        HousingInfo housingInfo = (HousingInfo) JSONObject.parse(data);
        return MongoUtils.editHousingInfo(_id, housingInfo);
    }

    @RequestMapping(value = "/favorite/list", method = RequestMethod.GET)
    public String getFavoriteList(@RequestParam("user_id") String user_id) {
        List<FavoriteInfo> favoriteInfos = MongoUtils.getFavoriteInfoList(user_id);
        JSONArray array = new JSONArray();
        array.addAll(favoriteInfos);
        return array.toJSONString();
    }

    @RequestMapping(value = "/favorite/remove/{_id}", method = RequestMethod.GET)
    public int removeFavoriteInfo(@RequestParam("user_id") String user_id, @PathVariable("_id") String _id) {
        return MongoUtils.removeFavoriteInfo(_id);
    }
}
