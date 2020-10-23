package com.miaoshaproject.dao;

import com.miaoshaproject.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public interface UserDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserDO record);

    int insertSelective(UserDO record);

    UserDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKey(UserDO record);

    UserDO selectByTelephone(String telephone);
}