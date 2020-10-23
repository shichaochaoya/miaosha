package com.miaoshaproject.dao;

import com.miaoshaproject.dataobject.UserPasswordDO;

/**
 * @author Administrator
 */
public interface UserPasswordDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPasswordDO record);

    int insertSelective(UserPasswordDO record);

    UserPasswordDO selectByPrimaryKey(Integer id);

    /**
     * 通过用户id直接获取密码
     * @param userId
     * @return
     */
    UserPasswordDO selectByUserId(Integer userId);

    int updateByPrimaryKeySelective(UserPasswordDO record);

    int updateByPrimaryKey(UserPasswordDO record);
}