package com.example.demo.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : liuqitian
 * @Date: 2018/6/21 10:06
 * @Version: V1.0
 * @Description: Date工具类
 */
public class DateUtil {

    /**
     * @Date: 2018/6/21 10:07
     * @Version: V1.0
     * @return: java.lang.String
     * @Description: 格式化获取当前系统时间
     */
    public static String getCurrentDateStr() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }
}
