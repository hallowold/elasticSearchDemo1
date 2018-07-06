package com.example.demo.request.role;

/**
 * 修改角色时使用的request对象
 * @author liuqitian	
 * @date 2018年6月12日 
 *
 */
public class RoleUpdateRequest {

	private Integer id;

	/**
	 * 角色名称
	 */
	private String name;

	/**
	 * 用户id(外键)
	 */
	private Integer userId;

	/**
	 * 权限ids
	 */
	private Integer[] rightIds;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer[] getRightIds() {
		return rightIds;
	}

	public void setRightIds(Integer[] rightIds) {
		this.rightIds = rightIds;
	}
}
