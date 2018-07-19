package com.example.demo.service.impl;

import com.example.demo.common.config.StaticValues;
import com.example.demo.common.util.KeyNumberUtil;
import com.example.demo.dao.UserInteractionArticleDAO;
import com.example.demo.entity.UserInteractionArticle;
import com.example.demo.exception.Demo1Exception;
import com.example.demo.service.UserInteractionArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户与文章交互记录服务实现类
 * @author liuqitian
 * @date 2018年6月11日
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class UserInteractionArticleServiceImpl implements UserInteractionArticleService {

	@Autowired
	private UserInteractionArticleDAO userInteractionArticleDao;

	@Override
	public void addUserInteractionArticle(UserInteractionArticle userInteractionArticle) {
		userInteractionArticle.setId(KeyNumberUtil.nextId());
		userInteractionArticleDao.save(userInteractionArticle);
	}

	@Override
	public void updateUserInteractionArticle(UserInteractionArticle userInteractionArticle) {
		userInteractionArticleDao.save(userInteractionArticle);
	}

	@Override
	public void deleteUserInteractionArticle(Long[] ids) {
		userInteractionArticleDao.deleteByIdIn(ids);
	}

	@Override
	public Iterable<UserInteractionArticle> findByUserIdAndMode(Integer userId, Long mode) throws Demo1Exception {
		Iterable<UserInteractionArticle> results = userInteractionArticleDao.findByUserIdAndMode(userId, mode);
		if(!results.iterator().hasNext()) {
			throw new Demo1Exception(StaticValues.SEARCH);
		} 
		return results;
	}
	
	@Override
	public Iterable<UserInteractionArticle> findByArticleIdAndMode(String articleId, Long mode) throws Demo1Exception {
		Iterable<UserInteractionArticle> results = userInteractionArticleDao.findByArticleIdAndMode(articleId, mode);
		if(!results.iterator().hasNext()) {
			throw new Demo1Exception(StaticValues.SEARCH);
		} 
		return results;
	}

	@Override
	public Long deleteByUserIds(Integer[] userIds) {
		return userInteractionArticleDao.deleteByUserIdIn(userIds);
	}

	@Override
	public Long deleteByArticleIds(String[] articleIds) {
		return userInteractionArticleDao.deleteByArticleIdIn(articleIds);
	}
	
	@Override
	public Long countByArticleIdAndMode(String articleId, Long mode) {
		Long count = 0L;
		count = userInteractionArticleDao.countByArticleIdAndMode(articleId, mode);
		return count;
	}
	
}
