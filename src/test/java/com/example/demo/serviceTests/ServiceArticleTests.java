package com.example.demo.serviceTests;

import com.example.demo.entity.Article;
import com.example.demo.exception.Demo1Exception;
import com.example.demo.security.config.LoginSuccessHandler;
import com.example.demo.security.entity.SysGroup;
import com.example.demo.service.ArticleService;
import com.example.demo.service.SysGroupService;
import com.example.demo.service.impl.ArticleServiceImpl;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
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
public class ServiceArticleTests {

    @Autowired
    private ArticleServiceImpl articleService;

    @Test
    public void testSer_00_AddArticle() {
        Article entity = new Article();
        entity.setName("testAdding_doNotUseIt");
        entity.setCreateDate(new Date());
        entity.setAuthor(LoginSuccessHandler.getCurrentUser());

        articleService.addArticle(entity);
    }

    @Test
    public void testSer_01_FuzzyFindByName() {
        List<Article> resultList = articleService.fuzzyFindByName("testAdding_doNotUseIt");
        resultList.forEach(result -> System.out.println(result));
    }

    @Test
    public void testSer_02_updateArticle() {
        try {
            Article entity = articleService.fuzzyFindByName("testAdding_doNotUseIt").get(0);
            entity.setName(entity.getName() + "_changed");
            articleService.updateArticle(entity);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testSer_03_interaction() {
        articleService.interaction(articleService.fuzzyFindByName("testAdding_doNotUseIt").get(0).getId(),1L);
    }

    @Test
    public void testSer_04_findByUserId() {
        articleService.findByUserId(LoginSuccessHandler.getCurrentUser().getId())
                .forEach(result-> System.out.println(result));
    }

    @Test
    public void testSer_05_findByArticleId() {
        articleService.findByArticleId(
                articleService.fuzzyFindByName("testAdding_doNotUseIt").get(0).getId())
                .forEach(result -> System.out.println(result));
    }

    @Test
    public void testSer_06_findByMode() {
        articleService.findByMode(1L).forEach(System.out::println);
    }

    @Test
    public void testSer_07_isAuthor() {
        articleService.isAuthor(articleService.fuzzyFindByName("testAdding_doNotUseIt").get(0).getId());
    }

    @Test
    public void testSer_08_FindAll() {
        Iterable<Article> resultList = articleService.findAllArticle();
        resultList.forEach(result -> System.out.println(result));
    }

    @Test
    public void testSer_09_deleteArticle() {
        try{
            List<String> idsList = new ArrayList<>();
            articleService.fuzzyFindByName("testAdding_doNotUseIt").forEach(article -> idsList.add(article.getId()));
            System.out.println(articleService.deleteArticle(idsList.toArray(new String[]{})));
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

}
