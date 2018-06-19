package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
/**
 * 文章实体类
 * @author liuqitian	
 * @date 2018年6月12日 
 *
 */
@Document(indexName = "article")
public class Article implements Serializable {
	
	//序列化
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	//文章名
	private String name;
	
	//作者id
	private Long authorId; 
	
	//创建时间
	private Date createDate;

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

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
