package com.miaoshaproject.error;

import com.miaoshaproject.response.CommonReturnType;

/**
 * 自定义异常枚举类
 * @author Administrator
 */

public enum EmBusinessError implements CommonError {
    //1000开头为通用错误类型
    PARAMWTER_VALIDATION_ERROR(10001,"参数不合法"),
    UNKNOW_ERROR(10002,"未知错误"),
    //2000开头为用户信息出错
    USER_NOT_EXIT(20001, "用户不存在"),
    USER_LOGIN_FAIL(20002, "用户手机号或密码不正确"),
    USER_NOT_LOGIN(20003,"用户未登录"),
    //3000开头为商品信息出错
    ITEM_NOT_EXIT(30001,"商品不存在"),
    //4000开头有为交易信息错误
   STOCK_NOT_EBOUGH(40001,"库存不足")
    ;

    private int errCode;
    private String errMsg;

    private EmBusinessError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrorCode() {
        return this.errCode;
    }

    @Override
    public String getErrorMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
