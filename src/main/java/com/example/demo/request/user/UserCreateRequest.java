package com.example.demo.request.user;

/**
 * 创建用户时使用的request对象
 * @author liuqitian
 * @version V1.1 因引入spring security统一管理权限，代码重构
 * @date 2018年7月5日
 */
public class UserCreateRequest {

	/**
	 * 用户登录名
	 */
	private String loginName;

	/**
	 * 用户展示名
	 */
	private String showName;

	/**
	 * 用户邮箱
	 */
	private String email;

	/**
	 * 用户密码
	 */
	private String password;

	/**
	 * 角色id数组
	 */
	private Integer[] roleIds;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Integer[] roleIds) {
		this.roleIds = roleIds;
	}
}
