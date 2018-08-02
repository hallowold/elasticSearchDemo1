package com.example.demo.security.entity;

import javax.persistence.*;

/**
 * @author : liuqitian
 * @date : 2018/7/2 09:41
 * @version : V1.0
 * 系统角色
 */
@Entity
@Table(name="s_role")
public class SysRole {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column (name="id",length = 9)
    private int id;

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

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
