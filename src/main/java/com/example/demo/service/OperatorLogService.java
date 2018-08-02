package com.example.demo.service;

import com.example.demo.common.baseDAO.DaoCriteria;
import com.example.demo.entity.OperatorLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 操作日志定义接口
 * @author liuqitian
 * @date 2018年8月1日
 */
public interface OperatorLogService {

    void addOperator(OperatorLog operatorLog);

    Integer deleteOperators(Integer[] ids);

    Page<OperatorLog> findOperators(List<DaoCriteria> criterias, Pageable page);

    List<OperatorLog> findOperators(List<DaoCriteria> criterias);

}
