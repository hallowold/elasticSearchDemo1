package com.example.demo.request;

/**
 * 登录时使用的request对象
 * @author liuqitian	
 * @date 2018年6月13日 
 *
 */
public class LogInRequest {
	
	//登录名
	private String loginName;
	
	//密码
	private String password;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
