package com.wyx.blog.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyx.blog.domain.Blog;
import com.wyx.blog.domain.Type;
import com.wyx.blog.domain.User;
import com.wyx.blog.service.BlogService;
import com.wyx.blog.service.TagService;
import com.wyx.blog.service.TypeService;
import com.wyx.blog.service.UserService;
import com.wyx.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
     //管理博客
    @RequestMapping("/blogs")
    public String blogs(@RequestParam(value = "pageNo",defaultValue ="1")Integer pn, Model model, BlogQuery blog){
        //使用分页查询的插件 pagehelper
        //只需要在执行分页查询之前调用
        //传入页码(想调用第几页的数据)以及每页的大小
        PageHelper.startPage(pn,4);
        //startPage后面紧跟的查询就是分页查询
        List<Blog> blogs=blogService.listPage(blog);
        //用pageInfo对结果进行包装，里面封装了各种属性
        model.addAttribute("types",typeService.listPage());
        PageInfo page=new PageInfo(blogs,3); //5表示连续显示5页,就是下面显示的页数
        model.addAttribute("pageInfo",page); //会放在request域中
        return "admin/blogs";
    }

    @RequestMapping("/blogs/search")
    public String search(@RequestParam(value = "pageNo",defaultValue ="1")Integer pn, Model model, BlogQuery blog){
        PageHelper.startPage(pn,4);
        List<Blog> blogs=blogService.listPage(blog);
        PageInfo page=new PageInfo(blogs,3);
        model.addAttribute("pageInfo",page); //会放在request域中
        return "admin/blogs :: blogList";  //结合ajax，返回到bolgs下的一个blogList片段，局部刷新
    }

    //发布博客
    @RequestMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("types",typeService.listPage());
        model.addAttribute("tags",tagService.listPage());
        model.addAttribute("blog",new Blog());

        return "admin/blogs-input";
    }

        //新增时候的提交博客
    @PostMapping("/blogs")
    public String post(Blog blog, HttpSession session, RedirectAttributes attributes, HttpServletRequest request){
        //检测博客数量
        Integer count=blogService.getCount();
        Integer blogId=count+1;
        blog.setId(blogId);
        blog.setUserId("1");   //博客所属者id
        blog.setTypeId(request.getParameter("type.id"));  //博客的分类id
        String str=request.getParameter("tagIds");
        List<String> list = Arrays.asList(str.split(","));
        System.out.println("============================"+blog);
        for(String res:list){
             //将博客和标签关系存入
            tagService.addTagAndBlogRelation(blogId,res);
        }
        Integer count2=blogService.saveBlog(blog);
        if(count2!=1){
            attributes.addFlashAttribute("message","博客添加失败");
        }else{
            attributes.addFlashAttribute("message","博客添加成功");
        }
        return "redirect:/admin/blogs";
    }

}
