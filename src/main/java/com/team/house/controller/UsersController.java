package com.team.house.controller;


import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.service.UserService;
import com.team.house.util.UserConditioin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 王建兵
 * @Classname UsersController
 * @Description TODO
 * @Date 2019/8/13 10:38
 * @Created by Administrator
 */
@Controller
@RequestMapping("/admin")
public class UsersController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getRUser")
    @ResponseBody
    public Map<String,Object> getRUser(UserConditioin conditioin){

        PageInfo<Users> pageInfo=userService.getAllRUsers(conditioin);
        //返回结果
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
}
