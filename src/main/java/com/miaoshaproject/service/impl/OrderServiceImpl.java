package com.miaoshaproject.service.impl;

import com.miaoshaproject.dao.OrderDoMapper;
import com.miaoshaproject.dao.SequenceDoMapper;
import com.miaoshaproject.dataobject.OrderDo;
import com.miaoshaproject.dataobject.SequenceDo;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.service.ItemService;
import com.miaoshaproject.service.OrderService;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.ItemModel;
import com.miaoshaproject.service.model.OrderModel;
import com.miaoshaproject.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.Resources;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 订单实现类
 * @author Administrator
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderDoMapper orderDoMapper;

    @Autowired
    private SequenceDoMapper sequenceDoMapper;

    @Override
    public OrderModel getOrderById(String id) {
        return null;
    }

    /**
     * 创建订单
     *
     * @param uid
     * @param itemid
     * @param amount
     * @return
     * @throws BusinessException
     */
    @Override
    @Transactional
    public OrderModel createOrder(Integer uid, Integer itemid, Integer promoId, Integer amount) throws BusinessException {
        //校验下单状态,下单的商品是否存在，用户书否合法，购买数量是否正确
        ItemModel itemModel = itemService.getItemById(itemid);
        if (itemModel == null) {
            throw new BusinessException("商品信息不存在", EmBusinessError.PARAMWTER_VALIDATION_ERROR);
        }
        UserModel userModel = userService.getUserById(uid);
        if (userModel == null) {
            throw new BusinessException("用户信息不存在", EmBusinessError.PARAMWTER_VALIDATION_ERROR);
        }
        if (amount <= 0 || amount > 99) {
            throw new BusinessException("数量信息不正确", EmBusinessError.PARAMWTER_VALIDATION_ERROR);
        }

        //校验活动信息
        if (promoId != null) {
            //校验活动是否适用商品
            if (itemModel.getPromoModel().getId() != promoId.intValue()) {
                throw new BusinessException("活动信息不正确", EmBusinessError.PARAMWTER_VALIDATION_ERROR);
                //校验活动是否正在进行
            } else if (itemModel.getPromoModel().getStatus().intValue() != 2) {
                throw new BusinessException("活动未开始", EmBusinessError.PARAMWTER_VALIDATION_ERROR);
            }
        }
        //落单减库存/支付减库存
        boolean result = itemService.decreaseStock(itemid, amount);
        if (!result) {
            throw new BusinessException(EmBusinessError.STOCK_NOT_EBOUGH);
        }
        //订单入库
        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(uid);
        orderModel.setAmount(amount);
        orderModel.setItemId(itemid);
        if (promoId != null) {
            orderModel.setItemPrice(itemModel.getPromoModel().getPromoItemPrice());
        }else {
            orderModel.setItemPrice(itemModel.getPrice());

        }
        orderModel.setOrderPrice(orderModel.getItemPrice().multiply(new BigDecimal(amount)));
        orderModel.setPromoId(promoId);
        //生成订单号
        orderModel.setId(generateOrderNo());

        OrderDo orderDo = convertFromModel(orderModel);
        orderDoMapper.insertSelective(orderDo);
        //返回前端
        return orderModel;

    }


    /**
     * 生成订单号
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    String generateOrderNo() {
        /*订单号有16位,*/
        StringBuilder stringBuilder = new StringBuilder();
        /*前八位为时间信息*/
        LocalDateTime now = LocalDateTime.now();
        String nowDate = now.format(DateTimeFormatter.ISO_DATE).replace("-", "");
        stringBuilder.append(nowDate);

        /*中间6位为自增序列*/
        /*获取当前sequence*/
        int sequence = 0;
        SequenceDo s = sequenceDoMapper.getSequenceByName("order_info");
        sequence = s.getCurrentValue();
        s.setCurrentValue(s.getCurrentValue() + s.getStep());
        sequenceDoMapper.updateByPrimaryKeySelective(s);

        String sequenceStr = String.valueOf(sequence);
        for (int i = 0; i < 6 - sequenceStr.length(); i++) {
            stringBuilder.append(0);
        }
        stringBuilder.append(sequenceStr);

        /*最后两位为分库分表位,暂时写死*/
        stringBuilder.append("00");

        return stringBuilder.toString();
    }

    /**
     *订单模型转订单原型
     * @param orderModel
     * @return
     */
    private OrderDo convertFromModel(OrderModel orderModel) {
        if (orderModel == null) {
            return null;
        }
        OrderDo orderDo = new OrderDo();
        BeanUtils.copyProperties(orderModel, orderDo);
        orderDo.setItemPrice(orderModel.getItemPrice().doubleValue());
        orderDo.setOrderPrice(orderModel.getOrderPrice().doubleValue());
        return orderDo;
    }
}
