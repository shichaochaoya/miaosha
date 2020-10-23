package com.miaoshaproject.service.impl;

import com.miaoshaproject.dao.UserDOMapper;
import com.miaoshaproject.dao.UserPasswordDOMapper;
import com.miaoshaproject.dataobject.UserDO;
import com.miaoshaproject.dataobject.UserPasswordDO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.UserModel;
import com.miaoshaproject.validator.ValidationResult;
import com.miaoshaproject.validator.ValidatorImpl;
import com.sun.tools.internal.xjc.reader.dtd.bindinfo.BIUserConversion;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.xml.bind.ValidationEvent;

/**
 * 用户接口实现类
 * @author Administrator
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;

    @Resource
    private UserPasswordDOMapper userPasswordDOMapper;

    @Autowired
    private ValidatorImpl validator;

    /**
     * 查询用户
     * @param id
     * @return
     */
    @Override
    public UserModel getUserById(Integer id) {
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);

        if (userDO == null) {
            return null;
        }
        //获取加密密码信息
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(id);
        return convertFromDataObject(userDO, userPasswordDO);
    }

    /**
     * 用户注册
     * @param userModel
     * @throws BusinessException
     */
    @Override
    @Transactional
    public void register(UserModel userModel) throws BusinessException {
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.PARAMWTER_VALIDATION_ERROR);
        }

        //原始判空方法
        //if (StringUtils.isEmpty(userModel.getName())
        //|| userModel.getGender() == null
        //|| userModel.getAge() == null
        //|| StringUtils.isEmpty(userModel.getTelephone())) {
        //throw new BusinessException(EmBusinessError.PARAMWTER_VALIDATION_ERROR);
        //}

        ValidationResult result = validator.validata(userModel);
        if (result.isHasErrors()){
            throw new BusinessException(result.getErrorMsg(),EmBusinessError.PARAMWTER_VALIDATION_ERROR);
        }

        /* 实现Modle转DATAObject方法 */
        UserDO userDO = convertFromModle(userModel);

        try {
            userDOMapper.insertSelective(userDO);
        } catch (DuplicateKeyException ex) {
            throw new BusinessException("手机号以重复注册", EmBusinessError.PARAMWTER_VALIDATION_ERROR);
        }

        userModel.setId(userDO.getId());

        UserPasswordDO userPasswordDO = convertPasswordFromModle(userModel);
        userPasswordDOMapper.insertSelective(userPasswordDO);

        return;

    }

    /**
     * 校验登录
     * @param telephone
     * @param encrptPassword
     * @return
     * @throws BusinessException
     */
    @Override
    public UserModel validateLogin(String telephone, String encrptPassword) throws BusinessException {
        //通过用户手机获取用户信息
        UserDO userDO = userDOMapper.selectByTelephone(telephone);
        if (userDO == null) {
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        UserModel userModel = convertFromDataObject(userDO, userPasswordDO);
        //比对密码是否一致

        if (!StringUtils.equals(encrptPassword,userModel.getEncrptPassword())){
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        return userModel;

    }

    private UserPasswordDO convertPasswordFromModle(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserPasswordDO userPasswordDO = new UserPasswordDO();
        userPasswordDO.setEncrptPassword(userModel.getEncrptPassword());
        userPasswordDO.setUserId(userModel.getId());
        return userPasswordDO;
    }

    private UserDO convertFromModle(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel, userDO);
        return userDO;
    }

    private UserModel convertFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO) {
        if (userDO == null) {
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);
        if (userPasswordDO != null) {
            userModel.setEncrptPassword(userPasswordDO.getEncrptPassword());
        }

        return userModel;

    }
}
