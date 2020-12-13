package com.tytanisukcesu.demo.controller;


import com.tytanisukcesu.demo.entity.UserEntity;
import com.tytanisukcesu.demo.repository.UserRepository;
import com.tytanisukcesu.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserEntity> getAll(){
        return userService.getAll();
    }
}
