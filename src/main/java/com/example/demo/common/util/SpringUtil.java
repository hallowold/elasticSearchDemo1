package com.example.demo.common.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @Auther: liuqitian
 * @Date: 2018/6/21 9:59
 * @Version: V1.0
 * @Description: Spring工具类
 */
public class SpringUtil {

	/**
	 * @Auther: liuqitian
	 * @Date: 2018/6/21 10:00
	 * @Version: V1.0
	 * @return: javax.servlet.http.HttpServletRequest
	 * @Description: 用于获取javax.servlet.http.HttpServletRequest
	 */
	public static HttpServletRequest getRequest() {
		ServletRequestAttributes localServletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest localHttpServletRequest = null;
		if (localServletRequestAttributes != null) {
			localHttpServletRequest = localServletRequestAttributes.getRequest();
		}
		return localHttpServletRequest;
	}

}
