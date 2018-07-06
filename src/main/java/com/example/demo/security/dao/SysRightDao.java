package com.example.demo.security.dao;

import com.example.demo.security.entity.SysRight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : liuqitian
 * @Date: 2018/7/2 14:43
 * @Version: V1.0
 * @Description:
 */
public interface SysRightDao extends JpaRepository<SysRight, Integer> {

    /**
     * 批量删除
     * @param ids id数组
     * @return Integer 删除的数量
     */
    Integer deleteByIdIn(Integer[] ids);

    /**
     * 模糊查询
     * @param name 查询字段
     * @return  List    查询结果集
     */
    List<SysRight> findByNameLike(String name);
}
