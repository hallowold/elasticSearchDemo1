package com.example.demo.common.config;

import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * 定义一些常量
 * @author liuqitian	
 * @date 2018年6月13日 
 *
 */
public class StaticValues {

	/**
	 * 后台管理系统登陆成功后，记录用户实体的session key
	 */
	public static final String BACKGROUND_USER = "background_user";

	/**
	 * 需要使用字符串进行和系统管理员相关操作时使用
	 */
	public static final String ADMIN = "ADMIN";

	/**
	 * 需要使用字符串进行与查询相关操作时使用
	 */
	public static final String SEARCH = "search";

	/**
	 * 因权限不足抛出异常时候使用
	 */
	public static final String ACCESSDENIED = "当前用户无访问权限";

}
