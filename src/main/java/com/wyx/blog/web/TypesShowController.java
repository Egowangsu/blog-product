package com.wyx.blog.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyx.blog.domain.Blog;
import com.wyx.blog.domain.Type;
import com.wyx.blog.service.BlogService;
import com.wyx.blog.service.TypeService;
import com.wyx.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TypesShowController {
    @Autowired
    private UserService userService;
    @Autowired
    TypeService typeService;
    @Autowired
    BlogService blogService;
    @GetMapping("/types/{id}")
    public String types(@RequestParam(value = "pageNo",defaultValue = "1") Integer pn, Model model,
                        @PathVariable("id") Integer id,
                        HttpServletRequest request) {
        //先判断id值，如果是从首页的分类点进来的，id值为-1，则查询第一个标签的博客
        //如果id值不为-1，则是标签的id，根据标签的id，查到所有博客
        //查询所有的标签
        List<Type> typeList=typeService.listTypeTop(10000);  //传的够大，就能把所有分类找出来
        if(id==-1){
            //拿第一个标签的id去找博客
            id=typeList.get(0).getId();
        }//若果不是就正常找
        System.out.println("---------------pageNo");
        PageHelper.startPage(pn,1);
        List<Blog> blogList=blogService.getBlogByTypeId(id);
        for(Blog blog:blogList){
            setBlog(blog);
        }
        PageInfo page=new PageInfo(blogList,4);
        model.addAttribute("pageInfo",page);
        model.addAttribute("types",typeList);
        model.addAttribute("activeId",id);  //把当前查询的typeId放进去，前端检测加activeId
        return "types";
    }


    public void setBlog(Blog blog){    //将用户信息，分类名称和更改后的日期放入博客中
        blog.setUser(userService.getUser());
        Type type=typeService.getType(Integer.valueOf(blog.getTypeId()));
        blog.setTypeName(type.getName());
        String newUpdateTime=blog.getUpdateTime().substring(0, 10);  //修改更新日期的格式
        blog.setUpdateTime(newUpdateTime);
    }

    @RequestMapping("/jump")
    public void jump(@RequestParam(value = "pageNo",defaultValue = "1") Integer pn,HttpSession session){
        System.out.println("======pn"+pn);
        session.setAttribute("pageNo", pn);
    }

}
