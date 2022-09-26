package com.ken.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BlogController {

    @GetMapping("/blog/write")
    public String blogWriteForm(){
        System.out.println("test");
        return "write";
    }

    @PostMapping("/blog/writePro")
    public String boardWritePro(String title, String content){
        System.out.println("title: "+title);
        System.out.println("content: "+content);

        return "";
    }
}
