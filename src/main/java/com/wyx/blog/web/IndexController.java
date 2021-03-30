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
import com.wyx.blog.util.MyDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @GetMapping("/")
    public String index(@RequestParam(value = "pageNo",defaultValue ="1")Integer pn, Model model){

        //传入页码(想调用第几页的数据)以及每页的大小
        PageHelper.startPage(pn,4);
        //startPage后面紧跟的查询就是分页查询
        List<Blog> blogs=blogService.getAllBlogs();  //拿到所有的博客，在页面进行展示
        for(Blog blog:blogs){
           setBlog(blog);
        }

        //用pageInfo对结果进行包装，里面封装了各种属性
        PageInfo page=new PageInfo(blogs,4); //4表示连续显示4页,就是下面显示的页数
        model.addAttribute("pageInfo",page);
        model.addAttribute("types",typeService.listTypeTop(6));   //前端类型展示
        model.addAttribute("tags",tagService.listTagTop(6));         //前端标签展示
        model.addAttribute("recommendBlogs",blogService.listBlogTop(8)); //推荐文章
        return "index";
    }

    @GetMapping("/blog/{id}")     //跳转到博客详情页
        public  String blog(@PathVariable("id") Integer id,Model model){
        Blog blog=blogService.getAndConvert(id);  //markdown,html格式转换
        setBlog2(blog);
        getTags(blog,model);  //将关联的标签拼接字符串返回给前端详情页
        blogService.updateViews(id);   //每访问一次博客，就刷新一次查阅数量
        System.out.println(blog.getViews());
        model.addAttribute("blog",blog);

        return "blogDetail";
        }


        public void setBlog(Blog blog){    //将用户信息，分类名称和更改后的日期放入博客中
            blog.setUser(userService.getUser());
            Type type=typeService.getType(Integer.valueOf(blog.getTypeId()));
            blog.setTypeName(type.getName());
            String newUpdateTime=blog.getUpdateTime().substring(0, 10);  //修改更新日期的格式
            blog.setUpdateTime(newUpdateTime);
        }
         public void setBlog2(Blog blog){    //将用户信息，分类名称和更改后的日期格式放入博客中
        blog.setUser(userService.getUser());
        Type type=typeService.getType(Integer.valueOf(blog.getTypeId()));
        blog.setTypeName(type.getName());
        String newUpdateTime=blog.getUpdateTime().substring(0, 10);  //修改更新日期的格式
        blog.setUpdateTime2(newUpdateTime);
    }

        public void getTags(Blog blog,Model model){  //把关系表中该博客的标签全部拿出来,拼接字符串返回给详情页
               List<Tag> tags=tagService.getTagList(blog.getId());
               if(tags!=null){
                   model.addAttribute("tags",tags);
               }
        }
}
