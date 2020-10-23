package com.miaoshaproject.dao;

import com.miaoshaproject.dataobject.ItemDo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public interface ItemDoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemDo record);

    int insertSelective(ItemDo record);

    ItemDo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemDo record);

    int updateByPrimaryKey(ItemDo record);


    /**获取所有商品信息
     * @return
     */
    List<ItemDo> selectItems();
}