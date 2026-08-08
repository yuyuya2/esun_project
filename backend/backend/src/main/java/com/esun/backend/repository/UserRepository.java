package com.esun.backend.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
    
@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;
    public UserRepository(JdbcTemplate jdbcTemplate) 
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int registerUser(String userName, String phone, String email, String password) 
    {
        String sql = "INSERT INTO users (user_name, phone, email, password) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, userName, phone, email, password);
    }
    
    public int loginUser(String phone, String password)
    {
        String sql = "SELECT user_id FROM users WHERE phone = ? AND password = ?";

        try
        {
            return jdbcTemplate.queryForObject(sql, Integer.class, phone, password);
        }
        catch (Exception e)
        {
            return 0;
        }
    }

}
