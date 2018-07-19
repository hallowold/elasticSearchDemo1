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
	
	List<Article> findArticleByName(String name);
	
	Long deleteByIdIn(String[] ids);

	List<Article> findByNameAndAuthorLoginName(String name, String authorLoginName);

}
