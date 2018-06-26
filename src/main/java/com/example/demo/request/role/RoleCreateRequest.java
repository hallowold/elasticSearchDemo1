package com.example.demo.request.role;

import java.util.Date;

/**
 * 创建角色时使用的request对象
 * @author liuqitian	
 * @date 2018年6月12日 
 *
 */
public class RoleCreateRequest {

	//角色名称
	private String name;
	
	//创建日期
	private Date createDate;
	
	//权限id数组
	private Long[] rightIds;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long[] getRightIds() {
		return rightIds;
	}

	public void setRightIds(Long[] rightIds) {
		this.rightIds = rightIds;
	}
	
}
