package com.miaoshaproject.service;

import com.miaoshaproject.service.model.PromoModel;

/**
 * 活动接口
 * @author Administrator
 */
public interface PromoService {

    PromoModel selectByItemId(Integer itemId);
}
