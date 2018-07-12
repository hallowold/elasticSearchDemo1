package com.example.demo.request;

/**
 * @author : liuqitian
 * @date : 2018/7/11 15:43
 * @version : V1.2
 * @deprecated : 以json格式放置mysql ids数组参数
 */
public class MysqlIdsRequest {

    private Integer[] ids;

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }
}
