package com.example.demo.common.config.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author : liuqitian
 * @date : 2018/7/18 10:12
 * @version : V1.2
 * 校验指定字段是否是httpMethod
 *  实现类
 */
public class MethodTypeValidator implements ConstraintValidator<MethodType, String> {

    private String[] methods = {"GET", "POST", "DELETE", "PUT"};

    @Override
    public void initialize(MethodType constraintAnnotation) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {   // 实现校验规则
        if (null == value) { return false; }
        for (String data : methods) {
            if(data.equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

}
