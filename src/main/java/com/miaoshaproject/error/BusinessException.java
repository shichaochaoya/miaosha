package com.miaoshaproject.error;


import com.sun.xml.internal.ws.handler.HandlerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * 包装器业务异常类实现
 * @author Administrator
 */
public class BusinessException extends Exception implements CommonError {

    private CommonError commonError;

    /* 直接接受EmBusiness的传参用于构造异常 */
    public BusinessException(CommonError commonError) {
        super();
        this.commonError = commonError;
    }

    //接受自定义的方式构造异常
    public BusinessException(String message, CommonError commonError) {
        super();
        this.commonError = commonError;
        this.commonError.setErrMsg(message);
    }

    @Override
    public int getErrorCode() {
        return this.commonError.getErrorCode();
    }

    @Override
    public String getErrorMsg() {
        return this.commonError.getErrorMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);
        return this;
    }

}
