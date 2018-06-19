package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.config.ModuleNameEnum;
import com.example.demo.common.util.ResponseUtil;
import com.example.demo.common.util.createRequestEntityUtil.ArticleRequestUtil;
import com.example.demo.entity.Article;
import com.example.demo.entity.UserInteractionArticle;
import com.example.demo.request.article.ArticleCreateRequest;
import com.example.demo.request.article.ArticleUpdateRequest;
import com.example.demo.response.ResponseData;
import com.example.demo.service.ArticleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 文章控制器
 * @author liuqitian
 * @date 2018年6月11日
 */
@Api(value="/testdemo1", tags="文章接口模块")
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController{
	
	/*
	 * 注入文章服务类
	 */
	@Autowired
	ArticleService articleService;

    /**
     * 新增接口
     * @param 	article
     * @return
     */
    @ApiOperation(value="添加文章信息", notes = "添加文章信息")
    @RequestMapping(value = "/article",method = RequestMethod.POST)  
    public ResponseData addArticle(@RequestBody ArticleCreateRequest article) throws Exception {
    	if(!this.checkIfHasBackgroundUser()) {
    		return ResponseUtil.createResponseDataNeedLogIn();
    	}
    	if(!this.checkIfHasRight(ModuleNameEnum.ARTICLE)) {
    		return ResponseUtil.createResponseDataHasNoRight();
    	}
    	articleService.addArticle(ArticleRequestUtil.createArticleByCreateRequest(article));
        return ResponseUtil.createResponseData(true, "新增成功", null, 200);
    }
    
    /**
     * 删除接口
     * @param 	article
     * @return
     */
    @ApiOperation(value="批量删除文章信息", notes = "批量删除文章信息")
    @RequestMapping(value = "/articles/ids",method = RequestMethod.DELETE)  
    public ResponseData deleteArticle(@RequestBody Long[] ids) throws Exception {
    	if(!this.checkIfHasBackgroundUser()) {
    		return ResponseUtil.createResponseDataNeedLogIn();
    	}
    	if(!this.checkIfHasRight(ModuleNameEnum.ARTICLE)) {
    		return ResponseUtil.createResponseDataHasNoRight();
    	}
    	articleService.deleteArticle(ids);
    	return ResponseUtil.createResponseData(true, "删除成功", null, 200);
    }
    
    /**
     * 修改接口
     * @param 	article
     * @return
     */
    @ApiOperation(value="修改文章信息", notes = "修改文章信息")
    @RequestMapping(value = "/article",method = RequestMethod.PUT)  
    public ResponseData updateArticle(@RequestBody ArticleUpdateRequest article) throws Exception {
    	if(!this.checkIfHasBackgroundUser()){
    		return ResponseUtil.createResponseDataNeedLogIn();
    	}
    	if(!this.checkIfHasRight(ModuleNameEnum.ARTICLE)) {
    		return ResponseUtil.createResponseDataHasNoRight();
    	}
    	articleService.updateArticle(ArticleRequestUtil.createArticleByUpdateRequest(article));
    	return ResponseUtil.createResponseData(true, "修改成功", null, 200);
    }
    
    /**
     * 获取所有文章接口
     * @return
     */
    @ApiOperation(value="获取所有文章信息", notes = "获取所有文章信息")
    @RequestMapping(value = "/articles",method = RequestMethod.GET)  
    public ResponseData searchAllArticles() throws Exception {
    	if(!this.checkIfHasBackgroundUser()) {
    		return ResponseUtil.createResponseDataNeedLogIn();
    	}
    	Iterable<Article> results = articleService.findAllArticle();
    	return ResponseUtil.createResponseData(true, "查询", results, 200);
    }
    
    /**
     * 根据标题查询近似文章列表接口
     * @return
     */
    @ApiOperation(value="根据标题查询近似文章列表", notes = "根据标题查询近似文章列表")
    @RequestMapping(value = "/articles/name/{name}",method = RequestMethod.GET)  
    public ResponseData searchArticleByName(@ApiParam(value = "需要查询的文章标题", required = true, defaultValue = "1") 
		@PathVariable("name") String name) {
    	if(!this.checkIfHasBackgroundUser()) {
    		return ResponseUtil.createResponseDataNeedLogIn();
    	}
    	List<Article> results = articleService.fuzzyFindByName(name);
    	return ResponseUtil.createResponseData(true, "查询", results, 200);
    }
    
    /**
     * 记录互动(点赞或踩)接口
     * @param 	articleId	文章id
     * @param	mode		互动模式
     * @return
     */
    @ApiOperation(value="记录互动(点赞或踩)", notes = "记录互动(点赞或踩)")
    @RequestMapping(value = "/interaction/articleId/{articleId}/mode/{mode}",method = RequestMethod.GET)  
    public ResponseData interaction(@ApiParam(value = "文章id", required = true, defaultValue = "1") 
		@PathVariable("articleId") Long articleId,@ApiParam(value = "互动模式", required = true, defaultValue = "1") 
		@PathVariable("mode") Long mode) throws Exception {
    	
    	if(!this.checkIfHasBackgroundUser()) {
    		return ResponseUtil.createResponseDataNeedLogIn();
    	}
    	if(!this.checkIfHasRight(ModuleNameEnum.ARTICLE)) {
    		return ResponseUtil.createResponseDataHasNoRight();
    	}
    	articleService.interaction(articleId, mode);
        return ResponseUtil.createResponseData(true, "记录成功", null, 200);
    }
    
    @ApiOperation(value="根据文章id查询互动(点赞或踩)", notes = "查询文章id查询互动(点赞或踩)")
    @RequestMapping(value = "/interaction/articleId/{articleId}",method = RequestMethod.GET)  
    public ResponseData interaction(@ApiParam(value = "文章id", required = true, defaultValue = "1") 
		@PathVariable("articleId") Long articleId) throws Exception {
    	
//    	if(!this.checkIfHasBackgroundUser()) {
//    		return ResponseUtil.createResponseDataNeedLogIn();
//    	}
//    	if(!this.checkIfHasRight(ModuleNameEnum.ARTICLE)) {
//    		return ResponseUtil.createResponseDataHasNoRight();
//    	}
    	Object results = articleService.findTest(articleId);
        return ResponseUtil.createResponseData(true, "记录成功", results, 200);
    }
    
    
}