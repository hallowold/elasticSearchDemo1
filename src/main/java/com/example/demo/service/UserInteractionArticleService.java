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
	
	void addUserInteractionArticle(UserInteractionArticle userInteractionArticle);
	
	void updateUserInteractionArticle(UserInteractionArticle userInteractionArticle);
	
	void deleteUserInteractionArticle(Long[] ids);

	Iterable<UserInteractionArticle> findByUserIdAndMode(Integer userId, Long mode) throws Demo1Exception;
	
	Iterable<UserInteractionArticle> findByArticleIdAndMode(String articleId, Long mode) throws Demo1Exception;

	Long deleteByUserIds(Integer[] userIds);

	Long deleteByArticleIds(String[] articleIds);
	
	Long countByArticleIdAndMode(String articleId, Long mode);

}
