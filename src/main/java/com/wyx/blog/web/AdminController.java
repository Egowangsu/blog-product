package com.wyx.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
     //管理博客
    @RequestMapping("/management")
    public String management(){
        return "admin/blogs";
    }

    //发布博客
    @RequestMapping("/input")
    public String input(){
        return "admin/blogs-input";
    }
}
