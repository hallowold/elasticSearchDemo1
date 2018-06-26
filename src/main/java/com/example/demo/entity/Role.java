package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 角色实体类
 * @author liuqitian
 * @date 2018年6月12日
 *
 */
@Document(indexName = "role")
public class Role implements Serializable {
	
	//序列化
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	//角色名称
	private String name;
	
	//创建日期
	private Date createDate;
	
	//最后修改日期
	private Date lastChangeDate;

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

	public String getExString() {
		return exString;
	}

	public void setExString(String exString) {
		this.exString = exString;
	}

	public Long getExLong() {
		return exLong;
	}

	public void setExInt(Long exLong) {
		this.exLong = exLong;
	}

	@Override
	public String toString() {
		return "Role{" +
				"id=" + id +
				", name='" + name + '\'' +
				", createDate=" + createDate +
				", lastChangeDate=" + lastChangeDate +
				", exString='" + exString + '\'' +
				", exLong=" + exLong +
				'}';
	}
}