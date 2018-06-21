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
	
	//角色
	private Role role;

	//权限
	private Right right;

	public RoleRight() {}
	
	public RoleRight(Long id, Role role, Right right) {
		this.id = id;
		this.role = role;
		this.right = right;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Right getRight() {
		return right;
	}

	public void setRight(Right right) {
		this.right = right;
	}
}
