package com.miaoshaproject.dao;

import com.miaoshaproject.dataobject.PromoDo;

/**
 * @author Administrator
 */
public interface PromoDoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PromoDo record);

    int insertSelective(PromoDo record);

    PromoDo selectByPrimaryKey(Integer id);

    /**
     * 获取商品的活动id
     * @param itemId
     * @return
     */
    PromoDo selectByItemId(Integer itemId);

    int updateByPrimaryKeySelective(PromoDo record);

    int updateByPrimaryKey(PromoDo record);
}