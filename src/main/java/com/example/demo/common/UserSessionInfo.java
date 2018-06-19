package com.example.demo.common;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.example.demo.common.config.StaticValues;
import com.example.demo.common.util.SpringUtil;
import com.example.demo.entity.User;

/**
 * 管理session中的用户信息
 * @author liuqitian	
 * @date 2018年6月13日 
 *
 */
public class UserSessionInfo {
	
	private static final Log LOGGER = LogFactory.getLog(UserSessionInfo.class);

	/**
	 * 向session中存放当前后台管理系统用户
	 * 
	 * @param request 	HttpServletRequest
	 * @param user 		用户
	 */
	public static synchronized void setBackgroundUserInfo(User user) {
		try {
			if (user != null) {
				HttpSession session = SpringUtil.getRequest().getSession();
				// 默认设置用户session超时时间为半个小时
				session.setMaxInactiveInterval(1800);
				session.setAttribute(StaticValues.BACKGROUND_USER, user);
				LOGGER.info("后台用户登录：" + user.getLoginName());
			}
		} catch (Exception e) {
			LOGGER.error("向session中存储后台系统用户信息时报错:", e);
		}
	}
	
	/**
	 * 将session中存放的当前后台管理系统用户删除
	 */
	public static synchronized void removeBackgroundUserInfo() {
		LOGGER.info("后台用户注销：" + getBackgroundUserInfo().getLoginName());
		SpringUtil.getRequest().getSession().removeAttribute(StaticValues.BACKGROUND_USER);
	}

	/**
	 * 从session中获取当前后台管理系统用户
	 * 
	 * @param 	request 	HttpServletRequest
	 * @return 	user		用户
	 */
	public static synchronized User getBackgroundUserInfo() {
		HttpSession session = SpringUtil.getRequest().getSession(false);
		User user = null;
//		LOGGER.info("获取后台用户测试");
		if (session != null && session.getAttribute(StaticValues.BACKGROUND_USER) != null) {
			user = (User) session.getAttribute(StaticValues.BACKGROUND_USER);
//			LOGGER.info("获取后台用户：" + user.getLoginName());
		}
		return user;
	}
	
}
