package com.example.demo.security.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author  liuqitian
 * @date : 2018/7/2 09:33
 * @version : V1.0
 * 系统用户
 */
@Entity
@Table(name = "s_user")
public class SysUser implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    /**
     * 用户登录名
     */
    @Column(name = "login_name", length = 256)
    private String loginName;

    /**
     * 用户展示名
     */
    @Column(name = "show_name", length = 256)
    private String showName;

    @Column(name = "password", length = 500)
    private String password;

    @Column(name = "email", length = 256)
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 该用户所拥有所有角色的集合，这个属性不入库
     */
    @Transient
    private Set<SysRole> SysRoles = new HashSet<SysRole>(0);

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Set<SysRole> getSysRoles() {
        return SysRoles;
    }

    public void setSysRoles(Set<SysRole> sysRoles) {
        SysRoles = sysRoles;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", showName='" + showName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", SysRoles=" + SysRoles +
                '}';
    }
}

