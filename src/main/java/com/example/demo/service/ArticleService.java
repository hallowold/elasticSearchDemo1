package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.entity.Article;
import com.example.demo.entity.UserInteractionArticle;
import com.example.demo.exception.Demo1Exception;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;

/**
 * 文章服务定义接口
 * @author liuqitian
 * @date 2018年6月11日 
 *
 */
public interface ArticleService {
	
	void addArticle(Article article);
	
	/**
	 * Demo1Exception异常表示当前用户不是作者，无权限操作
	 */
	void updateArticle(Article article) throws Demo1Exception;
	
	/**
	 * Demo1Exception异常表示当前用户不是作者，无权限操作
	 */
	Long deleteArticle(String[] ids) throws Demo1Exception;
	
	List<Article> fuzzyFindByName(String name);
	
	Iterable<Article> findAllArticle();
	
	/**
	 * 当前用户与指定文章互动
	 * @param 	articleId	文章id
	 * @param 	mode		互动模式
	 */
	void interaction(String articleId, Long mode);

	List<UserInteractionArticle> findByUserId(Integer userId);

	List<UserInteractionArticle> findByArticleId(String articleId);

	AggregatedPage<UserInteractionArticle> findByMode(Long mode);

}
