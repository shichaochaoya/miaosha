package com.miaoshaproject.service.impl;

import com.miaoshaproject.dao.ItemDoMapper;
import com.miaoshaproject.dao.ItemStockDoMapper;
import com.miaoshaproject.dao.PromoDoMapper;
import com.miaoshaproject.dataobject.*;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.service.ItemService;
import com.miaoshaproject.service.PromoService;
import com.miaoshaproject.service.model.ItemModel;
import com.miaoshaproject.service.model.PromoModel;
import com.miaoshaproject.service.model.UserModel;
import com.miaoshaproject.validator.ValidationResult;
import com.miaoshaproject.validator.ValidatorImpl;
import com.sun.tools.internal.xjc.reader.dtd.bindinfo.BIUserConversion;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品业务实现类
 * @author Administrator
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    private ItemDoMapper itemDoMapper;

    @Autowired
    private ValidatorImpl validator;

    @Resource
    private ItemStockDoMapper itemStockDoMapper;

    @Resource
    private PromoService promoService;

    /**
     * 查询商品
     * @param id
     * @return
     */
    @Override
    public ItemModel getItemById(Integer id){
        ItemDo itemDo = itemDoMapper.selectByPrimaryKey(id);
        if (itemDo == null) {
            return null;
        }
        ItemStockDo itemStockDo = itemStockDoMapper.selectByItemId(itemDo.getId());
        //转化为Model
        ItemModel itemModel = convertModelFromDataObject(itemDo,itemStockDo);
        //获取活动商品信息
        PromoModel promoModel = promoService.selectByItemId(itemModel.getId());
        if (promoModel != null && promoModel.getStatus() != 3) {
            itemModel.setPromoModel(promoModel);
        }
        return itemModel;
    }

    /**
     * 获取商品列表
     * @return
     */
    @Override
    public List<ItemModel> selectItems() {
        List<ItemDo> items = itemDoMapper.selectItems();
        /*List<ItemModel> models = new ArrayList<>();
        for (ItemDo itemDo : items) {
            models.add(convertModelFromDataObject(itemDo,itemStockDoMapper.selectByItemId(itemDo.getId())));
        }*/
        /* lambda表达式 */
        List<ItemModel> models = items.stream().map(itemDo -> {
            ItemStockDo itemStockDo = itemStockDoMapper.selectByItemId(itemDo.getId());
            ItemModel itemModel = convertModelFromDataObject(itemDo,itemStockDo);
            return itemModel;
        }).collect(Collectors.toList());
        return models;
    }

    /**
     * 创建商品
     * @param itemModel
     * @return
     * @throws BusinessException
     */
    @Transactional
    @Override
    public ItemModel addItem(ItemModel itemModel) throws BusinessException {
        //校验入参
        ValidationResult validationResult = validator.validata(itemModel);
        if (validationResult.isHasErrors()) {
            throw new BusinessException(validationResult.getErrorMsg(),EmBusinessError.PARAMWTER_VALIDATION_ERROR);
        }
        //将Model转换为DataObject
        ItemDo itemDo = this.convertItemDOFromItemModel(itemModel);
        //写入数据库
        itemDoMapper.insertSelective(itemDo);
        itemModel.setId(itemDo.getId());

        ItemStockDo itemStockDo = this.convertItemStockDoFromItemModel(itemModel);
        itemStockDoMapper.insertSelective(itemStockDo);
        //返回创建完成的对象
        return this.getItemById(itemModel.getId());
    }

    /**
     * 减库存
     *
     * @param itemId
     * @param amount
     * @return
     */
    @Override
    @Transactional
    public boolean decreaseStock(Integer itemId, Integer amount) {
        int affectedRow = itemStockDoMapper.decreaseStock(itemId,amount);
        if (affectedRow > 0) {
            /* 更新库存成功 */
            return true;
        }else {
            /* 更新库存失败 */
            return false;
        }
    }


    /**
     * 商品模型转商品原型
     * @param itemModel
     * @return
     */
    private ItemDo convertItemDOFromItemModel(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemDo itemDo = new ItemDo();
        BeanUtils.copyProperties(itemModel, itemDo);
        itemDo.setPrice(itemModel.getPrice().doubleValue());
        return itemDo;
    }

    /**
     * 商品模型转库存原型
     * @param itemModel
     * @return
     */
    private ItemStockDo convertItemStockDoFromItemModel(ItemModel itemModel){
        if (itemModel == null) {
            return null;
        }
        ItemStockDo itemStockDo = new ItemStockDo();
        itemStockDo.setItemId(itemModel.getId());
        itemStockDo.setStock(itemModel.getStock());
        return itemStockDo;
    }

    /**
     * 构建商品模型
     * @param itemDo
     * @param itemStockDo
     * @return
     */
    private ItemModel convertModelFromDataObject(ItemDo itemDo,ItemStockDo itemStockDo){
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDo,itemModel);
        itemModel.setPrice(new BigDecimal(itemDo.getPrice()));
        itemModel.setStock(itemStockDo.getStock());
        return itemModel;
    }


}
