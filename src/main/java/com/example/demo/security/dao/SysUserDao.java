package com.example.demo.security.dao;

import com.example.demo.security.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : liuqitian
 * @Date: 2018/7/2 10:37
 * @Version: V1.0
 * @Description:
 */
@Repository
public interface SysUserDao extends JpaRepository<SysUser, Integer> {

    /**
     * 通过登录名查找
     * @param loginName 查询字符串
     * @return SysUser 查询结果
     */
    SysUser findByLoginName(String loginName);

    /**
     * 通过登录名模糊查询
     * @param loginName 查询字符串
     * @return List<SysUser> 查询结果集
     */
    List<SysUser> findByLoginNameLike(String loginName);

    /**
     * 通过ids批量删除
     * @param ids id数组
     * @return Integer 成功删除信息条数
     */
    Integer deleteByIdIn(Integer[] ids);
}
