package com.fishman.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fishman.usercenter.model.domain.User;
import com.fishman.usercenter.service.UserService;
import com.fishman.usercenter.Mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.fishman.usercenter.constant.UserConstant.USER_LOGIN_STATE;

/**
* @author fishman
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-03-14 21:26:02
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService{
    @Resource
    public UserMapper userMapper;

    private static final String SALT="fishman";//加盐

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
        String validPattern="[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
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

    @Override
    public User userLogin(String userAccount, String userPassword,HttpServletRequest request) {
// 1.校验用户的账户、密码是否符合要求
// 1.1.非空校验
        if(StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
// 1.2. 账户长度不小于4位
        if(userAccount.length() < 4) {
            return null;
        }
// 1.3. 密码就不小于8位
        if(userPassword.length() < 8) {
            return null;
        }
// 1.4. 账户不包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
// 如果包含非法字符，则返回
        if(matcher.find()){
            return null;
        }
// 2.校验密码是否输入正确，要和数据库中的密文密码对比去
        String encodePassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes(StandardCharsets.UTF_8));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
// 这里存在bug：会把逻辑删除的用户查出来
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", encodePassword);
        User user = userMapper.selectOne(queryWrapper);
        if(user == null){
            log.info("user login failed, userAccount Cannot match userPassword");
        }
// 3.用户信息脱敏，隐藏敏感信息，防止数据库中的字段泄露
        User safetyUser = null;
        if (user != null) {
            safetyUser = getSafetyUser(user);
        }
// 4.记录用户的登录态（session），将其存到服务器上
        request.getSession().setAttribute(USER_LOGIN_STATE, safetyUser);
// 5.返回脱敏后的用户信息
        return safetyUser;
    }

    /**
     * 用户脱敏
     * @param originUser
     * @return
     */
    @Override
    public User getSafetyUser(User originUser){
        if(originUser==null) return null;
        User safetyUser = new User();
        safetyUser.setId(originUser.getId());
        safetyUser.setUsername(originUser.getUsername());
        safetyUser.setUserAccount(originUser.getUserAccount());
        safetyUser.setAvatarUrl(originUser.getAvatarUrl());
        safetyUser.setGender(originUser.getGender());
        safetyUser.setPhone(originUser.getPhone());
        safetyUser.setEmail(originUser.getEmail());
        safetyUser.setUserRole(originUser.getUserRole());
        safetyUser.setUserStatus(originUser.getUserStatus());
        safetyUser.setCreateTime(originUser.getCreateTime());
// 5.返回脱敏后的用户信息
        return safetyUser;
    }
}




