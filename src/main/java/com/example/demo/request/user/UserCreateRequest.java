package com.example.demo.request.user;

import java.util.Date;

/**
 * 创建用户时使用的request对象
 * @author liuqitian	
 * @date 2018年6月12日 
 *
 */
public class UserCreateRequest {
	
	//用户id，暂用，解决主键问题后应去除
	private Long id;
	
	//登录名
	private String loginName;

	//登录密码
	private String password;
	
	//用户昵称
	private String userName;
	
	//电话
	private String mobile;
	
	//性别
	private String gender;
	
	//创建日期
	private Date createDate;
	
	//角色id	
	private Long roleId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
}
