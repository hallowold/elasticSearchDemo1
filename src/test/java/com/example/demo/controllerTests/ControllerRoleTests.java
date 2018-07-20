package com.example.demo.controllerTests;

import com.example.demo.security.dao.SysRoleDao;
import com.example.demo.security.entity.SysRole;
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
public class ControllerRoleTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    /**
     * 新增角色，[POST,/role/role]
     */
    @Test
    public void testCon_00_AddRole() throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("name", "testAdding_doNotUseIt");
        map.put("rightIds", new Integer[]{1,2,3,4,5,6,7});

        mockMvc.perform(post("/role/role")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 根据名称模糊查询角色，[GET,/role/roles/name/{name}]
     */
    @Test
    public void testCon_01_fuzzyFindRolesByName() throws Exception{
        mockMvc.perform(
                get("/role/roles/name/{name}", "testAdding_doNotUseIt"))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 修改角色，[PUT,/role/role]
     */
    @Test
    public void testCon_02_updateRole() throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<SysRole> sysRoles = wac.getBean(SysRoleDao.class)
                .findByNameLike("testAdding_doNotUseIt");
        SysRole originEntity = sysRoles.get(0);

        map.put("id", originEntity.getId());
        map.put("name", originEntity.getName() + "_changed");
        map.put("rightIds", new Integer[] {1,3,4,5,7,10});

        mockMvc.perform(put("/role/role")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 删除角色，[DELETE,/role/roles]
     */
    @Test
    public void testCon_03_deleteRoles() throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<SysRole> roles = wac.getBean(SysRoleDao.class)
                .findByNameLike("%testAdding_doNotUseIt%");
        List<Integer> idsList = new ArrayList<>();
        roles.forEach(sysRole -> idsList.add(sysRole.getId()));
        map.put("ids", idsList.toArray(new Integer[]{}));

        mockMvc.perform(delete("/role/roles")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 获取所有角色，[GET,/role/roles]
     */
    @Test
    public void testCon_04_getAllRoles() throws Exception {
        mockMvc.perform(get("/role/roles"))
                .andExpect(status().isOk())
                .andReturn();
    }
}
