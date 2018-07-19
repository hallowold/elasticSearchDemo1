package com.example.demo.entity;

import java.io.Serializable;

import com.example.demo.security.entity.SysUser;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 用户与文章交互记录实体
 * @author liuqitian	
 * @version V1.1  因引入spring security，代码重构
 * @date 2018年7月4日
 */
@Document(indexName = "user_article")
public class UserInteractionArticle  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	/**
	 * 互动类型，1为赞，0为踩
	 */
	private Long mode;

	private SysUser user;

	private Article article;
	
	public UserInteractionArticle() {}

	public UserInteractionArticle(Long id, SysUser user, Article article, Long mode) {
		this.id = id;
		this.user = user;
		this.article = article;
		this.mode = mode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMode() {
		return mode;
	}

	public void setMode(Long mode) {
		this.mode = mode;
	}

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	
}
