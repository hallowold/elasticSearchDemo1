package com.example.demo.common.util;

import com.example.demo.common.config.StaticValues;
import com.example.demo.entity.UserInteractionArticle;
import com.example.demo.response.BucketResponse;
import com.example.demo.response.ResponseData;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * response工具类
 * @author liuqitian	
 * @date 2018年6月12日 
 *
 */
public class ResponseUtil {

	/**
	 * 根据给定的参数生成responseData对象，通用方法
	 * @param state		状态
	 * @param message	反馈信息
	 * @param data		数据对象
	 * @param code		状态码，如200，404，500等
	 * @return
	 */
	public static ResponseData createResponseData(boolean state, String message, Object data, int code) {
		ResponseData res = new ResponseData();
	    res.setState(state);
	    if(StaticValues.SEARCH.equals(message) && data == null) {
	    	res.setMessage("查询完成，未找到指定数据");
	    	code = 200;
	    } else if (StaticValues.SEARCH.equals(message) && data != null) {
	    	res.setMessage("查询完成");
	    	code = 200;
	    } else if (StaticValues.DEPENDENCE.equals(message)) {
	    	res.setMessage("指定的数据被其他数据所依赖，无法执行此操作，请检查");
	    	code = 500;
	    } else if (StaticValues.AUTHOR.equals(message)) {
	    	res.setMessage("您的操作中至少有一项操作越权，只有作者本人可以执行该操作，请检查");
	    	code = 500;
	    } else {
			res.setMessage(message);
	    }
	    res.setData(data);
		res.setCode(code);
		return res;
	}
	
	/**
	 * 根据给定的参数生成responseData对象，校验唯一性时使用
	 * @param state		状态
	 * @param message	填充信息，此处应填入校验字段，如登录名，用户姓名，文章标题等
	 * @return
	 */
	public static ResponseData createResponseDataCheckIfExsit(boolean state, String message) {
		ResponseData res = new ResponseData();
		if(state) {
    		res = ResponseUtil.createResponseData(state, "新增成功", null, 200);
    	} else {
    		res = ResponseUtil.createResponseData(state, "该" + message + "已被占用，请修改", null, 500);
    	}
		return res;
	}
	
    /**
     * 使用bucket进行查询时，因为格式问题json无法直接进行解析，需要做一次中转处理。
     *  key名对应的为分组标记，docCount对应的为组内信息个数，
     *      剩余字段在其他value中使用key-value形式进行抓取，key为writeableName对应value，value为value对应的value
     * @Version 1.0
     * @date 20180620
     * @param account   操作类返回结果集
     * @param key       本次操作使用的分组字段标记名(即bucket名)
     * @return  BucketResponse  bucket对象封装类
     */
	public static BucketResponse createBucketResponse(AggregatedPage<UserInteractionArticle> account, String key) {
		BucketResponse br = new BucketResponse();

		Terms terms = account.getAggregations().get(key);
		List<? extends Terms.Bucket> buckets = terms.getBuckets();
		for(Terms.Bucket bt : buckets)
		{
			Map<String, Object> tempMap = new HashMap<>();
			Map<String, Aggregation> map =  bt.getAggregations().getAsMap();

			tempMap.putAll(map);
			if(bt.isFragment())
			tempMap.put("docCount", bt.getDocCount());
            tempMap.put(key, bt.getKey());
			br.getAggs().add(tempMap);
		}

		return br;
	}
}
