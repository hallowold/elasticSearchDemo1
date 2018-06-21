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
	 * @return	boolean
	 */
	void addUserInteractionArticle(UserInteractionArticle userInteractionArticle);
	
	/**
	 * 修改交互记录
	 * @param 	userInteractionArticle	交互记录实体
	 * @return	boolean
	 */
	void updateUserInteractionArticle(UserInteractionArticle userInteractionArticle);
	
	/**
	 * 删除交互记录
	 * @param 	ids		交互记录id数组
	 * @return	boolean
	 */
	void deleteUserInteractionArticle(Long[] ids);
		
	/**
	 * 通过用户id和交互模式查找交互记录
	 * @param 	userId		用户id
	 * @param 	mode		交互模式
	 * @return	userInteractionArticles		交互记录集合
	 * @throws 	Demo1Exception
	 */
	Iterable<UserInteractionArticle> findByUserIdAndMode(Long userId, Long mode) throws Demo1Exception;
	
	/**
	 * 通过文章id和交互模式查找交互记录
	 * @param 	articleId	文章id
	 * @param 	mode		交互模式
	 * @return	userInteractionArticles		交互记录集合
	 * @throws 	Demo1Exception
	 */
	Iterable<UserInteractionArticle> findByArticleIdAndMode(Long articleId, Long mode) throws Demo1Exception;

	/**
	 * @Auther: liuqitian
	 * @Date: 2018/6/21 12:06
	 * @Version: V1.0
	 * @Param: [ids]
	 * @return: java.lang.Long  删除信息条数
	 * @Description: 通过用户ids批量删除
	 */
	Long deleteByUserIds(Long[] ids);

	/**
	 * @Auther: liuqitian
	 * @Date: 2018/6/21 12:06
	 * @Version: V1.0
	 * @Param: [ids]
	 * @return: java.lang.Long  删除信息条数
	 * @Description: 通过文章ids批量删除
	 */
	Long deleteByArticleIds(Long[] ids);
	
	/**
	 * 通过文章id和模式，统计符合要求的信息条数
	 * @param 	articleId	文章id
	 * @param 	mode		交互模式
	 * @return	Long
	 */
	Long countByArticleIdAndMode(Long articleId, Long mode);

}
