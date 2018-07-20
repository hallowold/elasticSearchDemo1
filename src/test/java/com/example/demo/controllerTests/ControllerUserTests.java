package com.example.demo.controllerTests;

import com.example.demo.security.dao.SysUserDao;
import com.example.demo.security.entity.SysUser;
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
public class ControllerUserTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    /**
     * 新增用户，[POST,/user/user]
     */
    @Test
    public void testCon_00_AddUser() throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("loginName", "testAdding_doNotUseIt");
        map.put("showName", "testAdding_doNotUseIt_showName");
        map.put("email", "a@b.c");
        map.put("password", "password");
        map.put("roleIds", new Integer[]{1,2});

        mockMvc.perform(post("/user/user")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 根据登录名模糊查询用户，[GET,/user/users/loginName/{loginName}]
     */
    @Test
    public void testCon_01_fuzzyFindUsersByLoginName() throws Exception{
        mockMvc.perform(
                get("/user/users/loginName/{loginName}", "testAdding_doNotUseIt"))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 修改用户，[PUT,/user/user]
     */
    @Test
    public void testCon_02_updateUser() throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<SysUser> sysUsers = wac.getBean(SysUserDao.class)
                .findByLoginNameLike("testAdding_doNotUseIt");
        SysUser originEntity = sysUsers.get(0);

        map.put("id", originEntity.getId());
        map.put("loginName", originEntity.getLoginName() + "_updated");
        map.put("showName", originEntity.getShowName() + "_updated");
        map.put("email", originEntity.getEmail() + "_updated");
        map.put("password", "password");
        map.put("roleIds", new Integer[] {1});

        mockMvc.perform(put("/user/user")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 删除角色，[DELETE,/user/users]
     */
    @Test
    public void testCon_03_deleteUsers() throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<SysUser> roles = wac.getBean(SysUserDao.class)
                .findByLoginNameLike("%testAdding_doNotUseIt%");
        List<Integer> idsList = new ArrayList<>();
        roles.forEach(sysRole -> idsList.add(sysRole.getId()));
        map.put("ids", idsList.toArray(new Integer[]{}));

        mockMvc.perform(delete("/user/users")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 获取所有权限，[GET,/user/users]
     */
    @Test
    public void testCon_04_getAllUsers() throws Exception {
        mockMvc.perform(get("/user/users"))
                .andExpect(status().isOk())
                .andReturn();
    }
}
