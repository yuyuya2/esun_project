package com.esun.backend.controller;
import com.esun.backend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository userRepository;
    public UserController(UserRepository userRepository) 
    {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public String register(@RequestBody Map<String, String> user) 
    {
        userRepository.registerUser(
            user.get("userName"),
            user.get("phone"),
            user.get("email"),
            user.get("password")
        );

        return "註冊成功";
    }
    
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> user) {

        int userId = userRepository.loginUser(
            user.get("phone"),
            user.get("password")
        );

         if (userId > 0)
        {
            return String.valueOf(userId);
        }
        else
        {
            return "登入失敗";
        }
    }

}
