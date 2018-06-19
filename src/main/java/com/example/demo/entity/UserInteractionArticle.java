package com.example.demo.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 用户与文章交互记录实体
 * @author liuqitian	
 * @date 2018年6月12日 
 *
 */
@Document(indexName = "user_article")
public class UserInteractionArticle  implements Serializable {
	
	//序列化
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	//用户id
	private Long userId;

	//文章id
	private Long articleId;
	
	//互动类型，1为赞，0为踩
	private Long mode;
	
	private User user;
	
	private Article article;
	
	public UserInteractionArticle() {};
	
	public UserInteractionArticle(Long id, Long userId, Long articleId, Long mode) {
		this.id = id;
		this.userId = userId;
		this.articleId = articleId;
		this.mode = mode;
	};

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public Long getMode() {
		return mode;
	}

	public void setMode(Long mode) {
		this.mode = mode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	
}
