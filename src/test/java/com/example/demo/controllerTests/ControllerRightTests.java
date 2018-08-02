package com.example.demo.controllerTests;

import com.example.demo.security.dao.SysRightDao;
import com.example.demo.security.entity.SysRight;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @version : V1.2
 * @author : liuqitian
 * @date : 2018/7/19 16:00
 * 自动化测试用例，选中所有测试用例文件，执行文件即可
 * 注意执行顺序是以方法名排序，统一使用testCon_AB_C()的形式
 *  AB用来排序，均为0-1-a-z-A-Z的顺序，从前到后举几个例子：01，02，03，0a,0h,0F,0Z,10,11,1f,1p,1A...
 *  C用来指示方法名,不引起歧义即可，使用驼峰规则。
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ControllerRightTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    /**
     * 新增权限，[POST,/right/right]
     */
    @Test
    public void testCon_00_AddRight() throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("methodName", "methodName_testAdding_doNotUseIt");
        map.put("methodPath", "/testAdding_doNotUseIt");
        map.put("methodType", "POST");
        map.put("name", "testAdding_doNotUseIt");
        map.put("remark","参数说明");
        map.put("rightUrl", "/test/test/{param}");

        mockMvc.perform(post("/right/right")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 根据名称模糊查询权限，[GET,/right/rights/name/{name}]
     */
    @Test
    public void testCon_01_fuzzyFindRightsByName() throws Exception{
        mockMvc.perform(
                get("/right/rights/name/{name}", "testAdding_doNotUseIt"))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 修改权限，[PUT,/right/right]
     */
    @Test
    public void testCon_02_updateRight() throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<SysRight> originEntities = wac.getBean(SysRightDao.class)
                .findByNameLike("testAdding_doNotUseIt");
        SysRight originEntity = originEntities.get(0);

        map.put("id", originEntity.getId());
        map.put("methodName", originEntity.getMethodName() + "_changed");
        map.put("methodPath", originEntity.getMethodPath() + "_changed");
        map.put("methodType", "POST");
        map.put("name", originEntity.getName() + "_changed");
        map.put("remark", originEntity.getRemark() + "_changed");
        map.put("rightUrl", originEntity.getRightUrl() + "/changed");

        mockMvc.perform(put("/right/right")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 删除权限，[DELETE,/right/rights]
     */
    @Test
    public void testCon_03_deleteRights() throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<SysRight> rights = wac.getBean(SysRightDao.class)
                .findByNameLike("%testAdding_doNotUseIt%");
        List<Integer> idsList = new ArrayList<>();
        rights.forEach(sysRight -> idsList.add(sysRight.getId()));
        map.put("ids", idsList.toArray(new Integer[]{}));

        mockMvc.perform(delete("/right/rights")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 获取所有权限，[GET,/right/rights]
     */
    @Test
    public void testCon_04_getAllRights() throws Exception {
        System.out.println(mockMvc.perform(post("/right/rights?size=10&page=1"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString());
    }
}
