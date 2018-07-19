package com.example.demo.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 使用bucket进行查询时，因为格式问题json无法直接进行解析，需要做一次中转处理。
 *  注意：不要放入源信息，即实体对象的结果集，会导致json无法解析。暂未找出解决方案
 * @author  liuqitian
 * @version 1.0
 * @date 20180620
 */
public class BucketResponse {

    private List<Map<String, Object>> aggs = new ArrayList<>();

    public List<Map<String, Object>> getAggs() {
        return aggs;
    }

    public void setAggs(List<Map<String, Object>> aggs) {
        this.aggs = aggs;
    }
}
