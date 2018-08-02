package com.example.demo.security.entity;

import javax.persistence.*;

/**
 * @author : liuqitian
 * @date : 2018/7/6 10:52
 * @version : V1.1
 * 系统用户和机构的关系表
 */
@Entity
@Table(name="s_role_group")
public class SysRoleGroup {

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
     * 机构实体
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "groupId", referencedColumnName = "id")
    private SysGroup sysGroup;

    /**
     *  机构id(外键)
     */
    @Column(length = 9, insertable=false, updatable=false)
    private Integer groupId;

    public SysRoleGroup() {}

    public SysRoleGroup(SysRole sRole, Integer roleId, SysGroup sysGroup, Integer groupId) {
        this.sRole = sRole;
        this.roleId = roleId;
        this.sysGroup = sysGroup;
        this.groupId = groupId;
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

    public SysGroup getSysGroup() {
        return sysGroup;
    }

    public void setSysGroup(SysGroup sysGroup) {
        this.sysGroup = sysGroup;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "SysRoleGroup{" +
                "id=" + id +
                ", sRole=" + sRole +
                ", roleId=" + roleId +
                ", sysGroup=" + sysGroup +
                ", groupId=" + groupId +
                '}';
    }
}
