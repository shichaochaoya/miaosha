package com.miaoshaproject.service.model;

import java.math.BigDecimal;

/**
 * 订单模型
 * @author Administrator
 */
public class OrderModel {
    /**
     * 订单号需要有明确属性 20181021000（有属性的）
     */
    private String id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 商品Id
     */
    private Integer itemId;
    /**
     * 购买商品的单价
     */
    private BigDecimal itemPrice;

    /**
     * 数量
     */
    private Integer amount;

    /**
     * 金额
     */
    private BigDecimal orderPrice;

    /**
     * 若非空表示用秒杀方式下单
     */
    private Integer promoId;


    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }
}
