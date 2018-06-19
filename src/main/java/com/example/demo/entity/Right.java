package com.example.demo.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 权限实体
 * @author liuqitian	
 * @date 2018年6月13日 
 *
 */
@Document(indexName = "right")
public class Right implements Serializable {
	
	//序列化
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	//模块名
	private String moduleName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
}
