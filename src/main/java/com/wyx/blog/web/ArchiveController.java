package com.wyx.blog.web;

import com.wyx.blog.domain.Blog;
import com.wyx.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArchiveController {
    @Autowired
    private BlogService blogService;
    @GetMapping("/archives")
    public String archives(Model model){
        Integer blogCount=blogService.getBlogCount();
        model.addAttribute("blogCount",blogCount);
        model.addAttribute("archiveMap",blogService.archiveBLog());
        return "archives";
    }
}
