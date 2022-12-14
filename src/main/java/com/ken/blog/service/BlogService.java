package com.ken.blog.service;

import com.ken.blog.entity.Blog;
import com.ken.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public void save(Blog blog, MultipartFile file) throws Exception{
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_"+ file.getOriginalFilename();

        File saveFile = new File(projectPath,fileName);
        file.transferTo(saveFile);

        blog.setFilename(fileName);
        blog.setFilepath("/files/" + fileName);

        blogRepository.save(blog);
    }

    public Page<Blog> list(Pageable pageable){

        return blogRepository.findAll(pageable);
    }

    public Page<Blog> search(String keyword, Pageable pageable){
        return blogRepository.findByTitleContaining(keyword, pageable);
    }

    public Blog view(Integer id){
        return blogRepository.findById(id).get();
    }

    public void detele(Integer id){
        blogRepository.deleteById(id);
    }
}
