package com.example.demo.service.impl;

import com.example.demo.common.config.StaticValues;
import com.example.demo.common.util.KeyNumberUtil;
import com.example.demo.common.util.StringUtil;
import com.example.demo.dao.ArticleDAO;
import com.example.demo.dao.UserInteractionArticleDAO;
import com.example.demo.entity.Article;
import com.example.demo.entity.UserInteractionArticle;
import com.example.demo.exception.Demo1Exception;
import com.example.demo.security.config.LoginSuccessHandler;
import com.example.demo.security.entity.SysUser;
import com.example.demo.service.ArticleService;
import com.example.demo.service.UserInteractionArticleService;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 文章服务实现类
 * @author liuqitian
 * @date 2018年6月11日
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private UserInteractionArticleService userInteractionArticleservice;

	@Autowired
	private ArticleDAO articleDao;
	
	@Autowired
	UserInteractionArticleDAO userInteractionArticleDao;

	@Override
	public void addArticle(Article article) {
		article.setId(KeyNumberUtil.nextId() + "");
		articleDao.save(article);
	}

	/**
	 * Demo1Exception异常表示当前用户不是作者，无权限操作
	 */
	@Override
	public void updateArticle(Article article) throws Demo1Exception {
		if(!articleDao.existsById(article.getId())) {
			throw new Demo1Exception(StaticValues.NODATA);
		}
		if(!this.isAuthor(article.getId())) {
			throw new Demo1Exception(StaticValues.AUTHOR);
		}
		articleDao.save(article);
	}

	/**
	 * Demo1Exception异常表示当前用户不是作者，无权限操作
	 */
	@Override
	public Long deleteArticle(String[] ids) throws Demo1Exception {
		Arrays.stream(ids).forEach(System.out::println);
		for(int num = 0; num < ids.length; num++) {
			if(!this.isAuthor(ids[num]) && ids.length > 1) {
				throw new Demo1Exception(StaticValues.AUTHOR);
			} else if (!this.isAuthor(ids[num]) && ids.length == 1) {
				throw new Demo1Exception(StaticValues.AUTHOR);
			}
		}
		Long result = articleDao.deleteByIdIn(ids);
		userInteractionArticleservice.deleteByArticleIds(ids);
		if(result.longValue() == 0L) {
			throw new Demo1Exception(StaticValues.NODATA);
		}
		return result;
	}

	@Override
	public List<Article> fuzzyFindByName(String name){
		List<Article> list = articleDao.findArticleByName("*" + StringUtil.changeSpecialCharacter(name) + "*");
		return list;
	}
	
	@Override
	public Iterable<Article> findAllArticle() {
		return articleDao.findAll();
	}
	
	@Override
	public List<UserInteractionArticle> findByUserId(Integer userId) {
		return userInteractionArticleDao.findByUserId(userId);
	}

	@Override
	public List<UserInteractionArticle> findByArticleId(String articleId) {
		return userInteractionArticleDao.findByArticleId(articleId);
	}

	@Override
	public AggregatedPage<UserInteractionArticle> findByMode(Long mode) {
	    //where条件
		QueryBuilder qb = QueryBuilders.matchQuery("mode", mode);
		//分组，聚合以及排序条件
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withQuery(qb)
				.addAggregation(AggregationBuilders
						.terms("articleName").field("article.name.keyword")
						.order(Terms.Order.count(false)))
				.build();
		//获得结果集
		AggregatedPage<UserInteractionArticle> account =
				(AggregatedPage<UserInteractionArticle>) userInteractionArticleDao.search(searchQuery);
		return account;
	}

	/**
	 * 当前用户与指定文章互动
	 * @param 	articleId	文章id
	 * @param 	mode		互动模式
	 */
	@Override
	public void interaction(String articleId, Long mode) {
		SysUser user = LoginSuccessHandler.getCurrentUser();
		UserInteractionArticle userInteractionArticle = 
				userInteractionArticleDao.findByArticleIdAndUserId(articleId, user.getId());
		//若不存在互动记录，则新增
		//	若已存在，模式相同则删除，不同则更新
		if(userInteractionArticle == null || userInteractionArticle.getId() == null) {
			userInteractionArticleservice.addUserInteractionArticle(
				new UserInteractionArticle(KeyNumberUtil.nextId(), user, articleDao.findById(articleId).get(), mode));
		} else {
			if(userInteractionArticle.getMode().longValue() != mode) {
				userInteractionArticleservice.updateUserInteractionArticle(
					new UserInteractionArticle(userInteractionArticle.getId(), user, articleDao.findById(articleId).get(), mode));
			} else {
				userInteractionArticleservice.deleteUserInteractionArticle(new Long[] {userInteractionArticle.getId()});
			}
		}
	}

	/**
	 * 判断当前后台系统用户是否是指定文章的作者
	 * @param 	articleId	文章id
	 * @return	boolean
	 */
	public boolean isAuthor(String articleId) {
		boolean isAuthor = false;
		SysUser currentUser = LoginSuccessHandler.getCurrentUser();
		if(currentUser.getId().intValue() == articleDao.findById(articleId).get().getAuthor().getId().intValue()) {
			isAuthor = true;
		}
		return isAuthor;
	}

}
