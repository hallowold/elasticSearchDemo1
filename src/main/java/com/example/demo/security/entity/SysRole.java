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
    @Column (name="id",length = 9)
    private int id;

    /**
     * @Auther: liuqitian
     * @Date: 2018/7/4 15:38
     * @Version: V1.0
     * @Description: 角色名称
     */
    @Column(name="name",length = 256)
    private String name;

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

}
