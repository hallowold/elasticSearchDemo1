package com.example.demo.common.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : liuqitian
 * @date : 2018/7/5 11:15
 * @version : V1.0
 */
public class StringUtil {

    /**
     * 对传入的字符串进行校验，若符合定义的规则，则对齐进行转义
     * @param oldStr    需要校验的字符
     * @return  需要转义的字符返回转义结果，否则返回原字符
     */
    public static String changeSpecialCharacter(String oldStr) {
        boolean flag = ifSpecialStrListContainsStr(oldStr);
        if(flag) { oldStr = "\\" + oldStr; }
        return oldStr;
    }

    private static List<String> getSpecialStrList() {
        List<String> specialStrList = new ArrayList<>();
        specialStrList.add("_");
        specialStrList.add("%");
        return specialStrList;
    }

    private static boolean ifSpecialStrListContainsStr(String str) {
        List<String> specialStrList = getSpecialStrList();
        return specialStrList.contains(str);
    }

    public static String encode(String str) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(str);
    }
}
