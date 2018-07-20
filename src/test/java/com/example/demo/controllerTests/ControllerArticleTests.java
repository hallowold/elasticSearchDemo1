package com.example.demo.controllerTests;

import com.example.demo.dao.ArticleDAO;
import com.example.demo.entity.Article;
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
public class ControllerArticleTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    /**
     * 这个方法用于模拟用户登录，值得注意的是类似功能的方法，一定要放在文件目录树从上往下第一个会用到它的test文件中，只需要写一次
     */
    @Before
    public void userLoginSpringSecurity() {
        CustomUserDetailsService userDetailsService = wac.getBean(CustomUserDetailsService.class);
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

        UserDetails userDetails = userDetailsService.loadUserByUsername("ADMIN");
        //根据userDetails构建新的Authentication,这里使用了
        //  PreAuthenticatedAuthenticationToken当然可以用其他token,如UsernamePasswordAuthenticationToken
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
    public void testCon_00_AddArticle() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "testAdding_doNotUseIt");
        System.out.println(mockMvc.perform(post("/article/article")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString());
    }

    /**
     * 记录互动，[GET,/interaction/articleId/{articleId}/mode/{mode}
     */
    @Test
    public void testCon_01_AddInteraction() throws Exception {
        Article article = wac.getBean(ArticleDAO.class)
                .findByNameAndAuthorLoginName("testAdding_doNotUseIt", "ADMIN").get(0);
        mockMvc.perform(
                get("/article/interaction/articleId/{articleId}/mode/{mode}", article.getId(), 1))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 分类查询互动，[GET,/interaction/articles/mode/{mode}]
     */
    @Test
    public void testCon_02_QueryInteraction() throws Exception {
        mockMvc.perform(
                get("/article/interaction/articles/mode/{mode}",1))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 修改文章，[PUT,/article/article]
     */
    @Test
    public void testCon_03_PutArticle() throws Exception {
        Map<String, Object> map = new HashMap<>();
        Article article = wac.getBean(ArticleDAO.class)
                .findByNameAndAuthorLoginName("testAdding_doNotUseIt", "ADMIN").get(0);
        map.put("id", article.getId());
        map.put("name", article.getName() + "_updated");
        map.put("author", article.getAuthor());
        mockMvc.perform(put("/article/article")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 模糊查询文章，[GET,/article/articles/name/{name}]
     */
    @Test
    public void testCon_04_FuzzyFindArticlesByName() throws Exception {
        mockMvc.perform(get("/article/articles/name/{name}", "*testAdding_doNotUseIt*"))
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 删除文章，[DELETE,/article/article]
     */
    @Test
    public void testCon_05_DeleteArticles() throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<Article> articles = wac.getBean(ArticleDAO.class)
                .findByNameAndAuthorLoginName("*testAdding_doNotUseIt*", "ADMIN");
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
    public void testCon_06_GetAllArticles() throws Exception {
        mockMvc.perform(get("/article/articles"))
                .andExpect(status().isOk())
                .andReturn();
    }

}
