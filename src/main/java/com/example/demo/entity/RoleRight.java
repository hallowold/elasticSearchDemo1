package com.example.demo.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 角色与权限关联实体
 * @author liuqitian	
 * @date 2018年6月13日 
 *
 */
@Document(indexName = "role_right")
public class RoleRight implements Serializable {
	
	//序列化
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	//角色id
	private Long roleId;
	
	//权限id
	private Long rightId;
	
	public RoleRight() {}
	
	public RoleRight(Long id, Long roleId, Long rightId) {
		this.id = id;
		this.roleId = roleId;
		this.rightId = rightId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getRightId() {
		return rightId;
	}

	public void setRightId(Long rightId) {
		this.rightId = rightId;
	}
	
}
