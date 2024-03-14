package com.fishman.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fishman.usercenter.model.domain.User;
import com.fishman.usercenter.service.UserService;
import com.fishman.usercenter.Mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @author fishman
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-03-14 21:26:02
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService{
    @Resource
    public UserMapper userMapper;
    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
// 1.校验用户的账户、密码、校验密码，是否符合要求
// 1.1.非空校验
        if(StringUtils.isAnyBlank(userAccount, userPassword, checkPassword))
        {
            return -1;
        }
// 1.2. 账户长度不小于4位
        if(userAccount.length() < 4) {
            return -1;
        }
// 1.3. 密码就不小于8位
        if(userPassword.length() < 8) {
            return -1;
        }
// 1.4. 账户不包含特殊字符
        String validPattern="[`~!@#$%^&*()+=|{}'：；,\\\\.<>/?（）【】‘“。，、？”]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
// 如果包含非法字符，则返回
        if(matcher.find()){
            return -1;
        }
// 1.5. 密码和校验密码相同
        if(!userPassword.equals(checkPassword)) {
            return  -1;
        }
// 1.6. 账户不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        long count = userMapper.selectCount(queryWrapper);
        if(count > 0) {
            return  -1;
        }
// 2.对密码进行加密（密码千万不要直接以明文存储到数据库中）
        final String SALT ="fishman";
        String verifyPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
// 3. 向数据库插入用户数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(verifyPassword);
        boolean savaResult =this.save(user);
        if(!savaResult){
            return -1;
        }
        return user.getId();
    }
}




