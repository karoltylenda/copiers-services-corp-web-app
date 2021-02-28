package com.tytanisukcesu.copiers.controller;

import com.tytanisukcesu.copiers.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/login")
@RequiredArgsConstructor
public class LoginController {

    public void login(@RequestBody User user) {
    }

    public String secured() {
        return "secured";
    }

    
}
