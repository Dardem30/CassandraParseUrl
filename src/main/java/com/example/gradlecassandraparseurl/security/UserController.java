package com.example.gradlecassandraparseurl.security;

import com.example.gradlecassandraparseurl.model.AppUser;
import com.example.gradlecassandraparseurl.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public String signUp(@RequestBody AppUser user) {
        return userService.saveUser(user);
    }
}