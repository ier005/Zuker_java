package com.workshop.zukerjava.controller;

import com.alibaba.fastjson.JSONObject;
import com.workshop.zukerjava.bean.User;
import com.workshop.zukerjava.security.JwtUtils;
import com.workshop.zukerjava.util.MongoUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;


@RestController
@RequestMapping("/user")
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody JSONObject data) {/*
                        @RequestParam("username") String username,
                        @RequestParam("password")String password) {*/
        String username = data.getString("username");
        String password = data.getString("password");
        JSONObject ret = new JSONObject();
        User user = MongoUtils.findUserByName(username);
        if (user == null) {
            return ret.toJSONString();
        }
        if (!user.getPassword().equals(password)){
            return ret.toJSONString();
        }
        ret.put("user_id", user.getUser_id());
        //JWT 新加的
        String JWT = JwtUtils.createToken(user.getUser_id());
        ret.put("token", JWT);
        Cookie cookie = new Cookie("token", JWT);

        return ret.toJSONString();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public int logout(@RequestParam("user_id") String user_id) {
        //TODO logout
        return 0;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public int register(@RequestBody JSONObject data) {
                        /*@RequestParam("email") String email,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("avatar_Path") List <String> avatarPath) {*/
        String email = data.get("email").toString();
        String username = data.get("username").toString();
        String password = data.get("password").toString();
        //String avatarPath = data.get("avatar_Path").toString();

        User sameName = MongoUtils.findUserByName(username);
        User sameEmail = MongoUtils.findUser(email);
        if (sameName == null && sameEmail == null) {
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            //user.setAvatarPath(avatarPath);
            return MongoUtils.register(user);
        }

        return 0;
    }

    @RequestMapping(value = "/forget", method = RequestMethod.POST)
    public int forget(@RequestParam("email") String email,
                        @RequestParam("username") String username,
                        @RequestParam("new_password") String newpassword) {

        User user = MongoUtils.findUserByName(username);
        if (user != null) {
            user.setPassword(newpassword);
            return MongoUtils.forget(user.getUser_id(),newpassword);
        }

        return 0;
    }

    @RequestMapping(value = "/update/password", method = RequestMethod.POST)
    public int updatePassword(@RequestParam("user_id") String user_id,
                      @RequestParam("origin_pwd") String pwd,
                      @RequestParam("new_pwd") String newpwd) {

        User user = MongoUtils.findUser(user_id);
        if (user != null && user.getPassword() == pwd) {
            user.setPassword(newpwd);
            return MongoUtils.updatePassword(user_id,newpwd);
        }

        return 0;
    }

}