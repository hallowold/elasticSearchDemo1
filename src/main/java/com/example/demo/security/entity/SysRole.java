package com.example.demo.security.entity;

import javax.persistence.*;

/**
 * @Auther: liuqitian
 * @Date: 2018/7/2 09:41
 * @Version: V1.0
 * @Description: 系统角色
 */
@Entity
@Table(name="s_role")
public class SysRole {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column (name="id",length=10)
    private int id;

    /**
     * @Auther: liuqitian
     * @Date: 2018/7/4 15:38
     * @Version: V1.0
     * @Description: 角色名称
     */
    @Column(name="name",length=100)
    private String name;

    /**
     * @Auther: liuqitian
     * @Date: 2018/7/4 15:38
     * @Version: V1.0
     * @Description: 角色对应的用户实体
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId",  referencedColumnName = "id")
    private SysUser SUser;

    /**
     * @Auther: liuqitian
     * @Date: 2018/7/4 15:38
     * @Version: V1.0
     * @Description: 用户id(外键)
     */
    @Column(insertable=false, updatable=false)
    private Integer userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SysUser getSUser() {
        return SUser;
    }

    public void setSUser(SysUser SUser) {
        this.SUser = SUser;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
