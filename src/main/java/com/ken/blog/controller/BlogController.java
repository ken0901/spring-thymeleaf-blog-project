package com.ken.blog.controller;

import com.ken.blog.entity.Blog;
import com.ken.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blog/write")
    public String blogWriteForm(){
        return "write";
    }

    @PostMapping("/blog/writePro")
    public String blogWritePro(Blog blog, Model model, MultipartFile file) throws Exception{

        blogService.save(blog, file);

        model.addAttribute("message","Your blog was completed");
        model.addAttribute("searchUrl", "/blog/list");

        return "message";
    }

    @GetMapping("/blog/list")
    public String blogList(Model model, @PageableDefault(page = 0, size = 10, sort = "id",
            direction = Sort.Direction.DESC) Pageable pageable){

        Page<Blog> list = blogService.list(pageable);

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage -4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list",list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

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
    public String blogUpdate(@PathVariable Integer id,Blog blog, MultipartFile file) throws Exception{
        Blog blogTemp = blogService.view(id);
        blogTemp.setTitle(blog.getTitle());
        blogTemp.setContent(blog.getContent());
        blogService.save(blogTemp,file);

        return "redirect:/blog/list";
    }

}
