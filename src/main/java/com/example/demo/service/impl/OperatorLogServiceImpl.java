package com.example.demo.service.impl;

import com.example.demo.common.baseDAO.BaseQuerySpecification;
import com.example.demo.common.baseDAO.DaoCriteria;
import com.example.demo.dao.OperatorLogDao;
import com.example.demo.entity.OperatorLog;
import com.example.demo.service.OperatorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章服务实现类
 * @author liuqitian
 * @date 2018年6月11日
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class OperatorLogServiceImpl implements OperatorLogService {

    @Autowired
    OperatorLogDao operatorLogDao;

    @Override
    public void addOperator(OperatorLog operatorLog) {
        operatorLogDao.save(operatorLog);
    }

    @Override
    public Integer deleteOperators(Integer[] ids) {
        return 1;
//        return operatorLogDao.deleteByIdIn(ids);
    }

    @Override
    public Page<OperatorLog> findOperators(List<DaoCriteria> criterias, Pageable page) {
        BaseQuerySpecification<OperatorLog> specification = new BaseQuerySpecification<OperatorLog>(criterias);
        return operatorLogDao.findAll(specification, page);
    }

    @Override
    public List<OperatorLog> findOperators(List<DaoCriteria> criterias) {
        BaseQuerySpecification<OperatorLog> specification = new BaseQuerySpecification<OperatorLog>(criterias);
        return operatorLogDao.findAll(specification);
    }

}
