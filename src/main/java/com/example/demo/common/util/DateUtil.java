package com.example.demo.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : liuqitian
 * @date : 2018/6/21 10:06
 * @version : V1.0
 *  Date工具类
 */
public class DateUtil {

    public static String getCurrentDateStr() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }
}
