package com.esun.backend.repository;

import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;


@Repository
public class PostRespository {
    //放可以用資料庫的工具
    private final JdbcTemplate jdbcTemplate;
    
    //建PostRespository，把東西傳進來
    public PostRespository(JdbcTemplate jdbcTemplate) 
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    //查所有貼文
    public Object getAllPosts() 
    {
        String sql = "SELECT * FROM posts";
        return jdbcTemplate.queryForList(sql);
    }

    //新增貼文
    public int addPost(int userId, String content, String image) 
    {
        String sql = "INSERT INTO posts (user_id, content, image) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, userId, content, image);
    }

    //刪掉貼文
    public int deletePost(int postId) 
    {
    String sql = "DELETE FROM posts WHERE post_id = ?";
    return jdbcTemplate.update(sql, postId);
    }
}
