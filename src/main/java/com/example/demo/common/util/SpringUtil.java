package com.example.demo.common.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SpringUtil {

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
