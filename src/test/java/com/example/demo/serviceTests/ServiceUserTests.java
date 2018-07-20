package com.example.demo.serviceTests;

import com.example.demo.security.entity.SysUser;
import com.example.demo.service.UserService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class ServiceUserTests {

    @Autowired
    private UserService userSercice;

    @Test
    public void testSer_00_AddUser() throws Exception{
        Map<String, Object> dataMap = new HashMap<>();
        SysUser entity = new SysUser();
        entity.setLoginName("testAdding_doNotUseIt");
        entity.setShowName("testAdding_doNotUseIt_showName");
        entity.setEmail("a@b.c");
        entity.setPassword("password");
        dataMap.put("entity", entity);
        dataMap.put("roleIds", new Integer[] {1,2});

        userSercice.addUser(dataMap);
    }

    @Test
    public void testSer_01_updateUser() throws Exception{
        Map<String, Object> dataMap = new HashMap<>();
        SysUser entity = userSercice.fuzzyFindByLoginName("testAdding_doNotUseIt").get(0);
        entity.setLoginName(entity.getLoginName() + "_changed");
        entity.setShowName(entity.getShowName() + "_changed");
        dataMap.put("entity", entity);
        dataMap.put("roleIds", new Integer[] {1});
        userSercice.updateUser(dataMap);
    }

    @Test
    public void testSer_02_FuzzyFindByName() throws Exception{
        List<SysUser> resultList = userSercice.fuzzyFindByLoginName("testAdding_doNotUseIt");
        resultList.forEach(result -> System.out.println(result));
    }

    @Test
    public void testSer_03_findById() throws Exception{
        System.out.println(userSercice.findById(
                userSercice.fuzzyFindByLoginName("testAdding_doNotUseIt").get(0).getId()));
    }

    @Test
    public void testSer_04_findAllUsers() {
        userSercice.findAllUser().forEach(System.out::println);
    }

    @Test
    public void testSer_05_deleteUser() throws Exception{
        List<SysUser> resultList = userSercice.fuzzyFindByLoginName("testAdding_doNotUseIt");
        List<Integer> idList = new ArrayList<>();
        resultList.forEach(result -> idList.add(result.getId()));
        userSercice.deleteUser(idList.toArray(new Integer[]{}));
    }

}
