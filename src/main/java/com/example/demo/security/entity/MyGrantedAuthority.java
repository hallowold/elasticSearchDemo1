package com.example.demo.security.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * @Auther: liuqitian
 * @Date: 2018/7/4 09:58
 * @Version: V1.0
 * @Description: 实现GrantedAuthority类，在其中增加url和methodType两个属性
 *                      此类已废弃，作为示例放置于此
 */
public class MyGrantedAuthority implements GrantedAuthority {

    private String url;
    private String methodType;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public MyGrantedAuthority(String url, String methodType) {
        this.url = url;
        this.methodType = methodType;
    }

    @Override
    public String getAuthority() {
        return this.url + "_" + this.methodType;
    }
}
