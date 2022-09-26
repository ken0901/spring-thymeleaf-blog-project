package com.ken.blog.controller;

import com.ken.blog.entity.Blog;
import com.ken.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blog/write")
    public String blogWriteForm(){
        System.out.println("test");
        return "write";
    }

    @PostMapping("/blog/writePro")
    public String blogWritePro(Blog blog){

        blogService.write(blog);
        return "";
    }

    @GetMapping("/blog/list")
    public String blogList(Model model){
        model.addAttribute("list",blogService.blogList());

        return "list";
    }
}
