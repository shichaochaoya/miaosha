package com.miaoshaproject.controller;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 定义通用控制器
 * @author Administrator
 */
public class BaseController {

    static final String CONTENT_TYPE_FROMED = "application/x-www-form-urlencoded";

    //定义ExceptionHandler解决未被controller吸收的的Exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception ex) {
        Map<String, Object> responseData = new HashMap<>();
        CommonReturnType commonReturnType = new CommonReturnType();
        commonReturnType.setStatus("fail");
        if (ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;
            responseData.put("errCode", businessException.getErrorCode());
            responseData.put("errMsg", businessException.getErrorMsg());
        } else {
            responseData.put("errCode", EmBusinessError.UNKNOW_ERROR.getErrorCode());
            responseData.put("errMsg", EmBusinessError.UNKNOW_ERROR.getErrorMsg());
        }
        commonReturnType.setData(responseData);
        return commonReturnType;

    }
}
