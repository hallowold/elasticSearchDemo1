package com.example.demo.common.baseDAO;

/**
 * 自定义查询条件的输入格式
 * @author liuqitian
 * @date 2018年8月1日
 */
public enum QueryOperatorEnum {

	LIKE("LIKE", 1, "LIKE"),
	EQUAL("EQUAL", 2, "="),
	NOT_EQUAL("NOT EQUAL", 3, "<>"),
	LESS("LESS", 4, "<"),
	BIGGER("BIGGER", 5, ">"),
	LESS_EQUAL("LESS EQUAL", 6, "<="),
	BIGGER_EQUAL("BIGGER EQUAL", 7, ">="),
	IN("IN", 8,"IN"),
	NOT_IN("NOT IN", 9, "NOT IN"),
	ORDER_BY("ORDER BY", 10, "ORDER BY");

	public String name;
	public int value;
	public String condition;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	private QueryOperatorEnum(String name, int value, String condition) {
		this.name = name;
		this.value = value;
        this.condition = condition;      
    }

	public static QueryOperatorEnum getEnum(String name) {
		try {
			return QueryOperatorEnum.valueOf(name);
		} catch (Exception ex) {
			return null;
		}	
	}
}
