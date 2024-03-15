package com.fishman.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author fishman
 * 用户请求体    
 */
@Data
public class UserLoginRequest implements Serializable{
    private static final long serialVersionUID=3191241716373120793L;
    private String userAccount;
    private String userPassword;
}
