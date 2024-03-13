package com.fishman.user_center.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fishman.user_center.service.UserService;
import com.fishman.user_center.model.domain.User;
import generator.service.UserService;
import com.fishman.user_center.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author fishman
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-03-13 22:43:34
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}




