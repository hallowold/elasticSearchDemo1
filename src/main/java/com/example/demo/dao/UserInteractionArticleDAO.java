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
	
	Iterable<UserInteractionArticle> findByUserIdAndMode(Integer userId, Long mode);
	
	Iterable<UserInteractionArticle> findByArticleIdAndMode(String articleId, Long mode);
	
	Long countByArticleIdAndMode(String articleId, Long mode);
	
	Long deleteByIdIn(Long[] ids);

	Long deleteByUserIdIn(Integer[] ids);

	Long deleteByArticleIdIn(String[] ids);
	
	UserInteractionArticle findByArticleIdAndUserId(String articleId, Integer userId);

	List<UserInteractionArticle> findByUserId(Integer userId);

	List<UserInteractionArticle> findByArticleId(String articleId);

}
