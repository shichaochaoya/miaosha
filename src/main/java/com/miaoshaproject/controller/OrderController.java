package com.miaoshaproject.controller;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.OrderService;
import com.miaoshaproject.service.model.OrderModel;
import com.miaoshaproject.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 订单controller
 * @author Administrator
 */
@RequestMapping("/order")
@Controller("order")
@CrossOrigin(origins = {"*"}, allowCredentials = "true")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    HttpServletRequest httpServletRequest;

    /**
     * 商品下单请求
     * @param itemId
     * @param amount
     * @param promoId
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/createorder",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FROMED})
    @ResponseBody
    public CommonReturnType createOrder(@RequestParam(name = "itemId") Integer itemId,
                                        @RequestParam(name = "amount") Integer amount,
                                        @RequestParam(name = "promoId",required = false) Integer promoId) throws BusinessException {
        //获取用户登录信息
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if (isLogin == null || !isLogin.booleanValue()) {
            throw new BusinessException("用户未登录,不能下单", EmBusinessError.USER_NOT_LOGIN);
        }
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        //下单
        OrderModel orderModel = orderService.createOrder(userModel.getId(), itemId,promoId, amount);
        return CommonReturnType.create(null);
    }
}
