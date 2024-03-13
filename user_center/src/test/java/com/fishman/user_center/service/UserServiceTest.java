package com.fishman.user_center.service;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 用户测试
 * @author fishman
 *
 */
@SpringBootTest
class UserServiceTest {
    @Resource
    private UserService userService;
    @Test
    public void testAddUser(){
        User user =new user();

    }
}