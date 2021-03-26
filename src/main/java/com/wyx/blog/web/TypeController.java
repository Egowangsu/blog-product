package com.wyx.blog.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyx.blog.domain.Type;
import com.wyx.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;
    @RequestMapping("/types")
    public String selectEmp(@RequestParam(value = "pageNo",defaultValue ="1")Integer pn, Model model){
        //使用分页查询的插件 pagehelper
        //只需要在执行分页查询之前调用
        //传入页码(想调用第几页的数据)以及每页的大小
        PageHelper.startPage(pn,4);
        //startPage后面紧跟的查询就是分页查询
        List<Type> types=typeService.listPage();
        //用pageInfo对结果进行包装，里面封装了各种属性
        PageInfo page=new PageInfo(types,3); //5表示连续显示5页,就是下面显示的页数
        model.addAttribute("pageInfo",page); //会放在request域中
        return "admin/types";
    }

    @GetMapping("/types/input")   //点击分类跳转到分类页面
    public String input(){
        return"admin/types-input";
    }

    @PostMapping("/types")
    public String post(Type type, RedirectAttributes attributes){
        int count=typeService.saveType(type);
        if(count!=1){
        attributes.addFlashAttribute("message","添加失败");
            System.out.println("==================="+attributes.getFlashAttributes());
        }else{
            attributes.addFlashAttribute("message","添加成功");
            System.out.println("==================="+attributes.getFlashAttributes());
        }
            return "redirect:/admin/types";

    }
}
