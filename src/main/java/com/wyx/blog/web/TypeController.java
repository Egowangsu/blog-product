package com.wyx.blog.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyx.blog.domain.Type;
import com.wyx.blog.exception.TypeNameEmptyException;
import com.wyx.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    @GetMapping("/types/edit")   //点击分类跳转到分类修改页面
    public String edit(){
        return"admin/types-edit";
    }

    @PostMapping("/types")
    public String post(Type type, RedirectAttributes attributes){
        if("".equals(type.getName())||type.getName()==null){
            throw new TypeNameEmptyException("分类为空,请正确执行操作");
        }
        Type t=typeService.getTypeByName(type.getName());

        if(t!=null){
            attributes.addFlashAttribute("message","分类已经存在");
            return "redirect:/admin/types/input";
        }

        int count=typeService.saveType(type);
        if(count!=1){
        attributes.addFlashAttribute("message","添加失败");
        }else{
            attributes.addFlashAttribute("message","添加成功");
        }
            return "redirect:/admin/types";

    }
    //修改标签
        @GetMapping("/types/{id}/input")
    public  String editType(@PathVariable Integer id,Model model){
        model.addAttribute("type",typeService.getType(id));  //存进去
            return "admin/types-edit";
        }


    @PostMapping("/types/{id}")    //修改分类
    public String edit(Type type, RedirectAttributes attributes,@PathVariable Integer id){
        if("".equals(type.getName())||type.getName()==null){
            throw new TypeNameEmptyException("分类为空,请正确执行操作");
        }
        Type t=typeService.getTypeByName(type.getName());

        if(t!=null){
            attributes.addFlashAttribute("message","分类已经存在");
            return "redirect:/admin/types";
        }

        int count=typeService.updateType(type,id);
        if(count!=1){
            attributes.addFlashAttribute("message","修改失败");
        }else{
            attributes.addFlashAttribute("message","修改成功");
        }
        return "redirect:/admin/types";

    }

    //删除标签
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Integer id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }

}
