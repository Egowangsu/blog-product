package com.wyx.blog.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyx.blog.domain.Blog;
import com.wyx.blog.domain.Tag;
import com.wyx.blog.domain.Type;
import com.wyx.blog.service.BlogService;
import com.wyx.blog.service.TagService;
import com.wyx.blog.service.TypeService;
import com.wyx.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TagsShowController {
    @Autowired
    private UserService userService;
    @Autowired
    TagService tagService;
    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;
    @GetMapping("/tags/{id}")
    public String types(@RequestParam(value = "pageNo",defaultValue = "1") Integer pn, Model model,
                        @PathVariable("id") Integer id,
                        HttpSession session) {
        //先判断id值，如果是从首页的分类点进来的，id值为-1，则查询第一个标签的博客
        //如果id值不为-1，则是标签的id，根据标签的id，查到所有博客
        //查询所有的标签
        List<Tag> tagsList=tagService.listTagTop(10000);  //传的够大，就能把所有分类找出来
        if(id==-1){
            //拿第一个标签的id去找博客
            id=tagsList.get(0).getId();
        }//若果不是就正常找
        PageHelper.startPage(pn,1);
        List<Blog> blogList=blogService.getBlogByTagId(id);
        for(Blog blog:blogList){
            setBlog(blog);
        }
        PageInfo page=new PageInfo(blogList,4);
        model.addAttribute("pageInfo",page);
        model.addAttribute("tags",tagsList);
        model.addAttribute("activeId",id);  //把当前查询的typeId放进去，前端检测加activeId
        return "tags";
    }


    public void setBlog(Blog blog){    //将用户信息，分类名称和更改后的日期放入博客中
        blog.setUser(userService.getUser());
        Type type=typeService.getType(Integer.valueOf(blog.getTypeId()));
        blog.setTypeName(type.getName());
        //将其tag集合放入Blog中
        blog.setTag(tagService.getTagList(blog.getId()));
        String newUpdateTime=blog.getUpdateTime().substring(0, 10);  //修改更新日期的格式
        blog.setUpdateTime(newUpdateTime);
    }


}
