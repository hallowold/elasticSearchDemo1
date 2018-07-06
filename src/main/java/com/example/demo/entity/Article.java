package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import com.example.demo.security.entity.SysUser;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 文章实体类
 * @author liuqitian
 * @version V1.1  因引入spring security，代码重构
 * @date 2018年7月4日
 */
@Document(indexName = "article")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    /**
     * 文章名
     */
    private String name;

    /**
     * 作者
     */
    private SysUser author;

    /**
     * 创建时间
     */
    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SysUser getAuthor() {
        return author;
    }

    public void setAuthor(SysUser author) {
        this.author = author;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
