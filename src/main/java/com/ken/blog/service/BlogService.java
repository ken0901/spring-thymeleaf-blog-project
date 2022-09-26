package com.ken.blog.service;

import com.ken.blog.entity.Blog;
import com.ken.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public void write(Blog blog){
        blogRepository.save(blog);
    }
}
