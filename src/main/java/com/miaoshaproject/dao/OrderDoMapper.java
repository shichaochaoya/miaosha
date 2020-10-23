package com.miaoshaproject.dao;

import com.miaoshaproject.dataobject.OrderDo;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public interface OrderDoMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderDo record);

    int insertSelective(OrderDo record);

    OrderDo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderDo record);

    int updateByPrimaryKey(OrderDo record);
}