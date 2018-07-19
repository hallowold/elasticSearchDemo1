package com.example.demo.security.dao;

import com.example.demo.security.entity.SysGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : liuqitian
 * @date : 2018/7/16 12:13
 * @version : V1.2
 * 机构dao
 */
public interface SysGroupDao extends JpaRepository<SysGroup, Integer> {

    List<SysGroup> findByNameLike(String name);

    Integer deleteByIdIn(Integer[] ids);
}
