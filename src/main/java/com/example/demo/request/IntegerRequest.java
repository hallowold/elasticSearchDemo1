package com.example.demo.request;

/**
 * @author : liuqitian
 * @date : 2018/7/11 15:43
 * @version : V1.2
 * 以json格式放置Integer格式和Integer[]格式的信息
 */
public class IntegerRequest {

    private Integer id;

    private Integer[] ids;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }
}
