package com.example.demo.dao;

import com.example.demo.entity.UserInteractionArticle;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户与文章交互记录实体的dao
 * @author liuqitian
 * @date 2018年6月11日
 *
 */
@Component
public interface UserInteractionArticleDAO extends ElasticsearchRepository<UserInteractionArticle, Long> {
	
	/**
	 * 通过用户id和模式来查找交互记录
	 * @param 	userId	用户id
	 * @param	mode	交互模式
	 * @return	Iterable<UserInteractionArticle>
	 */
	Iterable<UserInteractionArticle> findByUserIdAndMode(Integer userId, Long mode);
	
	/**
	 * 通过文章id和模式来查找交互记录
	 * @param 	articleId	文章id
	 * @param 	mode		交互模式
	 * @return	Iterable<UserInteractionArticle>
	 */
	Iterable<UserInteractionArticle> findByArticleIdAndMode(String articleId, Long mode);
	
	/**
	 * 通过文章id和模式，统计符合要求的信息条数
	 * @param 	articleId	文章id
	 * @param 	mode		交互模式
	 * @return	Long
	 */
	Long countByArticleIdAndMode(String articleId, Long mode);
	
	/**
	 * 通过id数组批量删除数据
	 * @param 	ids		ids
	 * @return	Long	删除信息条数
	 */
	Long deleteByIdIn(Long[] ids);

	/**
	 * 通过用户ids批量删除
	 * @param 	ids		用户ids
	 * @return	java.lang.Long  删除信息条数
	 */
	Long deleteByUserIdIn(Integer[] ids);

	/**
	 * 通过文章ids批量删除
	 * @param 	ids		文章ids
	 * @return	java.lang.Long  删除信息条数
	 */
	Long deleteByArticleIdIn(String[] ids);
	
	/**
	 * 通过文章id和用户id查找信息
	 * @param 	articleId	文章id
	 * @param 	userId		用户id
	 * @return	UserInteractionArticle
	 */
	UserInteractionArticle findByArticleIdAndUserId(String articleId, Integer userId);

	/**
	 * 通过用户id和角色id查找信息
	 * @param 	userId		用户id
	 * @return	UserInteractionArticle
	 */
	List<UserInteractionArticle> findByUserId(Integer userId);

	/**
	 * 通过文章id查找信息
	 * @param 	articleId	文章id
	 * @return	UserInteractionArticle
	 */
	List<UserInteractionArticle> findByArticleId(String articleId);

}
