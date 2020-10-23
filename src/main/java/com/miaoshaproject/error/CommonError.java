package com.miaoshaproject.error;

import com.miaoshaproject.response.CommonReturnType;

/**
 * 包装器业务异常类
 * @author Administrator
 */
public interface CommonError {
    int getErrorCode();
    String getErrorMsg();
    CommonError setErrMsg(String errMsg);
}
