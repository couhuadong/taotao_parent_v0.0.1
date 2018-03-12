package com.taotao.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.taotao.manager.model.User;
import com.taotao.manager.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Reference
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/save")
    public  String save(){

        User user = new User("xiaohaong1","1234","12345","xh@.com",new Date(),null);

        userService.saveSelective(user);
        return "插入成功！";
    }

    @ResponseBody
    @RequestMapping(value = "/query")
    public  User queryById(String id){

       // User user = new User("xiaohaong","1234","12345","xh@.com",new Date(),null);

        //User user = userService.getOneById(id);
        User user = userService.getOneById(id);
        return user;
    }

}
