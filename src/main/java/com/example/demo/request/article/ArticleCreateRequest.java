package com.example.demo.request.article;

import com.example.demo.entity.User;

import java.util.Date;

/**
 * 创建文章时使用的request对象
 * @author liuqitian	
 * @date 2018年6月12日 
 *
 */
public class ArticleCreateRequest {
	
	//用户id，暂用，解决主键问题后应去除
	private Long id;
	
	//文章名
	private String name;

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

}
