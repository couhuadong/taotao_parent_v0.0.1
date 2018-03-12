package com.taotao.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.taotao.manager.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/tesst")
public class TestController {

    @Reference
    private TestService testService;

    @ResponseBody
    @RequestMapping(value = "/query")
    public  String query(){
        return testService.queryDate();
    }

}
