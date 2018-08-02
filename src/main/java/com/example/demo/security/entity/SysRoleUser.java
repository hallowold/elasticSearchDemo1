package com.example.demo.security.entity;

import javax.persistence.*;

/**
 * @author : liuqitian
 * @date : 2018/7/6 10:52
 * @version : V1.1
 * 系统用户和角色的关系表
 */
@Entity
@Table(name="s_role_user")
public class SysRoleUser {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column (name="id",length = 9)
    private int id;

    /**
     * 角色实体
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roleId", referencedColumnName = "id")
    private SysRole sRole;

    /**
     *  角色id(外键)
     */
    @Column(length = 9, insertable=false, updatable=false)
    private Integer roleId;

    /**
     * 用户实体
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private SysUser sUser;

    /**
     *  用户id(外键)
     */
    @Column(length = 9, insertable=false, updatable=false)
    private Integer userId;

    public SysRoleUser() {}

    public SysRoleUser(SysRole sRole, Integer roleId, SysUser sUser, Integer userId) {
        this.sRole = sRole;
        this.roleId = roleId;
        this.sUser = sUser;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SysRole getsRole() {
        return sRole;
    }

    public void setsRole(SysRole sRole) {
        this.sRole = sRole;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public SysUser getsUser() {
        return sUser;
    }

    public void setsUser(SysUser sUser) {
        this.sUser = sUser;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "SysRoleUser{" +
                "id=" + id +
                ", sRole=" + sRole +
                ", roleId=" + roleId +
                ", sUser=" + sUser +
                ", userId=" + userId +
                '}';
    }
}
