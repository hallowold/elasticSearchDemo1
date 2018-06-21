package com.example.demo.service.impl;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.Avg;
import org.elasticsearch.search.aggregations.metrics.max.Max;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.tophits.TopHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.common.UserSessionInfo;
import com.example.demo.dao.ArticleDAO;
import com.example.demo.dao.UserInteractionArticleDAO;
import com.example.demo.entity.Article;
import com.example.demo.entity.User;
import com.example.demo.entity.UserInteractionArticle;
import com.example.demo.exception.Demo1Exception;
import com.example.demo.service.ArticleService;
import com.example.demo.service.UserInteractionArticleService;

/**
 * 文章服务实现类
 * 
 * @author liuqitian
 * @date 2018年6月11日
 *
 */
@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private UserInteractionArticleService userInteractionArticleservice;

	@Autowired
	private ArticleDAO articleDao;
	
	@Autowired
	UserInteractionArticleDAO userInteractionArticleDao;

	/**
	 * 新增文章
	 * @param 	article 	文章实体
	 */
	@Transactional
	public void addArticle(Article article) {
		articleDao.save(article);
	}

	/**
	 * 修改文章
	 * @param 	article 	文章实体
	 * @return 	无返回值或自定义异常，异常表示当前用户不是作者，无权限操作
	 */
	@Transactional
	public void updateArticle(Article article) throws Demo1Exception {
		if(!this.isAuthor(article.getId())) {
			throw new Demo1Exception("作者");
		}
		articleDao.save(article);
	}

	/**
	 * 删除文章
	 * @param 	ids		文章id数组
	 * @return 	无返回值或自定义异常，异常表示当前用户不是作者，无权限操作
	 */
	@Transactional
	public void deleteArticle(Long[] ids) throws Demo1Exception {
		for(int num = 0; num < ids.length; num++) {
			if(!this.isAuthor(ids[num]) && ids.length > 1) {
				throw new Demo1Exception("作者，批量删除");
			} else if (!this.isAuthor(ids[num]) && ids.length == 1) {
				throw new Demo1Exception("作者");
			}
		}
		articleDao.deleteByIdIn(ids);
		userInteractionArticleservice.deleteByArticleIds(ids);
	}

	/**
	 * 通过标题查找近似文章列表
	 * 
	 * @param 	name	 	标题
	 * @return 	List<Article> 	文章实体集合
	 */
	@Transactional
	public List<Article> fuzzyFindByName(String name){
		List<Article> list = articleDao.findArticleByName("*" + name + "*");
		return list;
	}
	
	/**
	 * 获取所有文章
	 * @return	articles	文章集合
	 */
	@Transactional
	public Iterable<Article> findAllArticle() {
		return articleDao.findAll();
	}
	
	/**
	 * 判断当前后台系统用户是否是指定文章的作者
	 * @param 	articleId	文章id
	 * @return	boolean
	 */
	@Transactional
	public boolean isAuthor(Long articleId) {
		boolean isAuthor = false;
		if(UserSessionInfo.getBackgroundUserInfo().getId() == articleDao.findById(articleId).get().getAuthor().getId()) {
			isAuthor = true;
		}
		return isAuthor;
	}

	/**
	 * @Auther: liuqitian
	 * @Date: 2018/6/21 14:39
	 * @Version: V1.0
	 * @Param: [userId]
	 * @return: java.util.List<com.example.demo.entity.UserInteractionArticle>
	 * @Description: 通过用户id获取相关信息
	 */
	public List<UserInteractionArticle> findByUserId(Long userId) {
		return userInteractionArticleDao.findByUserId(userId);
	}

	/**
	 * @Auther: liuqitian
	 * @Date: 2018/6/21 14:39
	 * @Version: V1.0
	 * @Param: [articleId]
	 * @return: java.util.List<com.example.demo.entity.UserInteractionArticle>
	 * @Description: 通过文章id获取相关信息
	 */
	public List<UserInteractionArticle> findByArticleId(Long articleId) {
		return userInteractionArticleDao.findByArticleId(articleId);
	}

	/**
	 * @Auther: liuqitian
	 * @Date: 2018/6/21 11:38
	 * @Version: V1.0
	 * @Param: [mode]
	 * @return: org.springframework.data.elasticsearch.core.aggregation.AggregatedPage<com.example.demo.entity.UserInteractionArticle>
	 * @Description: 通过行为模式，记录所有文章的点/踩数量
	 */
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
	public void interaction(Long articleId, Long mode) {
		User user = UserSessionInfo.getBackgroundUserInfo();
		UserInteractionArticle userInteractionArticle = 
				userInteractionArticleDao.findByArticleIdAndUserId(articleId, user.getId());
		//若不存在互动记录，则新增
		//	若已存在，模式相同则删除，不同则更新
		if(userInteractionArticle == null || userInteractionArticle.getId() == null) {
			userInteractionArticleservice.addUserInteractionArticle(
				new UserInteractionArticle(userInteractionArticleDao.count() + 1, user, articleDao.findById(articleId).get(), mode));
		} else {
			if(userInteractionArticle.getMode() != mode) {
				userInteractionArticleservice.updateUserInteractionArticle(
					new UserInteractionArticle(userInteractionArticle.getId(), user, articleDao.findById(articleId).get(), mode));
			} else {
				userInteractionArticleservice.deleteUserInteractionArticle(new Long[] {userInteractionArticle.getId()});
			}
		}
		
	}
	
}
