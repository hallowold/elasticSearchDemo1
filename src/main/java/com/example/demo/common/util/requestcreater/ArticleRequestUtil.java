package com.example.demo.common.util.requestcreater;

import com.example.demo.entity.Article;
import com.example.demo.request.article.ArticleCreateRequest;
import com.example.demo.request.article.ArticleUpdateRequest;
import com.example.demo.security.config.LoginSuccessHandler;
import com.example.demo.security.entity.SysUser;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author  liuqitian
 * @date : 2018/7/5 11:44
 * @version  V1.1 因引入spring security统一管理权限，代码重构
 * 本类用于将article相关的request对象转化实体
 */
public class ArticleRequestUtil {
	
	public static Article createArticleByCreateRequest(ArticleCreateRequest request) {
		Article info = new Article();
		if(request == null) {
			return null;
		} else {
			if(request.getName() != null) {
				info.setName(request.getName());
			}
			info.setAuthor(LoginSuccessHandler.getCurrentUser());
			info.setCreateDate(new Date());
		}
		return info;
	}
	
	public static List<Article> createArticleByCreateRequests(List<ArticleCreateRequest> requests) {
		List<Article> resultList = new ArrayList<Article>();
		for(ArticleCreateRequest request : requests) {
			if(createArticleByCreateRequest(request) != null) {
				resultList.add(createArticleByCreateRequest(request));
			}
		}
		return resultList;
	}

	public static Article createArticleByUpdateRequest(ArticleUpdateRequest request) {
		Article info = new Article();
		if(request == null) {
			return null;
		} else {
			if(request.getId() != null) {
				info.setId(request.getId());
			}
			if(request.getName() != null) {
				info.setName(request.getName());
			}
			info.setAuthor(LoginSuccessHandler.getCurrentUser());
			if(request.getCreateDate() != null) {
				info.setCreateDate(request.getCreateDate());
			}
		}
		return info;
	}
	
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
