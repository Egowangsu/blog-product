package com.wyx.blog.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyx.blog.domain.Blog;
import com.wyx.blog.service.BlogService;
import com.wyx.blog.service.TagService;
import com.wyx.blog.service.TypeService;

import com.wyx.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        System.out.println("==============================");
        System.out.println("blog:"+blog);
        List<Blog> blogs=blogService.listPage(blog);
        //用pageInfo对结果进行包装，里面封装了各种属性
        model.addAttribute("types",typeService.listPage());
        PageInfo page=new PageInfo(blogs,3); //5表示连续显示5页,就是下面显示的页数
        model.addAttribute("pageInfo",page); //会放在request域中
        return "admin/blogs";
    }
  //博客查询
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

    @GetMapping("/blogs/{id}/input")  //博客编辑
    public String editInput(@PathVariable("id") Integer id, Model model,HttpServletRequest request){
        String tagIds="";
        model.addAttribute("types",typeService.listPage());
        model.addAttribute("tags",tagService.listPage());
        Blog blog=blogService.getBlog(id);
        model.addAttribute("blog",blog);
        if(!"".equals(request.getParameter("tagIds"))){   //如果穿过来的标签本身就是空，就没必要找了
            String[] str=tagService.getIds(id); //把关系表中该博客的标签全部拿出来
            for(String s:str){
                tagIds+=s+",";
            }
            if (!"".equals(tagIds)){
                tagIds=tagIds.substring(0,tagIds.length()-1);
                model.addAttribute("tagIds",tagIds);
            }
        }

        return "admin/blogs-input";
    }


        //新增时候的提交博客
    @PostMapping("/blogs")
    public String post(Blog blog, HttpSession session, RedirectAttributes attributes, HttpServletRequest request){


        if(blog.getId()!=null){   //如果传过来的blog有id，则是修改
            blog.setTypeId(request.getParameter("type.id"));  //博客的分类id
            //存入标签关系前先把原来的关系清空
            tagService.deleteTagAndBlogRelation(blog.getId());
            String str=request.getParameter("tagIds");
            if(!"".equals(str)){   //当标签id不为空才加上关系
                List<String> list = Arrays.asList(str.split(","));
                for(String res:list){
                    //将博客和标签关系存入
                    tagService.addTagAndBlogRelation(blog.getId(),res);
                }
            }


            Integer count2=blogService.updateBlog(blog.getId(),blog);
            if(count2!=1){
                attributes.addFlashAttribute("message","博客更新失败");
            }else{
                attributes.addFlashAttribute("message","博客更新成功");
            }
            return "redirect:/admin/blogs";
        }

         //如果blogmei没有id则是新增
        //检测博客数量
        Integer count=blogService.getCount();
        while(blogService.getBlog(count)!=null){
            count+=1;
        }
        Integer blogId=count;
        blog.setId(blogId);
        blog.setUserId("1");   //博客所属者id
        blog.setTypeId(request.getParameter("type.id"));  //博客的分类id

        String str=request.getParameter("tagIds");
        if(!"".equals(str)){
            List<String> list = Arrays.asList(str.split(","));
            for(String res:list){
                //将博客和标签关系存入
                tagService.addTagAndBlogRelation(blogId,res);
            }
        }
        Integer count2=blogService.saveBlog(blog);
        if(count2!=1){
            attributes.addFlashAttribute("message","博客添加失败");
        }else{
            attributes.addFlashAttribute("message","博客添加成功");
        }
        return "redirect:/admin/blogs";
    }

    //删除博客
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes){
        blogService.deleteBlog(id);
        redirectAttributes.addFlashAttribute("message","博客删除成功");
        return "redirect:/admin/blogs";
    }

}
