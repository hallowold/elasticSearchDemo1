package com.example.demo.exception;

import java.net.BindException;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.common.config.StaticValues;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.example.demo.common.util.ResponseUtil;
import com.example.demo.response.ResponseData;

/**
 * 异常统一捕获处理
 * @author liuqitian
 * @date 2018年6月12日
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 项目自定义异常处理
	 * @param 	req		HttpServletRequest对象
	 * @param 	ex		Demo1Exception异常对象
	 * @return	ResponseData
	 * @throws 	Exception
	 */
	@ExceptionHandler(value = Demo1Exception.class)
	@ResponseBody
	public ResponseData Demo1ExceptionErrorHandler(HttpServletRequest req, Demo1Exception ex) throws Exception {
		ResponseData res = new ResponseData();
		int statusCode = 500;
		String message = ex.getMessage();
		if ("重复".equals(ex.getMessage())) {
			message = "数据重复，有无效数据存在";
		} else if ("重复的登录名".equals(ex.getMessage())) {
			message = "该登录名已被占用，请修改";
		} else if (StaticValues.ADMIN.equals(ex.getMessage())) {
			message = "不可对系统管理员角色进行新增，修改或删除操作";
		} else if (StaticValues.ACCESSDENIED.equals(ex.getMessage())) {
			message = "当前用户无权限";
		}
	    res = ResponseUtil.createResponseData(false, message, null, statusCode);
		return res;
	}

	/**
	 * 默认的异常处理
	 * @param 	req		HttpServletRequest对象
	 * @param 	ex		异常对象
	 * @return	responseData
	 * @throws 	Exception
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResponseData defaultErrorHandler(HttpServletRequest req, Exception ex) throws Exception {
		ResponseData res = new ResponseData();
		int statusCode = 0;

		//判断异常类型
        if (ex instanceof HttpRequestMethodNotSupportedException) {
        	//405
            statusCode = HttpServletResponse.SC_METHOD_NOT_ALLOWED;
        } else if (ex instanceof HttpMediaTypeNotSupportedException) {
        	//415
            statusCode = HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE;
        } else if (ex instanceof HttpMediaTypeNotAcceptableException) {
        	//406
            statusCode = HttpServletResponse.SC_NOT_ACCEPTABLE;
        } else if (ex instanceof MissingServletRequestParameterException) {
        	//400
            statusCode = HttpServletResponse.SC_BAD_REQUEST;
        } else if (ex instanceof ServletRequestBindingException) {
            statusCode = HttpServletResponse.SC_BAD_REQUEST;
        } else if (ex instanceof TypeMismatchException) {
            statusCode = HttpServletResponse.SC_BAD_REQUEST;
        } else if (ex instanceof HttpMessageNotReadableException) {
            statusCode = HttpServletResponse.SC_BAD_REQUEST;
        } else if (ex instanceof HttpMessageNotWritableException) {
            statusCode = HttpServletResponse.SC_BAD_REQUEST;
        } else if (ex instanceof MethodArgumentNotValidException) {
            statusCode = HttpServletResponse.SC_BAD_REQUEST;
        } else if (ex instanceof MissingServletRequestPartException) {
            statusCode = HttpServletResponse.SC_BAD_REQUEST;
        } else if (ex instanceof BindException) {
            statusCode = HttpServletResponse.SC_BAD_REQUEST;
        } else if (ex instanceof NoHandlerFoundException) {
        	//404
            statusCode = HttpServletResponse.SC_NOT_FOUND;
        } else if (ex instanceof ConversionNotSupportedException) {
        	//500
            statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        } else if (ex instanceof NoSuchElementException) {
        	//500
            statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
            return ResponseUtil.createResponseData(false, "未找到指定数据", null, statusCode);
        }else {
        	//非已判断出的异常，需要跟进
        	statusCode = 0;
        }

	    res = ResponseUtil.createResponseData(false, ex.getMessage(), null, statusCode);
		return res;
	}

}
