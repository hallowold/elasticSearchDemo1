package com.example.demo.common.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: liuqitian
 * @Date: 2018/7/5 11:15
 * @Version: V1.0
 * @Description:
 */
public class StringUtil {

    /**
     * 对传入的字符串进行校验，若符合定义的规则，则对齐进行转义
     * @param oldStr    需要校验的字符
     * @return  需要转义的字符返回转义结果，否则返回原字符
     */
    public static String changeSpecialCharacter(String oldStr) {
        List<String> specialCharacters = new ArrayList<>();
        boolean flag = false;
        specialCharacters.add("_");
        specialCharacters.add("%");
        specialCharacters.add("'");
        for (String str : specialCharacters) {
            if(str.equals(oldStr)) {
                flag = true;
                break;
            }
        }
        if(flag) {
            oldStr = "\\" + oldStr;
        }
        return oldStr;
    }

    /**
     * 加密，用户密码使用
     * @param str 待加密字符串
     * @return String 加密字符串
     */
    public static String encode(String str) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(str);
    }
}
