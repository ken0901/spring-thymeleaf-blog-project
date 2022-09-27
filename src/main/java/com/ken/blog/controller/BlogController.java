package com.ken.blog.controller;

import com.ken.blog.entity.Blog;
import com.ken.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        blogService.save(blog);
        return "";
    }

    @GetMapping("/blog/list")
    public String blogList(Model model){
        model.addAttribute("list",blogService.list());
        return "list";
    }

    @GetMapping("/blog/view")
    public String blogView(Model model, Integer id){
        model.addAttribute("blog",blogService.view(id));
        return "view";
    }

    @GetMapping("/blog/delete")
    public String blogDelete(Integer id){
        blogService.detele(id);

        return "redirect:/blog/list";
    }

    @GetMapping("/blog/modify/{id}")
    public String blogModify(@PathVariable Integer id, Model model){
        model.addAttribute("blog",blogService.view(id));
        return "modify";
    }

    @PostMapping("/blog/update/{id}")
    public String blogUpdate(@PathVariable Integer id,Blog blog){
        Blog blogTemp = blogService.view(id);
        blogTemp.setTitle(blog.getTitle());
        blogTemp.setContent(blog.getContent());
        blogService.save(blogTemp);

        return "redirect:/blog/list";
    }

}
