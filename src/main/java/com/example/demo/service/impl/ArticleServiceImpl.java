package com.example.demo.service.impl;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
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
		if(UserSessionInfo.getBackgroundUserInfo().getId() == articleDao.findById(articleId).get().getAuthorId()) {
			isAuthor = true;
		}
		return isAuthor;
	}
	
	public List<UserInteractionArticle> findByUserId(Long userId) {
		return userInteractionArticleDao.findByUserId(userId);
	}
	
	public List<UserInteractionArticle> findByArticleId(Long articleId) {
		return userInteractionArticleDao.findByArticleId(articleId);
	}
	
	public Page<UserInteractionArticle> findTest(Long mode) {
//		this.test();
//		Object list = userInteractionArticleDao.findEntitys(mode);
		Page<UserInteractionArticle> list = userInteractionArticleDao.search(this.getSq(mode));
		return list;
	}
	
	public QueryBuilder getQb(Long mode) {
		QueryBuilder qb = QueryBuilders.boolQuery().
				must(QueryBuilders.matchQuery("mode", mode));
		return qb;
		
	}
	
	public SearchQuery getSq(Long mode) {
		QueryBuilder qb = QueryBuilders.boolQuery()
				.must(QueryBuilders.matchQuery("mode", mode));
		
//		ValueCountAggregationBuilder vcb =  AggregationBuilders.count("count_articleId").field("articleId");
		TermsAggregationBuilder tb =  AggregationBuilders.terms("group_articleId").field("articleId");
//		tb.subAggregation(vcb);
//		SumAggregationBuilder sumBuilder = AggregationBuilders.sum("sum_sales").field("goodsSales"); 
		
		SearchQuery searchQuery = new NativeSearchQueryBuilder()  
		        .withQuery(qb)  
		        .addAggregation(tb)  
		        .build();
		return searchQuery;
	}
	
//	public SearchQuery getSq2() {
//		SumAggregationBuilder sumBuilder = AggregationBuilders.sum("sum").field("adress.num");
//		AggregationBuilder aggregationBuilder = AggregationBuilders.nested("nested", "adress").subAggregation(sumBuilder);
//		SearchQuery searchQuery = new NativeSearchQueryBuilder()
//		    .withIndices("user_article")
//		    .withQuery(this.getQb(1l))
//		    .addAggregation((AbstractAggregationBuilder) aggregationBuilder).build();
//		AggregatedPage<UserInteractionArticle> account = (AggregatedPage<UserInteractionArticle>) userInteractionArticleDao
//				.search(EsQueryBuilders.buildYesterdayArrearsSumQuery(employeeId));
//		int sum = account.getAggregation("nested", SumAggregation.class).getAggregation("sum", SumAggregation.class).getSum().intValue();
//	}
	
//	public void test() {
//		SearchResponse sr = transportClient.prepareSearch("user_article")
//				.setQuery(QueryBuilders.matchAllQuery())  
//                .setSearchType(SearchType.QUERY_THEN_FETCH)  
//                .addAggregation(AggregationBuilders.terms("group_articleId")  
//                        .field("articleId").size(0))//根据age分组，默认返回10，size(0)也是10  
//                .execute().actionGet();
//		Terms terms = sr.getAggregations().get("group_articleId");  
//        List<Bucket> buckets = (List<Bucket>) terms.getBuckets();  
//        for(Bucket bt : buckets)  
//        {  
//            System.out.println(bt.getKey() + " " + bt.getDocCount());  
//        }  
//	}
	
//	//单表根据查询条件的分页    
//    @Override  
//    public Object queryTotalList(Pageable pageable) {  
//  
//        Specification<UserInteractionArticle> spec = getWhereClause();    
//  
//        Page<UserInteractionArticle> pageRst = userInteractionArticleDao.findAll(spec, pageable);    
//          
//        Map<String,Object> resMap = new HashMap<>();  
//        //Page的遍历  
//        if(pageRst.iterator().hasNext()) {  
//            String category = pageRst.iterator().next().getCategory();  
//            if(resMap.containsKey(category)) {  
//                resMap.put(category, (Integer)resMap.get(category)+1);  
//            }else {  
//                resMap.put(category, 1);  
//            }  
//        }  
//        resMap.put("list", pageRst);  
//        return resMap;  
//    }  
//  
//    //制造查询条件结果(建议存放map)    
//    private Specification<UserInteractionArticle> getWhereClause() {    
//        return new Specification<UserInteractionArticle>() {    
//            public Predicate toPredicate(Root<UserInteractionArticle> r, CriteriaQuery<?> q, CriteriaBuilder cb) {    
//                Predicate predicate = cb.conjunction();    
//                    //添加查询条件  
//                    predicate = cb.equal(r.get("ifUsed").as(Integer.class), 0);    
//                    //添加group by  
//                    q.groupBy(r.get("category"));  
//                return predicate;    
//            }    
//        };    
//    }  
	
	
	
	
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
				new UserInteractionArticle(userInteractionArticleDao.count() + 1, user.getId(), articleId, mode));
		} else {
			if(userInteractionArticle.getMode() != mode) {
				userInteractionArticleservice.updateUserInteractionArticle(
					new UserInteractionArticle(userInteractionArticle.getId(), user.getId(), articleId, mode));
			} else {
				userInteractionArticleservice.deleteUserInteractionArticle(new Long[] {userInteractionArticle.getId()});
			}
		}
		
	}
	
}
