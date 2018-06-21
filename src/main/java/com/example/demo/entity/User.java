package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 用户实体类
 * @author liuqitian
 * @date 2018年6月11日
 *
 */
@Document(indexName = "user")
public class User implements Serializable {
	
	//序列化
	private static final long serialVersionUID = 1L;
	
	@Id
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
	
	//最后修改日期
	private Date lastChangeDate;
	
	//角色
	private Role role;

	//备用字符串字段
	private String exString;
	
	//备用整数字段
	private Long exLong;

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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	public Date getLastChangeDate() {
		return lastChangeDate;
	}

	public void setLastChangeDate(Date lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}

	public String getExString() {
		return exString;
	}

	public void setExString(String exString) {
		this.exString = exString;
	}

	public Long getExLong() {
		return exLong;
	}

	public void setExLong(Long exLong) {
		this.exLong = exLong;
	}

}