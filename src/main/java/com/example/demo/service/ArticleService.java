package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.entity.Article;
import com.example.demo.entity.UserInteractionArticle;
import com.example.demo.exception.Demo1Exception;

/**
 * 文章服务定义接口
 * @author liuqitian
 * @date 2018年6月11日 
 *
 */
public interface ArticleService {
	
	/**
	 * 新增文章
	 * @param 	article	文章实体
	 * @return	boolean
	 */
	void addArticle(Article article);
	
	/**
	 * 修改文章
	 * @param 	article	文章实体
	 * @return	boolean
	 */
	void updateArticle(Article article) throws Demo1Exception;
	
	/**
	 * 删除文章
	 * @param 	ids		文章id数组
	 * @return	boolean
	 */
	void deleteArticle(Long[] ids) throws Demo1Exception;
	
	/**
	 * 通过标题查找近似文章列表
	 * 
	 * @param 	name	 	标题
	 * @return 	Article 	文章实体
	 */
	List<Article> fuzzyFindByName(String name);
	
	/**
	 * 获取所有文章
	 * @return	articles		文章集合
	 */
	Iterable<Article> findAllArticle();
	
	/**
	 * 当前用户与指定文章互动
	 * @param 	articleId	文章id
	 * @param 	mode		互动模式
	 */
	void interaction(Long articleId, Long mode);
	
	List<UserInteractionArticle> findByUserId(Long userId); 
	
	List<UserInteractionArticle> findByArticleId(Long articleId);
	
	Object findTest(Long mode);

}
