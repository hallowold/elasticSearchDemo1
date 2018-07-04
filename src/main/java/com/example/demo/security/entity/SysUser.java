package com.example.demo.security.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author  liuqitian
 * @Date: 2018/7/2 09:33
 * @Version: V1.0
 * @Description: 系统用户
 */
@Entity
@Table(name = "s_user")//code11
public class SysUser implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    /**
     * @Auther: liuqitian
     * @Date: 2018/7/4 15:41
     * @Version: V1.0
     * @Description: 用户登录名
     */
    @Column(name = "login_name", length = 120)
    private String loginName;

    /**
     * @Auther: liuqitian
     * @Date: 2018/7/4 16:13
     * @Version: V1.0
     * @Description: 用户展示名
     */
    @Column(name = "show_name")
    private String showName;

    /**
     * @Auther: liuqitian
     * @Date: 2018/7/4 15:42
     * @Version: V1.0
     * @Description: 用户邮箱
     */
    @Column(name = "email", length = 50)
    private String email;

    /**
     * @Auther: liuqitian
     * @Date: 2018/7/4 15:42
     * @Version: V1.0
     * @Description: 用户密码
     */
    @Column(name = "password", length = 120)
    private String password;

    /**
     * @Auther: liuqitian
     * @Date: 2018/7/4 15:42
     * @Version: V1.0
     * @Description: 时间
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "create_time", length = 10)
    private Date createTime;

    /**
     * @Auther: liuqitian
     * @Date: 2018/7/4 15:50
     * @Version: V1.0
     * @Description: 所对应的角色集合
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "SUser")
    private Set<SysRole> SysRoles = new HashSet<SysRole>(0);

    public SysUser() {}

    public SysUser(String loginName, String showName, String email, String password, Date createTime, Set<SysRole> SysRoles) {
        this.loginName = loginName;
        this.showName = showName;
        this.email = email;
        this.password = password;
        this.createTime = createTime;
        this.SysRoles = SysRoles;
    }

    public Integer getId() {
        return this.id;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getcreateTime() {
        return this.createTime;
    }

    public void setcreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "SUser")
    public Set<SysRole> getSysRoles() {
        return this.SysRoles;
    }

    public void setSysRoles(Set<SysRole> SysRoles) {
        this.SysRoles = SysRoles;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", showName='" + showName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", SysRoles=" + SysRoles +
                '}';
    }
}

