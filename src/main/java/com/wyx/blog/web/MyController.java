package com.wyx.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class MyController {
    //首页
    @RequestMapping("/index")
    public String getIndex(){
        return "index";
    }
    //详情页
    @RequestMapping("/detail")
    public String getDetail(){
        return "blogDetail";
    }
    //分类页
    @RequestMapping("/types")
    public String getType(){
        return "types";
    }
    //标签页
    @RequestMapping("/tags")
    public String getTags(){
        return "tags";
    }

    //归档
    @RequestMapping("/archives")
    public String getArchives(){
        return "archives";
    }

    //关于我
    @RequestMapping("/about")
    public String getAbout(){
        return "about";
    }
}
