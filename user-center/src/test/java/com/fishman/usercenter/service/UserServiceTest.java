package com.fishman.usercenter.service;
import java.util.Date;

import com.fishman.usercenter.model.domain.User;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author fishman
 * 用户测试
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testAddUser(){

        User user =new User();
        user.setUsername("fishman");
        user.setUserAccount("123");
        user.setAvatarUrl("https://avatars.githubusercontent.com/u/60722058?v=4");
        user.setGender(0);
        user.setUserPassword("123");
        user.setPhone("123");
        user.setEmail("123");

        boolean result=userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);

    }

    @Test
    public void userRegister() {
        String userAccount ="yupi";
        String userPassword ="";
        String checkPassword ="123456";
        long result=userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);
        userAccount ="yu";
        result =userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);
        userAccount ="yupi";
        userPassword ="123456";
        result =userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);
        userAccount ="yu pi";
        userPassword="12345678";
        result =userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);
        checkPassword ="123456789";
        result =userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);
        userAccount= "dogyupi";
        checkPassword ="12345678";
        result =userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);
        userAccount ="yupi";
        result =userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertTrue(result>0);

    }
}