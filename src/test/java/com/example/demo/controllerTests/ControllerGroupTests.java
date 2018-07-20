package com.example.demo.controllerTests;

import com.example.demo.security.dao.SysGroupDao;
import com.example.demo.security.entity.SysGroup;
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
public class ControllerGroupTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    /**
     * 新增机构，[POST,/group/group]
     */
    @Test
    public void testCon_00_AddGroup() throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("name", "testAdding_doNotUseIt");
        map.put("pid", null);
        map.put("roleIds", new Integer[]{1,2});

        mockMvc.perform(post("/group/group")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 根据名称模糊查询机构，[GET,/group/groups/name/{name}]
     */
    @Test
    public void testCon_01_fuzzyFindGroupByName() throws Exception{
        mockMvc.perform(
                get("/group/groups/name/{name}", "testAdding_doNotUseIt"))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 获取指定机构默认的角色，[GET,/group/group/id/roles]
     */
    @Test
    public void testCon_02_getDefaultRolesByGroup() throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<SysGroup> sysGroups = wac.getBean(SysGroupDao.class)
                .findByNameLike("testAdding_doNotUseIt");
        SysGroup originEntity = sysGroups.get(0);

        map.put("id", originEntity.getId());

        mockMvc.perform(get("/group/group/id/roles")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 修改机构，[PUT,/group/group]
     */
    @Test
    public void testCon_03_updateGroup() throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<SysGroup> sysGroups = wac.getBean(SysGroupDao.class)
                .findByNameLike("testAdding_doNotUseIt");
        SysGroup originEntity = sysGroups.get(0);

        map.put("id", originEntity.getId());
        map.put("name", originEntity.getName()+ "_updated");
        map.put("roleIds", new Integer[] {1});

        mockMvc.perform(put("/group/group")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 删除机构，[DELETE,/group/groups]
     */
    @Test
    public void testCon_04_deleteGroups() throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<SysGroup> groups = wac.getBean(SysGroupDao.class)
                .findByNameLike("%testAdding_doNotUseIt%");
        List<Integer> idsList = new ArrayList<>();
        groups.forEach(sysRole -> idsList.add(sysRole.getId()));
        map.put("ids", idsList.toArray(new Integer[]{}));

        mockMvc.perform(delete("/group/groups")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 获取所有机构，[GET,/group/groups]
     */
    @Test
    public void testCon_05_getAllGroups() throws Exception {
        mockMvc.perform(get("/group/groups"))
                .andExpect(status().isOk())
                .andReturn();
    }
}
