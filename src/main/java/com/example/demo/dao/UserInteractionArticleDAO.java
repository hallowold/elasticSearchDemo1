package com.example.demo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import com.example.demo.entity.UserInteractionArticle;

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
	Iterable<UserInteractionArticle> findByUserIdAndMode(Long userId, Long mode);
	
	/**
	 * 通过文章id和模式来查找交互记录
	 * @param 	articleId	文章id
	 * @param 	mode		交互模式
	 * @return	Iterable<UserInteractionArticle>
	 */
	Iterable<UserInteractionArticle> findByArticleIdAndMode(Long articleId, Long mode);
	
	/**
	 * 通过文章id和模式，统计符合要求的信息条数
	 * @param 	articleId	文章id
	 * @param 	mode		交互模式
	 * @return	Long
	 */
	Long countByArticleIdAndMode(Long articleId, Long mode);
	
	/**
	 * 通过id数组批量删除数据
	 * @param 	ids	id数组
	 * @return	Long	删除信息条数
	 */
	Long deleteByIdIn(Long[] ids);

	/**
	 * @Auther: liuqitian
	 * @Date: 2018/6/21 12:03
	 * @Version: V1.0
	 * @Param: [ids]
	 * @return: java.lang.Long  删除信息条数
	 * @Description: 通过用户ids批量删除
	 */
	Long deleteByUserIdIn(Long[] ids);

	/**
	 * @Auther: liuqitian
	 * @Date: 2018/6/21 12:04
	 * @Version: V1.0
	 * @Param: [ids]
	 * @return: java.lang.Long  删除信息条数
	 * @Description: 通过文章ids批量删除
	 */
	Long deleteByArticleIdIn(Long[] ids);
	
	/**
	 * 通过文章id和角色id查找信息
	 * @param 	articleId	文章id
	 * @param 	userId		用户id
	 * @return	UserInteractionArticle
	 */
	UserInteractionArticle findByArticleIdAndUserId(Long articleId, Long userId);
	
	List<UserInteractionArticle> findByUserId(Long userId);
	
	List<UserInteractionArticle> findByArticleId(Long article);

	@Query("{\"match\": { \"mode\": 1 } }, \"aggs\": { \"all_tags\": { \"terms\": { \"field\": \"articleId\"}}}")
	Object findEntitys(Long mode);

}
