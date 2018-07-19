package com.example.demo.response;

/**
 * 通用response对象
 * 
 * @author liuqitian
 * @date 2018年6月12日
 *
 */
public class ResponseData {

	private boolean state;

	private String message;

	private Object data;

	private int code;

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public ResponseData() {
	}

	public ResponseData(boolean state) {
		this.state = state;
	}

	public ResponseData(boolean state, Object data) {
		this.data = data;
		this.state = state;
	}

	public ResponseData(boolean state, String message) {
		this.state = state;
		this.message = message;
	}

	public ResponseData(boolean state, Object data, String message) {
		this.data = data;
		this.state = state;
		this.message = message;
	}

	public ResponseData(boolean state, Object data, String message, int code) {
		this.data = data;
		this.state = state;
		this.message = message;
		this.code = code;
	}
}
