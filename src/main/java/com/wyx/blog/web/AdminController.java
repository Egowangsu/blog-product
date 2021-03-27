package com.wyx.blog.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyx.blog.domain.Blog;
import com.wyx.blog.domain.Type;
import com.wyx.blog.domain.User;
import com.wyx.blog.service.BlogService;
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

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
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

    @PostMapping("/blogs/search")
    public String search(@RequestParam(value = "pageNo",defaultValue ="1")Integer pn, Model model, BlogQuery blog){
        PageHelper.startPage(pn,4);
        List<Blog> blogs=blogService.listPage(blog);
        PageInfo page=new PageInfo(blogs,3);
        model.addAttribute("pageInfo",page); //会放在request域中
        return "admin/blogs :: blogList";  //结合ajax，返回到bolgs下的一个blogList片段，局部刷新
    }

    //发布博客
    @RequestMapping("/input")
    public String input(){
        return "admin/blogs-input";
    }

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }
    //登陆验证
    @PostMapping("/login")
    public String login(@RequestParam("username") String name,
                        @RequestParam("password") String password,
                        HttpSession session,
                        RedirectAttributes attributes){
            User user=userService.checkUser(name, password);
            if(user!=null){
                //登陆成功
                user.setPassword(null);  //不把密码放到session域中，不安全
                session.setAttribute("user", user);
                return "admin/welcome";   //去到欢迎界面
            }else{
                //验证失败
                attributes.addFlashAttribute("message","用户名密码错误！");
                return "redirect:/admin";
            }
    }

    //注销登陆
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        System.out.println("jinlaile");
        return "redirect:/admin";
    }

    @RequestMapping("/typeInput")
        public String typeInput(){
        return "types-input";
    }


}
