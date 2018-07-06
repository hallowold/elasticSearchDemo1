package com.example.demo.service.impl;

import com.example.demo.common.config.StaticValues;
import com.example.demo.common.util.KeyNumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.UserInteractionArticleDAO;
import com.example.demo.entity.UserInteractionArticle;
import com.example.demo.exception.Demo1Exception;
import com.example.demo.service.UserInteractionArticleService;

/**
 * 用户与文章交互记录服务实现类
 * 
 * @author liuqitian
 * @date 2018年6月11日
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class UserInteractionArticleServiceImpl implements UserInteractionArticleService {

	@Autowired
	private UserInteractionArticleDAO userInteractionArticleDao;

	/**
	 * 新增交互记录
	 * @param 	userInteractionArticle	交互记录实体
	 */
	@Override
	public void addUserInteractionArticle(UserInteractionArticle userInteractionArticle) {
		userInteractionArticle.setId(KeyNumberUtil.nextId());
		userInteractionArticleDao.save(userInteractionArticle);
	}

	/**
	 * 修改交互记录
	 * @param 	userInteractionArticle	交互记录实体
	 */
	@Override
	public void updateUserInteractionArticle(UserInteractionArticle userInteractionArticle) {
		userInteractionArticleDao.save(userInteractionArticle);
	}

	/**
	 * 删除交互记录
	 * @param 	ids		交互记录id数组
	 */
	@Override
	public void deleteUserInteractionArticle(Long[] ids) {
		userInteractionArticleDao.deleteByIdIn(ids);
	}

	/**
	 * 通过用户id和交互模式查找交互记录
	 * @param 	userId		用户id
	 * @param 	mode		交互模式
	 * @return	userInteractionArticles		交互记录集合
	 * @throws 	Demo1Exception 查询结果集为空时抛出Demo1Exception(StaticValues.SEARCH)
	 */
	@Override
	public Iterable<UserInteractionArticle> findByUserIdAndMode(Integer userId, Long mode) throws Demo1Exception {
		Iterable<UserInteractionArticle> results = userInteractionArticleDao.findByUserIdAndMode(userId, mode);
		if(!results.iterator().hasNext()) {
			throw new Demo1Exception(StaticValues.SEARCH);
		} 
		return results;
	}
	
	/**
	 * 通过文章id和交互模式查找交互记录
	 * @param 	articleId	文章id
	 * @param 	mode		交互模式
	 * @return	userInteractionArticles		交互记录集合
	 * @throws 	Demo1Exception 查询结果集为空时抛出Demo1Exception(StaticValues.SEARCH)
	 */
	@Override
	public Iterable<UserInteractionArticle> findByArticleIdAndMode(String articleId, Long mode) throws Demo1Exception {
		Iterable<UserInteractionArticle> results = userInteractionArticleDao.findByArticleIdAndMode(articleId, mode);
		if(!results.iterator().hasNext()) {
			throw new Demo1Exception(StaticValues.SEARCH);
		} 
		return results;
	}

	/**
	 * 通过用户ids批量删除
	 * @param ids 用户ids
	 * @return Long 删除信息条数
	 */
	@Override
	public Long deleteByUserIds(Integer[] ids) {
		return userInteractionArticleDao.deleteByUserIdIn(ids);
	}

	/**
	 * 通过文章ids批量删除
	 * @param ids 文章ids
	 * @return Long 删除信息条数
	 */
	@Override
	public Long deleteByArticleIds(String[] ids) {
		return userInteractionArticleDao.deleteByArticleIdIn(ids);
	}
	
	/**
	 * 通过文章id和模式，统计符合要求的信息条数
	 * @param 	articleId	文章id
	 * @param 	mode		交互模式
	 * @return	Long 		统计信息条数
	 */
	@Override
	public Long countByArticleIdAndMode(String articleId, Long mode) {
		Long count = 0L;
		count = userInteractionArticleDao.countByArticleIdAndMode(articleId, mode);
		return count;
	}
	
}
