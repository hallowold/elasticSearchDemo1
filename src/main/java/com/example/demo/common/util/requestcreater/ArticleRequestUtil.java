package com.example.demo.common.util.requestcreater;

import com.example.demo.entity.Article;
import com.example.demo.request.article.ArticleCreateRequest;
import com.example.demo.request.article.ArticleUpdateRequest;
import com.example.demo.security.entity.SysUser;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author  liuqitian
 * @date : 2018/7/5 11:44
 * @version  V1.1 因引入spring security统一管理权限，代码重构
 * @deprecated : 本类用于将article相关的request对象转化实体
 */
public class ArticleRequestUtil {
	
	/**
	 * 将ArticleCreateRequest转化为Article
	 * @param 	request
	 * @return	Article
	 */
	public static Article createArticleByCreateRequest(ArticleCreateRequest request) {
		Article info = new Article();
		/*
		 * 若request为空则直接返回null，否则将属性值一一对应转化
		 */
		if(request == null) {
			return null;
		} else {
			if(request.getName() != null) {
				info.setName(request.getName());
			}
			info.setAuthor((SysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			info.setCreateDate(new Date());
		}
		return info;
	}
	
	/**
	 * 将ArticleCreateRequest集合转化为Article集合
	 * @param 	requests
	 * @return	List<Article>
	 */
	public static List<Article> createArticleByCreateRequests(List<ArticleCreateRequest> requests) {
		List<Article> resultList = new ArrayList<Article>();
		for(ArticleCreateRequest request : requests) {
			if(createArticleByCreateRequest(request) != null) {
				resultList.add(createArticleByCreateRequest(request));
			}
		}
		return resultList;
	}
	
	/**
	 * 将ArticleUpdateRequest转化为Article
	 * @param 	request
	 * @return	Article
	 */
	public static Article createArticleByUpdateRequest(ArticleUpdateRequest request) {
		Article info = new Article();
		/*
		 * 若request为空则直接返回null，否则将属性值一一对应转化
		 */
		if(request == null) {
			return null;
		} else {
			if(request.getId() != null) {
				info.setId(request.getId());
			}
			if(request.getName() != null) {
				info.setName(request.getName());
			}
			info.setAuthor((SysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			if(request.getCreateDate() != null) {
				info.setCreateDate(request.getCreateDate());
			}
		}
		return info;
	}
	
	/**
	 * 将ArticleUpdateRequest集合转化为Article集合
	 * @param 	requests
	 * @return	List<Article>
	 */
	public static List<Article> createArticleByUpdateRequests(List<ArticleUpdateRequest> requests) {
		List<Article> resultList = new ArrayList<>();
		for(ArticleUpdateRequest request : requests) {
			if(createArticleByUpdateRequest(request) != null) {
				resultList.add(createArticleByUpdateRequest(request));
			}
		}
		return resultList;
	}

}
