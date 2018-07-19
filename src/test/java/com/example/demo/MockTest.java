package com.example.demo;

import com.example.demo.dao.ArticleDAO;
import com.example.demo.entity.Article;
import com.example.demo.security.config.LoginSuccessHandler;
import com.example.demo.security.dao.SysGroupDao;
import com.example.demo.security.dao.SysRightDao;
import com.example.demo.security.dao.SysRoleDao;
import com.example.demo.security.dao.SysUserDao;
import com.example.demo.security.entity.SysGroup;
import com.example.demo.security.entity.SysRight;
import com.example.demo.security.entity.SysRole;
import com.example.demo.security.entity.SysUser;
import com.example.demo.security.service.CustomUserDetailsService;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author : liuqitian
 * @date : 2018/7/10 17:37
 * @version : V1.2
 * 自动化测试用例，执行文件即可
 * 注意执行顺序是以方法名排序，统一使用testAB_C()的形式
 *  A是模块编号按顺序排即可，B是模块内方法的执行顺序，C用来指示方法名,不引起歧义即可，使用驼峰规则。
 *  AB均为0-1-a-z-A-Z的顺序
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MockTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    /**
     * 在执行其他test之前，先模拟登录以存放用户，我们统一模拟admin用户登录。
     *  注意这一段是spring security的做法
     */
    @Before
    public void userLoginSpringSecurity() {
        //从spring容器中获取UserDetailsService(这个从数据库根据用户名查询用户信息,及加载权限的service)
        CustomUserDetailsService userDetailsService = wac.getBean(CustomUserDetailsService.class);
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

        //根据用户名username加载userDetails
        UserDetails userDetails = userDetailsService.loadUserByUsername("ADMIN");
        //根据userDetails构建新的Authentication,这里使用了
        //PreAuthenticatedAuthenticationToken当然可以用其他token,如UsernamePasswordAuthenticationToken
        PreAuthenticatedAuthenticationToken authentication =
                new PreAuthenticatedAuthenticationToken(userDetails, userDetails.getPassword(),userDetails.getAuthorities());
        //设置authentication中details
        authentication.setDetails(new WebAuthenticationDetails(request));
        //存放authentication到SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * 新增文章，[POST,/article/article]
     */
    @Test
    public void test00_AddArticle() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "articleAutoTest_doNotUseIt>?<,");
        mockMvc.perform(post("/article/article")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 记录互动，[GET,/interaction/articleId/{articleId}/mode/{mode}
     */
    @Test
    public void test01_AddInteraction() throws Exception {
        Article article = wac.getBean(ArticleDAO.class)
                .findByNameAndAuthorLoginName("articleAutoTest_doNotUseIt", "ADMIN").get(0);
        mockMvc.perform(
                get("/article/interaction/articleId/{articleId}/mode/{mode}", article.getId(), 1))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 分类查询互动，[GET,/interaction/articles/mode/{mode}]
     */
    @Test
    public void test02_QueryInteraction() throws Exception {
        mockMvc.perform(
                get("/article/interaction/articles/mode/{mode}",1))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 修改文章，[PUT,/article/article]
     */
    @Test
    public void test03_PutArticle() throws Exception {
        Map<String, Object> map = new HashMap<>();
        Article article = wac.getBean(ArticleDAO.class)
                .findByNameAndAuthorLoginName("articleAutoTest_doNotUseIt", "ADMIN").get(0);
        map.put("id", "1234567890123456781");
        map.put("name", "articleAutoTest_doNotUseIt_BeChanged");
        map.put("author", article.getAuthor());
        System.out.println(mockMvc.perform(put("/article/article")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString());
    }

    /**
     * 模糊查询文章，[GET,/article/articles/name/{name}]
     */
    @Test
    public void test04_FuzzyFindArticlesByName() throws Exception {
        mockMvc.perform(get("/article/articles/name/{name}", "*articleAutoTest_doNotUseIt*"))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 删除文章，[DELETE,/article/article]
     */
    @Test
    public void test05_DeleteArticles() throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<Article> articles = wac.getBean(ArticleDAO.class)
                .findByNameAndAuthorLoginName("*articleAutoTest_doNotUseIt*", "ADMIN");
        List<String> idList = new ArrayList<>();
        articles.forEach(article -> idList.add(article.getId()));
        map.put("ids", idList.toArray(new String[]{}));

        mockMvc.perform(delete("/article/articles")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)) )
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 查询所有文章，[GET,/article/articles]
     */
    @Test
    public void test06_GetAllArticles() throws Exception {
        mockMvc.perform(get("/article/articles"))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 新增权限，[POST,/right/right]
     */
    @Test
    public void test10_AddRight() throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("methodName", "自动测试_doNotUseIt");
        map.put("methodPath", "/autoTest_doNotUseId");
        map.put("methodType", "POST");
        map.put("name", "自动测试_doNotUseIt_name");
        map.put("remark","参数说明");
        map.put("rightUrl", "/test/test");

        mockMvc.perform(post("/right/right")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 根据名称模糊查询权限，[GET,/right/rights/name/{name}]
     */
    @Test
    public void test11_fuzzyFindRightsByName() throws Exception{
        mockMvc.perform(
                //url传中文要记得指定字符集utf-8
                get("/right/rights/name/{name}", "自动测试_doNotUseIt_name")
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 修改权限，[PUT,/right/right]
     */
    @Test
    public void test12_updateRight() throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<SysRight> originEntitys = wac.getBean(SysRightDao.class)
                .findByNameLike("自动测试_doNotUseIt_name");
        SysRight originEntity = originEntitys.get(0);

        map.put("id", originEntity.getId());
        map.put("methodName", originEntity.getMethodName() + "_changed");
        map.put("methodPath", originEntity.getMethodPath() + "_changed");
        map.put("methodType", "POST");
        map.put("name", originEntity.getName() + "_changed");
        map.put("remark", originEntity.getRemark() + "_changed");
        map.put("rightUrl", originEntity.getRightUrl() + "_changed");

        mockMvc.perform(put("/right/right")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 删除权限，[DELETE,/right/rights]
     */
    @Test
    public void test13_deleteRights() throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<SysRight> rights = wac.getBean(SysRightDao.class)
                .findByNameLike("%自动测试_doNotUseIt_name%");
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
    public void test14_getAllRights() throws Exception {
        mockMvc.perform(get("/right/rights"))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 新增角色，[POST,/role/role]
     */
    @Test
    public void test20_AddRole() throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("name", "自动测试_doNotUseIt");
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
    public void test21_fuzzyFindRolesByName() throws Exception{
        mockMvc.perform(
                //url传中文要记得指定字符集utf-8
                get("/role/roles/name/{name}", "自动测试_doNotUseIt")
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 修改角色，[PUT,/role/role]
     */
    @Test
    public void test22_updateRole() throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<SysRole> sysRoles = wac.getBean(SysRoleDao.class)
                .findByNameLike("自动测试_doNotUseIt");
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
    public void test23_deleteRoles() throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<SysRole> roles = wac.getBean(SysRoleDao.class)
                .findByNameLike("%自动测试_doNotUseIt%");
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
    public void test24_getAllRoles() throws Exception {
        mockMvc.perform(get("/role/roles"))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 新增用户，[POST,/user/user]
     */
    @Test
    public void test30_AddUser() throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("loginName", "自动测试_loginName_doNotUseIt");
        map.put("showName", "自动测试_showName_doNotUseIt");
        map.put("email", "a@b.c");
        map.put("password", "password");
        map.put("roleIds", new Integer[] {1});

        mockMvc.perform(post("/user/user")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 根据登录名模糊查询用户，[GET,/user/users/loginName/{loginName}]
     */
    @Test
    public void test31_fuzzyFindUsersByLoginName() throws Exception{
        mockMvc.perform(
                //url传中文要记得指定字符集utf-8
                get("/user/users/loginName/{loginName}", "自动测试_loginName_doNotUseIt")
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 修改用户，[PUT,/user/user]
     */
    @Test
    public void test32_updateUser() throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<SysUser> sysUsers = wac.getBean(SysUserDao.class)
                .findByLoginNameLike("自动测试_loginName_doNotUseIt");
        SysUser originEntity = sysUsers.get(0);

        map.put("id", originEntity.getId());
        map.put("loginName", originEntity.getLoginName() + "_changed");
        map.put("showName", originEntity.getShowName() + "_changed");
        map.put("email", originEntity.getEmail() + "_changed");
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
    public void test33_deleteUsers() throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<SysUser> roles = wac.getBean(SysUserDao.class)
                .findByLoginNameLike("%自动测试_loginName_doNotUseIt%");
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
    public void test34_getAllUsers() throws Exception {
        mockMvc.perform(get("/user/users"))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 新增机构，[POST,/group/group]
     */
    @Test
    public void test40_AddGroup() throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("name", "自动测试_name_doNotUseIt");
        map.put("roleIds", new Integer[]{1,2});

        mockMvc.perform(post("/group/group")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 根据登录名模糊查询用户，[GET,/group/groups/name/{name}]
     */
    @Test
    public void test41_fuzzyFindGroupByName() throws Exception{
        mockMvc.perform(
                //url传中文要记得指定字符集utf-8
                get("/group/groups/name/{name}", "自动测试_name_doNotUseIt")
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 获取指定机构默认的角色，[GET,/group/group/id/roles]
     */
    @Test
    public void test42_getDefaultRolesByGroup() throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<SysGroup> sysGroups = wac.getBean(SysGroupDao.class)
                .findByNameLike("自动测试_name_doNotUseIt");
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
    public void test43_updateGroup() throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<SysGroup> sysGroups = wac.getBean(SysGroupDao.class)
                .findByNameLike("自动测试_name_doNotUseIt");
        SysGroup originEntity = sysGroups.get(0);

        map.put("id", originEntity.getId());
        map.put("name", originEntity.getName()+ "_changed");
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
    public void test44_deleteGroups() throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<SysGroup> groups = wac.getBean(SysGroupDao.class)
                .findByNameLike("%自动测试_name_doNotUseIt%");
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
    public void test45_getAllGroups() throws Exception {
        mockMvc.perform(get("/group/groups"))
                .andExpect(status().isOk())
                .andReturn();
    }
}
