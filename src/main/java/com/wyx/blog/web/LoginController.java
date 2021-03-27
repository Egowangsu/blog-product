package com.wyx.blog.web;

import com.wyx.blog.domain.User;
import com.wyx.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
@RequestMapping("/admin")
@Controller
public class LoginController {
    @Autowired
    private UserService userService;
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
}
