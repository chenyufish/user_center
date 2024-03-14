package com.fishman.usercenter.service;

import com.fishman.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author fishman
 * 用户服务
*/
public interface UserService extends IService<User> {
    /**
     *
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 经验吗
     * @return 新用户id
     */
    long userRegister(String userAccount,String userPassword,String checkPassword);

}
