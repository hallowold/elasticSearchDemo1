package com.example.demo.dao;

import com.example.demo.common.baseDAO.BaseDAO;
import com.example.demo.entity.OperatorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : liuqitian
 * @date : 2018/8/1 12:22
 * @version : V1.2
 * 操作日志dao
 */
@Repository
public interface OperatorLogDao extends BaseDAO<OperatorLog, Integer> {

    Integer deleteByIdIn(Integer[] ids);

}
