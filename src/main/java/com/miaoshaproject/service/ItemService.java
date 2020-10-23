package com.miaoshaproject.service;

import com.alibaba.druid.sql.ast.expr.SQLCaseExpr;
import com.miaoshaproject.dataobject.ItemDo;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.ItemModel;

import java.util.List;

/**
 * 商品接口
 * @author Administrator
 */
public interface ItemService {
    /**
     * @param id
     * @return
     */
    ItemModel getItemById(Integer id);

    /**
     * @return
     * @throws BusinessException
     */
    List<ItemModel> selectItems() throws BusinessException;

    /**
     * @param itemModel
     * @return
     * @throws BusinessException
     */
    ItemModel addItem(ItemModel itemModel) throws BusinessException;

    /**
     * 库存扣减
     *
     * @param itemId
     * @param amount
     * @return
     *
     */
    boolean decreaseStock(Integer itemId,Integer amount);
}
