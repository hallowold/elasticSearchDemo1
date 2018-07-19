package com.example.demo.security.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @Auther: liuqitian
 * @Date: 2018/7/2 09:42
 * @Version: V1.0
 * @Description: 系统权限
 */
@Entity
@Table(name="s_right")
public class SysRight {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column (name="id",length = 9)
    private int id;

    /**
     * @Auther: liuqitian
     * @Date: 2018/7/4 9:50
     * @Version: V1.0
     * @Description: 权限名称
     */
    @Column(name="name",length=256)
    private String name;

    /**
     * @Auther: liuqitian
     * @Date: 2018/7/4 9:50
     * @Version: V1.0
     * @Description: 权限url
     */
    @Column(name="rightUrl",length = 256)
    private String rightUrl;

    /**
     * @Auther: liuqitian
     * @Date: 2018/7/4 9:50
     * @Version: V1.0
     * @Description: 备注信息
     */
    @Column(name="remark",length = 500)
    private String remark;

    /**
     * @Auther: liuqitian
     * @Date: 2018/7/4 9:51
     * @Version: V1.0
     * @Description: 所对应的方法名
     */
    @Column(name="methodName",length = 256)
    private String methodName;

    /**
     * @Auther: liuqitian
     * @Date: 2018/7/4 9:51
     * @Version: V1.0
     * @Description: 所对应的包路径
     */
    @Column(name="methodPath",length = 256)
    private String methodPath;

    /**
     * @Auther: liuqitian
     * @Date: 2018/7/4 9:52
     * @Version: V1.0
     * @Description: 对应的请求类型
     */
    @Column(name="methodType", length = 256)
    private String methodType;

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

    public String getRightUrl() {
        return rightUrl;
    }

    public void setRightUrl(String rightUrl) {
        this.rightUrl = rightUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodPath() {
        return methodPath;
    }

    public void setMethodPath(String methodPath) {
        this.methodPath = methodPath;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String mothedType) {
        this.methodType = mothedType;
    }

    @Override
    public String toString() {
        return "SysRight{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rightUrl='" + rightUrl + '\'' +
                ", remark='" + remark + '\'' +
                ", methodName='" + methodName + '\'' +
                ", methodPath='" + methodPath + '\'' +
                ", methodType='" + methodType + '\'' +
                '}';
    }
}
