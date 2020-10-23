package com.miaoshaproject.service.impl;

import com.miaoshaproject.dao.PromoDoMapper;
import com.miaoshaproject.dataobject.PromoDo;
import com.miaoshaproject.service.PromoService;
import com.miaoshaproject.service.model.PromoModel;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 秒杀模块实现
 * @author Administrator
 */
@Service
public class PromoServiceImpl implements PromoService {

    @Resource
    private PromoDoMapper promoDoMapper;


    /**
     * 获取商品活动信息
     * @param itemId
     * @return
     */
    @Override
    public PromoModel selectByItemId(Integer itemId) {
        PromoDo promoDo = promoDoMapper.selectByItemId(itemId);
        //dataobject->Model
        PromoModel promoModel = convertFromPromoDo(promoDo);
        if (promoModel == null) {
            return null;
        }
        DateTime now = new DateTime();
        if (promoModel.getStartTime().isAfterNow()) {
            promoModel.setStatus(1);
        } else if (promoModel.getEndTime().isBeforeNow()) {
            promoModel.setStatus(3);
        } else {
            promoModel.setStatus(2);
        }
        return promoModel;

    }

    private PromoModel convertFromPromoDo(PromoDo promoDo) {
        if (promoDo == null) {
            return null;
        }
        PromoModel promoModel = new PromoModel();
        BeanUtils.copyProperties(promoDo, promoModel);
        promoModel.setPromoItemPrice(new BigDecimal(promoDo.getPromoItemPrice()));
        promoModel.setStartTime(new DateTime(promoDo.getStartTime()));
        promoModel.setEndTime(new DateTime(promoDo.getEndTime()));

        return promoModel;
    }
}
