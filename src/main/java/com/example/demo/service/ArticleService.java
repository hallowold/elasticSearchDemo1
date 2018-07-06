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
	 */
	void deleteArticle(String[] ids) throws Demo1Exception;
	
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
	void interaction(String articleId, Long mode);

	/**
	 * @Auther: liuqitian
	 * @Date: 2018/6/21 14:39
	 * @Version: V1.0
	 * @Param: [userId]
	 * @return: java.util.List<com.example.demo.entity.UserInteractionArticle>
	 * @Description: 通过用户id获取相关信息
	 */
	List<UserInteractionArticle> findByUserId(Integer userId);

	/**
	 * @Auther: liuqitian
	 * @Date: 2018/6/21 14:39
	 * @Version: V1.0
	 * @Param: [articleId]
	 * @return: java.util.List<com.example.demo.entity.UserInteractionArticle>
	 * @Description: 通过文章id获取相关信息
	 */
	List<UserInteractionArticle> findByArticleId(String articleId);

	/**
	 * @Auther: liuqitian
	 * @Date: 2018/6/21 11:38
	 * @Version: V1.0
	 * @Param: [mode]
	 * @return: org.springframework.data.elasticsearch.core.aggregation.AggregatedPage<com.example.demo.entity.UserInteractionArticle>
	 * @Description: 通过行为模式，记录所有文章的点/踩数量
	 */
	AggregatedPage<UserInteractionArticle> findByMode(Long mode);

}
