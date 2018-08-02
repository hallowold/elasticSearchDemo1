package com.example.demo.serviceTests;

import com.example.demo.common.baseDAO.DaoCriteria;
import com.example.demo.common.baseDAO.QueryOperatorEnum;
import com.example.demo.entity.Article;
import com.example.demo.entity.OperatorLog;
import com.example.demo.security.config.LoginSuccessHandler;
import com.example.demo.service.OperatorLogService;
import com.example.demo.service.impl.ArticleServiceImpl;
import com.example.demo.service.impl.OperatorLogServiceImpl;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @version : V1.2
 * @author : liuqitian
 * @date : 2018/7/19 16:00
 * 自动化测试用例，选中所有测试用例文件，执行文件即可
 * 注意执行顺序是以方法名排序，统一使用testSer_AB_C()的形式
 *  AB用来排序，均为0-1-a-z-A-Z的顺序，从前到后举几个例子：01，02，03，0a,0h,0F,0Z,10,11,1f,1p,1A...
 *  C用来指示方法名,不引起歧义即可，使用驼峰规则。
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ServiceOperatorLogTests {

    @Autowired
    private OperatorLogService operatorLogService;

    @Test
    public void testSer_00_AddOperatorLog() {
        OperatorLog entity = new OperatorLog();
        entity.setUserName("testUser");
        entity.setCreateDate(new Date());
        entity.setModuleName("testModule");
        entity.setOprType("testType");
        entity.setDetail("testDetail");

        operatorLogService.addOperator(entity);
    }

    @Test
    public void testSer_01_FindOperators() {
        List<DaoCriteria> criterias = new ArrayList<>();
        criterias.add(new DaoCriteria("moduleName", QueryOperatorEnum.LIKE.getName(), "testModule"));
        criterias.add(new DaoCriteria("userName", QueryOperatorEnum.LIKE.getName(), "testUser"));
        List<OperatorLog> resultList = operatorLogService.findOperators(criterias);
        resultList.forEach(System.out::println);
    }


}
