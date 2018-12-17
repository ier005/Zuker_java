/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.workshop.zukerjava.controller;

import com.workshop.zukerjava.bean.User;
import com.workshop.zukerjava.pojo.LoginRequest;
import com.workshop.zukerjava.pojo.LoginResponse;
import com.workshop.zukerjava.service.ExceptionService;
import com.workshop.zukerjava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;


@Controller
public class LoginController {

    private UserService userService;
    private ExceptionService exceptionService;

    @Autowired
    public LoginController(UserService userService, ExceptionService exceptionService) {
        this.userService = userService;
        this.exceptionService = exceptionService;
    }

    @PostMapping("/zuker/user/register")
    public User registerUser(@Valid @RequestBody User user) {

        return userService.registerUser(user);
    }

    @PostMapping("/zuker/user/login")
    public LoginResponse loginUser(@Valid @RequestBody LoginRequest loginRequest) {

        return userService.loginUser(loginRequest);
    }

    @GetMapping("/verifyToken")
    public Long verifyToken(@RequestParam("token")  String token) {

        return userService.verifyToken(token);
    }

    @RequestMapping(value = "/zuker/profile/getProfile", method = RequestMethod.GET)
    public String getProfile(@RequestParam("user_id") String user_id) {
        List<HousingInfo> housingInfos = MongoUtils.getHousingInfoList(user_id);
        JSONArray array = new JSONArray();
        array.addAll(housingInfos);
        return array.toJSONString();
    }
}
