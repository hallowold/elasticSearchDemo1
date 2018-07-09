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

	public Integer[] getRightIds() {
		return rightIds;
	}

	public void setRightIds(Integer[] rightIds) {
		this.rightIds = rightIds;
	}
}
