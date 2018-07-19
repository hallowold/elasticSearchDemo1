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
public class IntegerArrayValidator implements ConstraintValidator<IntegerArray, Object> {

    @Override
    public void initialize(IntegerArray constraintAnnotation) {}

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {   // 实现校验规则
        /*正常情况下，传入的数组肯定是输入String数组，然后由String数组转型的Integer数组传递过来，
         *  所以直接判断是否为Integer[]即可，不对的都是异常数据*/
        //注意，因为业务需求，这里规定空值可以通过校验
        if (null == value) { return true; }
        boolean flag = false;
        if(value instanceof Integer[]) {
            for (Integer data : (Integer[]) value) {
                //这里只应该是1-9位的正整数
                if(data >= 1000000000 || data < 1) { return false; }
            }
        }
        return flag;
    }

}
