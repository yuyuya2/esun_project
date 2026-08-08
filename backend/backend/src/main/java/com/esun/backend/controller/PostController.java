package com.esun.backend.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.esun.backend.repository.PostRespository;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*")
public class PostController {
    
    //要用respository
    @Autowired
    private PostRespository postRespository;

    @GetMapping
    public Object getPosts() 
    {
        return postRespository.getAllPosts();
    }

    @PostMapping
    public String addPost(@RequestBody Map<String, String> post) {

        int userId = Integer.parseInt(post.get("userId"));
        String content = post.get("content");
        String image = post.get("image");
        postRespository.addPost(userId, content, image);
        return "發文成功";
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable int id) 
    {
        postRespository.deletePost(id);
        return "刪除成功";
    }

}
