package com.miaoshaproject.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.*;
import java.util.Set;

/**
 * 实现校验方法
 * @author Administrator
 */
@Component
public class ValidatorImpl implements InitializingBean {

    private Validator validator;

    //实现校验方法并返回校验结果
    public ValidationResult validata(Object bean){
        final ValidationResult validationResult = new ValidationResult();
        Set<ConstraintViolation<Object>> constraintValidatorSet = validator.validate(bean);
        if (constraintValidatorSet.size()>0){
            //有错
            validationResult.setHasErrors(true);
            constraintValidatorSet.forEach(constraintValidation->{
                String errMsg = constraintValidation.getMessage();
                String propertyName = constraintValidation.getPropertyPath().toString();
                validationResult.getErrMsgMap().put(propertyName,errMsg);
            });
        }
        return validationResult;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //将hibernatevalidator通过工厂的初始化方式使其实例化
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
