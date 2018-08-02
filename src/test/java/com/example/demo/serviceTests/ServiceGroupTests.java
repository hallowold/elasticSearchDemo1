package com.example.demo.serviceTests;

import com.example.demo.security.entity.SysGroup;
import com.example.demo.security.entity.SysRole;
import com.example.demo.service.SysGroupService;
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
public class ServiceGroupTests {

    @Autowired
    private SysGroupService groupService;

    @Test
    public void testSer_00_AddGroup(){
        Map<String, Object> dataMap = new HashMap<>();
        SysGroup entity = new SysGroup();
        entity.setName("testAdding_doNotUseIt");
        dataMap.put("entity", entity);
        dataMap.put("roleIds", new Integer[] {1,2});

        groupService.addGroup(dataMap);
    }

    @Test
    public void testSer_01_updateGroup() throws Exception{
        Map<String, Object> dataMap = new HashMap<>();
        SysGroup entity = groupService.fuzzyFindByName("testAdding_doNotUseIt").get(0);
        entity.setName(entity.getName() + "_changed");
        dataMap.put("entity", entity);
        dataMap.put("roleIds", new Integer[] {1});
        groupService.updateGroup(dataMap);
    }

    @Test
    public void testSer_02_FuzzyFindByName() throws Exception{
        List<SysGroup> resultList = groupService.fuzzyFindByName("testAdding_doNotUseIt");
        resultList.forEach(result -> System.out.println(result));
    }

    @Test
    public void testSer_03_findRolesByGroupId() throws Exception{
        List<SysRole> resultList = groupService.findRolesByGroupId(
                groupService.fuzzyFindByName("testAdding_doNotUseIt").get(0).getId());
        resultList.forEach(System.out::println);
    }

    @Test
    public void testSer_04_findAllGroups() {
        groupService.findAllGroups().forEach(System.out::println);
    }

    @Test
    public void testSer_05_deleteGroup() throws Exception{
        List<SysGroup> resultList = groupService.fuzzyFindByName("testAdding_doNotUseIt");
        List<Integer> idList = new ArrayList<>();
        resultList.forEach(result -> idList.add(result.getId()));
        groupService.deleteGroups(idList.toArray(new Integer[]{}));
    }

}
