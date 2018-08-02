package com.example.demo;

/**
 * @version : V1.2
 * @auther : liuqitian
 * @date : 2018/7/20 17:55
 */
public class TestClone implements Cloneable {

    public Object clone() throws CloneNotSupportedException {
        TestClone proto = (TestClone) super.clone();
        return proto;
    }

}
