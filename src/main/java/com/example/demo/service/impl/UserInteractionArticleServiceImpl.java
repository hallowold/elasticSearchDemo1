package com.example.demo.service.impl;

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
public class UserInteractionArticleServiceImpl implements UserInteractionArticleService {

	@Autowired
	private UserInteractionArticleDAO userInteractionArticleDao;

	/**
	 * 新增用户与文章交互记录
	 * 
	 * @param userInteractionArticle
	 *            用户与文章交互记录实体
	 * @return boolean
	 */
	@Transactional
	public void addUserInteractionArticle(UserInteractionArticle userInteractionArticle) {
		userInteractionArticleDao.save(userInteractionArticle);
	}

	/**
	 * 修改用户与文章交互记录
	 * 
	 * @param userInteractionArticle
	 *            用户与文章交互记录实体
	 * @return boolean
	 */
	@Transactional
	public void updateUserInteractionArticle(UserInteractionArticle userInteractionArticle) {
		userInteractionArticleDao.save(userInteractionArticle);
	}

	/**
	 * 删除用户与文章交互记录
	 * @param 	ids		用户与文章交互记录id数组
	 * @return	boolean
	 */
	@Transactional
	public void deleteUserInteractionArticle(Long[] ids) {
		userInteractionArticleDao.deleteByIdIn(ids);
	}
	
	/**
	 * 通过用户id和交互模式查找交互记录
	 * @param 	userInteractionArticleId		用户id
	 * @param 	mode		交互模式
	 * @return	userInteractionArticles		交互记录集合
	 * @throws 	Demo1Exception
	 */
	@Transactional
	public Iterable<UserInteractionArticle> findByUserIdAndMode(Long userId, Long mode) throws Demo1Exception {
		Iterable<UserInteractionArticle> results = userInteractionArticleDao.findByUserIdAndMode(userId, mode);
		if(!results.iterator().hasNext()) {
			throw new Demo1Exception("查询");
		} 
		return results;
	}
	
	/**
	 * 通过文章id和交互模式查找交互记录
	 * @param 	articleId	文章id
	 * @param 	mode		交互模式
	 * @return	userInteractionArticles		交互记录集合
	 * @throws 	Demo1Exception
	 */
	@Transactional
	public Iterable<UserInteractionArticle> findByArticleIdAndMode(Long articleId, Long mode) throws Demo1Exception {
		Iterable<UserInteractionArticle> results = userInteractionArticleDao.findByArticleIdAndMode(articleId, mode);
		if(!results.iterator().hasNext()) {
			throw new Demo1Exception("查询");
		} 
		return results;
	}

	/**
	 * @Auther: liuqitian
	 * @Date: 2018/6/21 12:06
	 * @Version: V1.0
	 * @Param: [ids]
	 * @return: java.lang.Long  删除信息条数
	 * @Description: 通过用户ids批量删除
	 */
	public Long deleteByUserIds(Long[] ids) {
		return userInteractionArticleDao.deleteByUserIdIn(ids);
	}

	/**
	 * @Auther: liuqitian
	 * @Date: 2018/6/21 12:06
	 * @Version: V1.0
	 * @Param: [ids]
	 * @return: java.lang.Long  删除信息条数
	 * @Description: 通过文章ids批量删除
	 */
	public Long deleteByArticleIds(Long[] ids) {
		return userInteractionArticleDao.deleteByArticleIdIn(ids);
	}
	
	/**
	 * 通过文章id和模式，统计符合要求的信息条数
	 * @param 	articleId	文章id
	 * @param 	mode		交互模式
	 * @return	Long
	 */
	@Transactional
	public Long countByArticleIdAndMode(Long articleId, Long mode) {
		Long count = 0l;
		count = userInteractionArticleDao.countByArticleIdAndMode(articleId, mode);
		return count;
	}
	
}
