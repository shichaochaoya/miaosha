package com.miaoshaproject.controller.viewObject;

import com.miaoshaproject.service.model.PromoModel;

import java.math.BigDecimal;

/**
 * 商品前端模型
 * @author Administrator
 */
public class ItemVO {

    private Integer id;            //商品主键id;


    private String title;          //商品名称;

    private BigDecimal price;      //商品价格;

    private Integer stock;         //商品库存;

    private String description;    //商品描述;
    private Integer sales;         //商品销量;

    private String imgUrl;         //商品描述图片的url;

    private Integer promoStatus;   //记录商品是否在秒杀活动中,以及对应的状态：0 没有，2 待开始，3 进行中

    private BigDecimal promoPrice; //秒杀活动商品价格

    private Integer promoId;       //秒杀活动id

    private String startTime;    //秒杀活动开始时间

    private PromoModel promoModel; //判断次此商品是否参加秒杀活动

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getPromoStatus() {
        return promoStatus;
    }

    public void setPromoStatus(Integer promoStatus) {
        this.promoStatus = promoStatus;
    }

    public BigDecimal getPromoPrice() {
        return promoPrice;
    }

    public void setPromoPrice(BigDecimal promoPrice) {
        this.promoPrice = promoPrice;
    }

    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
