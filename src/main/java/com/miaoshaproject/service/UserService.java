package com.miaoshaproject.service;

import com.miaoshaproject.controller.viewObject.UserVO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.UserModel;

/**
 * 用户接口
 * @author Administrator
 */
public interface UserService {
    UserModel getUserById(Integer id);

    void register(UserModel userModel) throws BusinessException;

/*    telephone:用户注册手机
    password：用户加密密码*/
    UserModel validateLogin(String telephone,String encrptPassword) throws BusinessException;
}
