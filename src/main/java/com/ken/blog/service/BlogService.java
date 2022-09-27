package com.ken.blog.service;

import com.ken.blog.entity.Blog;
import com.ken.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public void save(Blog blog){
        blogRepository.save(blog);
    }

    public List<Blog> list(){
        return blogRepository.findAll();
    }

    public Blog view(Integer id){
        return blogRepository.findById(id).get();
    }

    public void detele(Integer id){
        blogRepository.deleteById(id);
    }
}
