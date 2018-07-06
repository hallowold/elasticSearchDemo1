package com.example.demo.security.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @Auther: liuqitian
 * @Date: 2018/7/2 09:42
 * @Version: V1.0
 * @Description: 系统角色权限
 */
@Entity
@Table(name="s_role_right")
public class SysRoleRight {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column (name="id",length=10)
    private int id;

    /**
     * @Auther: liuqitian
     * @Date: 2018/7/4 15:39
     * @Version: V1.0
     * @Description: 角色实体
     */
    @ManyToOne(fetch = FetchType.EAGER)//设置在“一方”pojo的外键字段上
    @JoinColumn(name = "roleId", referencedColumnName = "id")//设置对应数据表的列名和引用的数据表的列名
    private SysRole sRole;

    /**
     * @Auther: liuqitian
     * @Date: 2018/7/4 15:40
     * @Version: V1.0
     * @Description: 角色id(外键)
     */
    @Column(insertable=false, updatable=false)
    private Integer roleId;

    /**
     * @Auther: liuqitian
     * @Date: 2018/7/4 15:40
     * @Version: V1.0
     * @Description: 权限实体
     */
    @ManyToOne(fetch = FetchType.EAGER)//设置在“一方”pojo的外键字段上
    @JoinColumn(name = "rightId", referencedColumnName = "id")//设置对应数据表的列名和引用的数据表的列名
    private SysRight sRight;

    /**
     * @Auther: liuqitian
     * @Date: 2018/7/4 15:40
     * @Version: V1.0
     * @Description: 权限id
     */
    @Column(insertable=false, updatable=false)
    private Integer rightId;

    /**
     * @Auther: liuqitian
     * @Date: 2018/7/4 15:40
     * @Version: V1.0
     * @Description: 更新时间
     */
    @Column(name="updateTime")
    private Date updateTime;

    public SysRoleRight() {}

    public SysRoleRight(Integer roleId, SysRole sysRole, Integer rightId, SysRight sysRight, Date updateTime ) {
        this.roleId = roleId;
        this.sRole = sysRole;
        this.rightId = rightId;
        this.sRight = sysRight;
        this.updateTime = updateTime;
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

    public SysRight getsRight() {
        return sRight;
    }

    public void setsRight(SysRight sRight) {
        this.sRight = sRight;
    }

    public Integer getRightId() {
        return rightId;
    }

    public void setRightId(Integer rightId) {
        this.rightId = rightId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
