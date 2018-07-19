package com.example.demo.security.entity;

import javax.persistence.*;

/**
 * @author : liuqitian
 * @date : 2018/7/16 12:07
 * @version : V1.0
 */
@Entity
@Table(name = "s_group")
public class SysGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 9, unique = true, nullable = false)
    private Integer id;

    /**
     * 机构名
     */
    @Column(name = "name", length = 256)
    private String name;

    /**
     * 父机构实体
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pId", referencedColumnName = "id")
    private SysGroup pSysGroup;

    /**
     * 父机构id(外键)
     */
    @Column(length = 9, insertable=false, updatable=false)
    private Integer pId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SysGroup getpSysGroup() {
        return pSysGroup;
    }

    public void setpSysGroup(SysGroup pSysGroup) {
        this.pSysGroup = pSysGroup;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    @Override
    public String toString() {
        return "SysGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pSysGroup=" + pSysGroup +
                ", pId=" + pId +
                '}';
    }
}
