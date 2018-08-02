package com.example.demo.common.baseDAO;

import java.io.Serializable;

/**
 * 自定义查询条件的封装类
 * @author liuqitian
 * @date 2018年8月1日
 */
public class DaoCriteria implements Serializable {

	private static final long serialVersionUID = 812759366580443779L;

	private String propertyName;

	//操作符（等于、小于等）
    private String operator;

    private Object value;
    
    public String getOperator()
    {
        return operator;
    }

    public void setOperator(String operator)
    {
        this.operator = operator;
    }

    public String getPropertyName()
    {
        return propertyName;
    }

    public void setPropertyName(String propertyName)
    {
        this.propertyName = propertyName;
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object value)
    {
        this.value = value;
    }

    public DaoCriteria(){}

    public DaoCriteria(String propertyName, String operator, Object value){
        this.propertyName = propertyName;
        this.operator = operator;
        this.value = value;
    }

}

