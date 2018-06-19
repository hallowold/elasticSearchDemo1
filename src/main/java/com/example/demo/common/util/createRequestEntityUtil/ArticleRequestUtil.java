package com.example.demo.common.util.createRequestEntityUtil;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Article;
import com.example.demo.request.article.ArticleCreateRequest;
import com.example.demo.request.article.ArticleUpdateRequest;

public class ArticleRequestUtil {
	
	/**
	 * 将ArticleCreateRequest转化为Article
	 * @param 	ArticleCreateRequest
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
			//用户id，暂用，解决主键问题后应去除
			if(request.getId() != null) {
				info.setId(request.getId());
			}
			if(request.getName() != null) {
				info.setName(request.getName());
			}
			if(request.getAuthorId() != null) {
				info.setAuthorId(request.getAuthorId());
			}
			if(request.getCreateDate() != null) {
				info.setCreateDate(request.getCreateDate());
			}
		}
		return info;
	}
	
	/**
	 * 将ArticleCreateRequest集合转化为Article集合
	 * @param 	List<ArticleCreateRequest>
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
	 * @param 	ArticleUpdateRequest
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
			if(request.getAuthorId() != null) {
				info.setAuthorId(request.getAuthorId());
			}
			if(request.getCreateDate() != null) {
				info.setCreateDate(request.getCreateDate());
			}
		}
		return info;
	}
	
	/**
	 * 将ArticleUpdateRequest集合转化为Article集合
	 * @param 	List<ArticleUpdateRequest>
	 * @return	List<Article>
	 */
	public static List<Article> createArticleByUpdateRequests(List<ArticleUpdateRequest> requests) {
		List<Article> resultList = new ArrayList<Article>();
		for(ArticleUpdateRequest request : requests) {
			if(createArticleByUpdateRequest(request) != null) {
				resultList.add(createArticleByUpdateRequest(request));
			}
		}
		return resultList;
	}

}
