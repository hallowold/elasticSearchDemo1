package com.example.demo.controller;

import java.util.List;

import com.example.demo.common.config.StaticValues;
import com.example.demo.common.util.DateUtil;
import com.example.demo.common.util.requestcreater.ArticleRequestUtil;
import com.example.demo.request.EsIdsRequest;
import com.example.demo.response.BucketResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.util.ResponseUtil;
import com.example.demo.entity.Article;
import com.example.demo.entity.UserInteractionArticle;
import com.example.demo.request.article.ArticleCreateRequest;
import com.example.demo.request.article.ArticleUpdateRequest;
import com.example.demo.response.ResponseData;
import com.example.demo.service.ArticleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.validation.Valid;

/**
 * 文章控制器
 * @author liuqitian
 * @date 2018年6月11日
 */
@Api(value="/testdemo1", tags="文章接口模块")
@RestController
@RequestMapping("/article")
@Validated
public class ArticleController{

    private static final Log LOGGER = LogFactory.getLog(ArticleController.class);

	@Autowired
	ArticleService articleService;

    /**
     * 新增接口
     * @param 	article
     */
    @ApiOperation(value="添加文章信息", notes = "添加文章信息")
    @RequestMapping(value = "/article",method = RequestMethod.POST)
    public ResponseData addArticle(@RequestBody @Valid ArticleCreateRequest article, BindingResult bindResult) throws Exception {
    	articleService.addArticle(ArticleRequestUtil.createArticleByCreateRequest(article));
        LOGGER.info("执行新增文章信息操作，操作用户为[" + SecurityContextHolder.getContext().getAuthentication().getName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
        return ResponseUtil.createResponseData(true, "新增成功", null, 200);
    }
    
    /**
     * 删除接口
     */
    @ApiOperation(value="批量删除文章信息", notes = "批量删除文章信息")
    @RequestMapping(value = "/articles",method = RequestMethod.DELETE)
    public ResponseData deleteArticle(@RequestBody EsIdsRequest request) throws Exception {
        Long num = articleService.deleteArticle(request.getIds());
        LOGGER.info("执行删除文章信息操作，操作用户为[" + SecurityContextHolder.getContext().getAuthentication().getName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
    	return ResponseUtil.createResponseData(true, "删除成功", num, 200);
    }
    
    /**
     * 修改接口
     * @param 	article
     */
    @ApiOperation(value="修改文章信息", notes = "修改文章信息")
    @RequestMapping(value = "/article",method = RequestMethod.PUT)  
    public ResponseData updateArticle(@RequestBody @Valid ArticleUpdateRequest article, BindingResult bindResult) throws Exception {
        System.out.println(article.getId());
    	articleService.updateArticle(ArticleRequestUtil.createArticleByUpdateRequest(article));
        LOGGER.info("执行修改文章信息操作，操作用户为[" + SecurityContextHolder.getContext().getAuthentication().getName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
    	return ResponseUtil.createResponseData(true, "修改成功", null, 200);
    }
    
    /**
     * 获取所有文章接口
     */
    @ApiOperation(value="获取所有文章信息", notes = "获取所有文章信息")
    @RequestMapping(value = "/articles",method = RequestMethod.GET)  
    public ResponseData searchAllArticles(){
    	Iterable<Article> results = articleService.findAllArticle();
        LOGGER.info("执行获取所有文章信息操作，操作用户为[" + SecurityContextHolder.getContext().getAuthentication().getName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
    	return ResponseUtil.createResponseData(true, StaticValues.SEARCH, results, 200);
    }
    
    /**
     * 根据标题模糊查询接口
     */
    @ApiOperation(value="根据标题查询近似文章列表", notes = "根据标题查询近似文章列表")
    @RequestMapping(value = "/articles/name/{name}",method = RequestMethod.GET)  
    public ResponseData searchArticleByNameFuzzy(@ApiParam(value = "需要查询的文章标题", required = true, defaultValue = "1")
		@PathVariable("name") String name) {

    	List<Article> results = articleService.fuzzyFindByName(name);
        LOGGER.info("执行查询文章信息操作，操作用户为[" + SecurityContextHolder.getContext().getAuthentication().getName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
    	return ResponseUtil.createResponseData(true, StaticValues.SEARCH, results, 200);
    }
    
    /**
     * 记录互动(点赞或踩)接口
     * @param 	articleId	文章id
     * @param	mode		互动模式
     */
    @ApiOperation(value="记录互动(点赞或踩)", notes = "记录互动(点赞或踩)")
    @RequestMapping(value = "/interaction/articleId/{articleId}/mode/{mode}",method = RequestMethod.GET)
    public ResponseData addInteraction(@ApiParam(value = "文章id", required = true, defaultValue = "1")
		@PathVariable("articleId") String articleId,@ApiParam(value = "互动模式", required = true, defaultValue = "1")
		@PathVariable("mode") Long mode) throws Exception {

    	articleService.interaction(articleId, mode);
        LOGGER.info("执行文章互动操作，操作用户为[" + SecurityContextHolder.getContext().getAuthentication().getName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
        return ResponseUtil.createResponseData(true, "记录成功", null, 200);
    }
    
    /**
     * @Auther: liuqitian
     * @Date: 2018/6/21 14:14
     * @Version: V1.0
     * @Param: [mode]
     * @return: com.example.demo.response.ResponseData
     * @Description: 根据行为模式统计各文章的赞/踩数量
     */
    @ApiOperation(value="根据行为模式统计各文章的赞/踩数量", notes = "根据行为模式统计各文章的赞/踩数量")
    @RequestMapping(value = "/interaction/articles/mode/{mode}",method = RequestMethod.GET)
    public ResponseData queryInteraction(@ApiParam(value = "行为模式", required = true, defaultValue = "1")
		@PathVariable("mode") Long mode) throws Exception {

		AggregatedPage<UserInteractionArticle> account = articleService.findByMode(mode);
		BucketResponse br = ResponseUtil.createBucketResponse(account, "articleName");
        return ResponseUtil.createResponseData(true, "记录成功", br, 200);
    }
    
    
}