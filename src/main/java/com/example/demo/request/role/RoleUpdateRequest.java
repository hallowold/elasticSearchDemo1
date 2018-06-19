package com.example.demo.request.role;

import java.util.Date;

/**
 * 修改角色时使用的request对象
 * @author liuqitian	
 * @date 2018年6月12日 
 *
 */
public class RoleUpdateRequest {
	
	//角色id
	private Long id;
	
	//角色名称
	private String name;
	
	//创建日期
	private Date createDate;
	
	//最后修改日期
	private Date lastChangeDate;
	
	//权限id数组
	private Long[] rightIds;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Date getLastChangeDate() {
		return lastChangeDate;
	}

	public void setLastChangeDate(Date lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}

	public Long[] getRightIds() {
		return rightIds;
	}

	public void setRightIds(Long[] rightIds) {
		this.rightIds = rightIds;
	}
	
}
