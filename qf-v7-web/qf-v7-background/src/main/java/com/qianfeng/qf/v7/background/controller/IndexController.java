package com.qianfeng.qf.v7.background.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping("{page}")
    public String index(@PathVariable("page")String page){
        return page;
    }
}
