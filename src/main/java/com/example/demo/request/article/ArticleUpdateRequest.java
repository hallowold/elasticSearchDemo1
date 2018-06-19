package com.example.demo.request.article;

import java.util.Date;

/**
 * 修改文章时使用的request对象
 * @author liuqitian	
 * @date 2018年6月12日 
 *
 */
public class ArticleUpdateRequest {
	
	//文章id
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
