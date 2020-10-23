package com.miaoshaproject.dao;

import com.miaoshaproject.dataobject.ItemStockDo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;


/**
 * @author Administrator
 */
public interface ItemStockDoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemStockDo record);

    int insertSelective(ItemStockDo record);

    /**
     * 通过商品id获取库存信息
     * @param itemId
     * @return
     */
    ItemStockDo selectByItemId(Integer itemId);

    ItemStockDo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemStockDo record);

    int updateByPrimaryKey(ItemStockDo record);

    /**
     * 减库存方法
     * @param itemId
     * @param ampount
     * @return
     */
    int decreaseStock(@Param("itemId") Integer itemId, @Param("amount") Integer ampount);
}