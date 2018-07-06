package com.example.demo.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Article;

/**
 * 文章实体的DAO
 * @author liuqitian	
 * @date 2018年6月12日 
 *
 */
@Component
public interface ArticleDAO extends ElasticsearchRepository<Article, String> {
	
	/**
	 * 通过标题来查找符合要求的文章列表
	 * @param 	name	标题
	 * @return	List<Article>
	 */
	List<Article> findArticleByName(String name);
	
	/**
	 * 通过id数组批量删除数据
	 * @param 	ids	id数组
	 * @return	Long	删除信息条数
	 */
	Long deleteByIdIn(String[] ids);

}
