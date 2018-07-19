package com.example.demo.common.config.validator;

import com.example.demo.common.config.ValidationStaticValues;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author : liuqitian
 * @date : 2018/7/18 10:12
 * @version : V1.2
 * 校验指定字段是否是httpMethod
 *  调用接口
 */
@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = MethodTypeValidator.class)
@Documented
public @interface MethodType {

    String message() default ValidationStaticValues.START_FLAG + ValidationStaticValues.VALID_METHOD_TYPE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

