package com.example.demo.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author : liuqitian
 * @date : 2018/8/1 12:22
 * @version : V1.2
 * 操作日志
 */
@Entity
@Table(name = "opr_log")
public class OperatorLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Integer id;

    //操作用户的展示名，showName
    @Column(name = "user_name")
    private String userName;

    //模块名，如文章，用户，机构等
    @Column(name = "module_name")
    private String moduleName;

    //操作类型，ADD,UPDATE,DELETE,QUERY
    @Column(name = "opr_type")
    private String oprType;

    //具体操作
    @Column(length = 500)
    private String detail;

    @Column(name = "create_date")
    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getOprType() {
        return oprType;
    }

    public void setOprType(String oprType) {
        this.oprType = oprType;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "OperatorLog{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", oprType='" + oprType + '\'' +
                ", detail='" + detail + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
