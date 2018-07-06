package com.example.demo.service;

import com.example.demo.entity.UserInteractionArticle;
import com.example.demo.exception.Demo1Exception;

/**
 * 交互记录与文章交互记录服务定义接口
 * @author liuqitian
 * @date 2018年6月11日 
 *
 */
public interface UserInteractionArticleService {
	
	/**
	 * 新增交互记录
	 * @param 	userInteractionArticle	交互记录实体
	 */
	void addUserInteractionArticle(UserInteractionArticle userInteractionArticle);
	
	/**
	 * 修改交互记录
	 * @param 	userInteractionArticle	交互记录实体
	 */
	void updateUserInteractionArticle(UserInteractionArticle userInteractionArticle);
	
	/**
	 * 删除交互记录
	 * @param 	ids		交互记录id数组
	 */
	void deleteUserInteractionArticle(Long[] ids);
		
	/**
	 * 通过用户id和交互模式查找交互记录
	 * @param 	userId		用户id
	 * @param 	mode		交互模式
	 * @return	userInteractionArticles		交互记录集合
	 * @throws 	Demo1Exception 查询结果集为空时抛出Demo1Exception(StaticValues.SEARCH)
	 */
	Iterable<UserInteractionArticle> findByUserIdAndMode(Integer userId, Long mode) throws Demo1Exception;
	
	/**
	 * 通过文章id和交互模式查找交互记录
	 * @param 	articleId	文章id
	 * @param 	mode		交互模式
	 * @return	userInteractionArticles		交互记录集合
	 * @throws 	Demo1Exception 查询结果集为空时抛出Demo1Exception(StaticValues.SEARCH)
	 */
	Iterable<UserInteractionArticle> findByArticleIdAndMode(String articleId, Long mode) throws Demo1Exception;

	/**
	 * 通过用户ids批量删除
	 * @param ids 用户ids
	 * @return Long 删除信息条数
	 */
	Long deleteByUserIds(Integer[] ids);

	/**
	 * 通过文章ids批量删除
	 * @param ids 文章ids
	 * @return Long 删除信息条数
	 */
	Long deleteByArticleIds(String[] ids);
	
	/**
	 * 通过文章id和模式，统计符合要求的信息条数
	 * @param 	articleId	文章id
	 * @param 	mode		交互模式
	 * @return	Long		统计信息条数
	 */
	Long countByArticleIdAndMode(String articleId, Long mode);

}
