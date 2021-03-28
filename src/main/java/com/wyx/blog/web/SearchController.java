package com.wyx.blog.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyx.blog.domain.Blog;
import com.wyx.blog.domain.Type;
import com.wyx.blog.service.BlogService;
import com.wyx.blog.service.TagService;
import com.wyx.blog.service.TypeService;
import com.wyx.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @RequestMapping("search")
    public String searchPage(@RequestParam(value = "pageNo",defaultValue ="1")Integer pn,
                             Model model,@RequestParam("query") String query){
        PageHelper.startPage(pn,4);  //到第pn页，一页4个内容
        List<Blog> blogs=blogService.getBlogsBySearch(query);
        for(Blog blog:blogs){
            blog.setUser(userService.getUser());
            Type type=typeService.getType(Integer.valueOf(blog.getTypeId()));
            blog.setTypeName(type.getName());
            String newUpdateTime=blog.getUpdateTime().substring(0, 10);  //修改更新日期的格式
            System.out.println(newUpdateTime);
            blog.setUpdateTime(newUpdateTime);
        }
        PageInfo page=new PageInfo(blogs,4); //把集合封装到page对象里
        model.addAttribute("pageInfo",page);
        model.addAttribute("query",query);
        return "search";
    }
}
