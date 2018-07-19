package com.example.demo.common.config;

/**
 * @author : liuqitian
 * @date : 2018/7/18 09:52
 * @version : V1.2
 * 参数校验相关的常量
 */
public class ValidationStaticValues {

    /**
     * 开始标记，截取异常的的信息字符串时使用。拼接在参数校验错误信息之前
     *  eg. @Size(min = 18, max = 18, message = ValidationStaticValues.START_FLAG + ValidationStaticValues.VALID_ID_ES)
     */
    public static final String START_FLAG = "start_here";

    public static final String VALID_ID_ES = "ID应为由阿拉伯数字拼接而成的18位字符串";

    public static final String REGULAR_ID_ES = "[0-9]{18}";

    public static final String VALID_ID_MYSQL = "ID应为最大9位的阿拉伯数字";

    public static final String REGULAR_ID_MYSQL = "[0-9]{9}";

    public static final String VALID_LOGINNAME = "用户登录名只支持使用大小写英文字母，阿拉伯数字，下划线和减号组成，长度应在4-256位";

    public static final String REGULAR_LOGINNAME = "^[a-zA-Z0-9_-]{4,256}$";

    public static final String VALID_NAME = "名称/标题不能超过256字符，不能包括转义字符，不可视字符，英文单双引号和以下几个字符 $ % & = ? ,";

    public static final String REGULAR_NAME = "[^%&'\",;=?$\\x22]{1,256}";

    /**
     * 参数校验错误信息，通用文本框字段，需要在前面拼接字段名称和长度限制，建议格式为 XXX不能超过yyy XXX为字段名，yyy为长度
     */
    public static final String VALID_CONTENT = "字符，不能包括转义字符，不可视字符，英文单双引号和以下几个字符 $ % & = ? ,";

    /**
     * 参数校验正则表达式，通用文本框字段，需要在后面拼接长度限制，格式为{x,y}，x为自然数，y为大于等于x的自然数
     */
    public static final String REGULAR_CONTENT = "[^%&'\",;=?$\\x22]";

    public static final String VALID_PASSWORD = "密码请设定在6-20位之间，只能由大小写英文字母和阿拉伯数字组成";

    public static final String REGULAR_PASSWORD = "[a-zA-Z0-9]{6,20}";

    public static final String VALID_INTEGER_ARRAY = "这不是一个int数组，或者有至少一个元素长度超过了9位";

    public static final String VALID_METHOD_TYPE = "这不是一个http请求类型";

    /**
     * 参数校验错误信息，rest规范的url。
     * 规则为{ 截取最多前500字符，只能包含大小写英文字母，阿拉伯数字，和/{}这三种符号，
     *          且需要符合/xxx/{yyy}/zzz风格，xxx为1到无穷个，yyy和zzz均为0到无穷个 }
     */
    public static final String VALID_REST_URL = "这不是一个符合restful规范的url，或者长度超过500";

    /**
     * 参数校验正则表达式，rest规范的url
     */
    public static final String REGULAR_REST_URL
            = "^(/[A-Za-z0-9]+)+((/\\{[A-Za-z0-9]+\\})*(/[A-Za-z0-9]+)*)*{1,500}";



}
