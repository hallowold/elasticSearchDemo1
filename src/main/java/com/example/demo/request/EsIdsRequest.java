package com.example.demo.request;

/**
 * @author : liuqitian
 * @date : 2018/7/11 15:43
 * @version : V1.2
 * 以json格式放置es ids数组参数
 */
public class EsIdsRequest {

    private String[] ids;

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }
}
