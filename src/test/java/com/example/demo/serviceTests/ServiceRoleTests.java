package com.example.demo.serviceTests;

import com.example.demo.security.entity.SysRole;
import com.example.demo.service.RoleService;
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
public class ServiceRoleTests {

    @Autowired
    private RoleService roleSercice;

    @Test
    public void testSer_00_AddRole() throws Exception{
        Map<String, Object> dataMap = new HashMap<>();
        SysRole entity = new SysRole();
        entity.setName("testAdding_doNotUseIt");
        dataMap.put("entity", entity);
        dataMap.put("rightIds", new Integer[] {1,2,3,4});

        roleSercice.addRole(dataMap);
    }

    @Test
    public void testSer_01_updateRole() throws Exception{
        Map<String, Object> dataMap = new HashMap<>();
        SysRole entity = roleSercice.fuzzyFindByName("testAdding_doNotUseIt").get(0);
        entity.setName(entity.getName() + "_changed");
        dataMap.put("entity", entity);
        dataMap.put("rightIds", new Integer[] {1});
        roleSercice.updateRole(dataMap);
    }

    @Test
    public void testSer_02_FuzzyFindByName() throws Exception{
        List<SysRole> resultList = roleSercice.fuzzyFindByName("testAdding_doNotUseIt");
        resultList.forEach(result -> System.out.println(result));
    }

    @Test
    public void testSer_03_findById() throws Exception{
        System.out.println(roleSercice.findById(
                roleSercice.fuzzyFindByName("testAdding_doNotUseIt").get(0).getId()));
    }

    @Test
    public void testSer_04_findAllRoles() {
        roleSercice.findAllRole().forEach(System.out::println);
    }

    @Test
    public void testSer_05_deleteRole() throws Exception{
        List<SysRole> resultList = roleSercice.fuzzyFindByName("testAdding_doNotUseIt");
        List<Integer> idList = new ArrayList<>();
        resultList.forEach(result -> idList.add(result.getId()));
        roleSercice.deleteRole(idList.toArray(new Integer[]{}));
    }

}
