package com.fishman.usercenter.controller;

import com.fishman.usercenter.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    public long userRegister(){
        userService.userRegister(userAccount, userPassword, checkPassword);
    }
}
