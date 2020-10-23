package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.OrderModel;

/**
 * 订单接口
 * @author Administrator
 */
public interface OrderService  {
    OrderModel getOrderById(String id);

    OrderModel createOrder(Integer uid,Integer itemid,Integer promoId,Integer amount) throws BusinessException;
}
