package com.example.demo.security.dao;

import com.example.demo.common.baseDAO.BaseDAO;
import com.example.demo.security.entity.SysRight;

import java.util.List;

/**
 * @author : liuqitian
 * @date : 2018/7/2 14:43
 * @version : V1.0
 * 权限dao
 */
public interface SysRightDao extends BaseDAO<SysRight, Integer> {

    Integer deleteByIdIn(Integer[] ids);

    List<SysRight> findByNameLike(String name);
}
