package com.example.demo.response.dataEntity;

import java.util.Date;
import java.util.List;

import com.example.demo.entity.User;

/**
 * 文章response对象
 * @author liuqitian	
 * @date 2018年6月14日 
 *
 */
public class ArticleResponseEntity {
	
	//文章id
	private Long id;
	
	//文章名
	private String name;
	
	//作者id
	private Long authorId; 
	
	//创建时间
	private Date createDate;
	
	//点赞数目
	private Long likedNumber; 
	
	//踩数目
	private Long dislikedNumber;
	
	//点赞用户列表
	private List<User> likedUsers;
	
	//踩用户列表
	private List<User> disLikedUsers;

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

	public Long getLikedNumber() {
		return likedNumber;
	}

	public void setLikedNumber(Long likedNumber) {
		this.likedNumber = likedNumber;
	}

	public Long getDislikedNumber() {
		return dislikedNumber;
	}

	public void setDislikedNumber(Long dislikedNumber) {
		this.dislikedNumber = dislikedNumber;
	}

	public List<User> getLikedUsers() {
		return likedUsers;
	}

	public void setLikedUsers(List<User> likedUsers) {
		this.likedUsers = likedUsers;
	}

	public List<User> getDisLikedUsers() {
		return disLikedUsers;
	}

	public void setDisLikedUsers(List<User> disLikedUsers) {
		this.disLikedUsers = disLikedUsers;
	}

	
}
