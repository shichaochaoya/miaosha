package com.miaoshaproject.response;


/**
 * 返回结果归一化处理
 * @author Administrator
 */
public class CommonReturnType {
    //表明对应的返回结果

    private String status;


    /**
     *
     *     若status是success，data返回需要的前端数据
     *     若fail，返回公用的错误码格式
     *
     */
    private Object data;

    /**
     * 定义一个通用的创建方法
     * @param result
     * @return
     */
    public static CommonReturnType create(Object result){
        return CommonReturnType.create(result,"success");
    }


    public static CommonReturnType create(Object result,String status){
        CommonReturnType commonReturnType = new CommonReturnType();
        commonReturnType.setStatus(status);
        commonReturnType.setData(result);
        return commonReturnType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
