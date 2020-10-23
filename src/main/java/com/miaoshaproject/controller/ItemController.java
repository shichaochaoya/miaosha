package com.miaoshaproject.controller;


import com.miaoshaproject.controller.viewObject.ItemVO;
import com.miaoshaproject.controller.viewObject.UserVO;
import com.miaoshaproject.dao.ItemDoMapper;
import com.miaoshaproject.dao.PromoDoMapper;
import com.miaoshaproject.dataobject.ItemDo;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.ItemService;
import com.miaoshaproject.service.model.ItemModel;
import com.miaoshaproject.service.model.UserModel;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品controller
 * @author Administrator
 */
@Controller("item")
@RequestMapping("/item")
//处理跨域请求
@CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
public class ItemController extends BaseController {
    @Autowired
    private ItemService itemService;


    /**
     * 获取商品列表
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public CommonReturnType listItems() throws BusinessException {
        List<ItemModel> items = itemService.selectItems();
        List<ItemVO> itemVOS = items.stream().map(itemModel -> {
            ItemVO itemVO = convertFromModel(itemModel);
            return itemVO;
        }).collect(Collectors.toList());
        return CommonReturnType.create(itemVOS);
    }


    /**
     * 商品下单
     * @param title
     * @param description
     * @param price
     * @param stock
     * @param imgUrl
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/create", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FROMED})
    @ResponseBody
    public CommonReturnType createItem(@RequestParam(name = "title") String title,
                                       @RequestParam(name = "description") String description,
                                       @RequestParam(name = "price") BigDecimal price,
                                       @RequestParam(name = "stock") Integer stock,
                                       @RequestParam(name = "imgUrl") String imgUrl) throws BusinessException {
        //封装service请求来创建商品
        ItemModel itemModel = new ItemModel();
        itemModel.setTitle(title);
        itemModel.setDescription(description);
        itemModel.setPrice(price);
        itemModel.setStock(stock);
        itemModel.setImgUrl(imgUrl);

        ItemModel itemModel1ForReturn = itemService.addItem(itemModel);

        //返回给前端
        ItemVO itemVO = convertFromModel(itemModel1ForReturn);

        return CommonReturnType.create(itemVO);
    }

    /**
     * 查找商品信息
     * 采用restful风格接口
     * @param id
     * @return
     * @throws BusinessException
     */
    @GetMapping("/getitem")
    @ResponseBody
    public CommonReturnType getItemById(@RequestParam(name = "id") Integer id) throws BusinessException {
        ItemModel itemModel = itemService.getItemById(id);
        if (itemModel == null) {
            throw new BusinessException(EmBusinessError.ITEM_NOT_EXIT);
        }
        ItemVO itemVO = convertFromModel(itemModel);
        return CommonReturnType.create(itemVO);
    }

    /**
     * 商品模型模型转前端模型
     * @param itemModel
     * @return
     */
    private ItemVO convertFromModel(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(itemModel, itemVO);
        if (itemModel.getPromoModel() != null) {
            itemVO.setPromoPrice(itemModel.getPromoModel().getPromoItemPrice());
            itemVO.setPromoStatus(itemModel.getPromoModel().getStatus());
            itemVO.setStartTime(itemModel.getPromoModel().getStartTime().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
            itemVO.setPromoId(itemModel.getPromoModel().getId());
        } else {
            itemVO.setPromoStatus(0);
        }
        return itemVO;
    }

}










