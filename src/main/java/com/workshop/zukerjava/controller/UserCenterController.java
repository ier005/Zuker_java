package com.workshop.zukerjava.controller;

import com.workshop.zukerjava.bean.FavoriteInfo;
import com.workshop.zukerjava.bean.HousingInfo;
import com.workshop.zukerjava.bean.Msg;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserCenterController {

    @RequestMapping(value = "/msg/view", method = RequestMethod.GET)
    public List<Msg> viewMsg(@RequestParam("token") String token, @RequestParam("from") String fromUser) {
        return new ArrayList<>();
    }

    @RequestMapping(value = "/msg/send", method = RequestMethod.POST)
    public String sendMsg(@RequestParam("token") String token, @ModelAttribute("to") String toUser,
                          @ModelAttribute("msg") String msg) {
        return "success";
    }

    @RequestMapping(value = "/housing/view/{user}", method = RequestMethod.GET)
    public List<HousingInfo> viewHousing(@RequestParam("token") String token, @PathVariable("user") String user) {
        return new ArrayList<>();
    }

    @RequestMapping(value = "/housing/edit/{ID}", method = RequestMethod.POST)
    public String editHousing(@RequestParam("token") String token, @PathVariable("ID") String housingID,
                              @ModelAttribute("housing") HousingInfo housing) {
        return "success";
    }

    @RequestMapping(value = "/favorite/list", method = RequestMethod.GET)
    public List<FavoriteInfo> getFavoriteList(@RequestParam("token") String token) {
        return new ArrayList<>();
    }

    @RequestMapping(value = "/favorite/remove/{ID}", method = RequestMethod.GET)
    public String getFavoriteList(@RequestParam("token") String token, @PathVariable("ID") String favoriteID) {
        return "success";
    }
}
