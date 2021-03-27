package com.wyx.blog.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyx.blog.domain.Tag;
import com.wyx.blog.exception.TagNameEmptyException;
import com.wyx.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;
    @RequestMapping("/tags")
    public String selectEmp(@RequestParam(value = "pageNo",defaultValue ="1")Integer pn, Model model){
        //使用分页查询的插件 pagehelper
        //只需要在执行分页查询之前调用
        //传入页码(想调用第几页的数据)以及每页的大小
        PageHelper.startPage(pn,4);
        //startPage后面紧跟的查询就是分页查询
        List<Tag> tags=tagService.listPage();
        //用pageInfo对结果进行包装，里面封装了各种属性
        PageInfo page=new PageInfo(tags,3); //5表示连续显示5页,就是下面显示的页数
        model.addAttribute("pageInfo",page); //会放在request域中
        return "admin/tags";
    }

    @GetMapping("/tags/input")   //点击分类跳转到标签页面
    public String input(){
        return"admin/tags-input";
    }
    @GetMapping("/tags/edit")   //点击分类跳转到标签修改页面
    public String edit(){
        return"admin/tags-edit";
    }

    @PostMapping("/tags")
    public String post(Tag tag, RedirectAttributes attributes){
        if("".equals(tag.getName())||tag.getName()==null){
            throw new TagNameEmptyException("分类为空,请正确执行操作");
        }
        Tag t=tagService.getTagByName(tag.getName());

        if(t!=null){
            attributes.addFlashAttribute("message","标签已经存在");
            return "redirect:/admin/tags/input";
        }

        int count=tagService.saveTag(tag);
        if(count!=1){
        attributes.addFlashAttribute("message","添加失败");
        }else{
            attributes.addFlashAttribute("message","添加成功");
        }
            return "redirect:/admin/tags";

    }
    //修改标签
        @GetMapping("/tags/{id}/input")
    public  String editTag(@PathVariable Integer id,Model model){
            System.out.println("==============================id是"+id);
        model.addAttribute("tag",tagService.getTag(id));  //存进去
            return "admin/tags-edit";
        }


    @PostMapping("/tags/{id}")    //修改标签
    public String edit(Tag tag, RedirectAttributes attributes,@PathVariable Integer id){
        if("".equals(tag.getName())||tag.getName()==null){
            throw new TagNameEmptyException("分类为空,请正确执行操作");
        }
        Tag t=tagService.getTagByName(tag.getName());

        if(t!=null){
            attributes.addFlashAttribute("message","标签已经存在");
            return "redirect:/admin/tags";
        }

        int count=tagService.updateTag(tag,id);
        if(count!=1){
            attributes.addFlashAttribute("message","修改失败");
        }else{
            attributes.addFlashAttribute("message","修改成功");
        }
        return "redirect:/admin/tags";

    }

    //删除标签
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Integer id,RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }

}
