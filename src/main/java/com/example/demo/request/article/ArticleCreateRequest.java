package com.example.demo.request.article;

import com.example.demo.common.config.ValidationStaticValues;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 创建文章时使用的request对象
 * @author liuqitian	
 * @date 2018年6月12日 
 *
 */
public class ArticleCreateRequest {

	@NotNull()
	@Pattern(regexp = ValidationStaticValues.REGULAR_NAME,
			message = ValidationStaticValues.START_FLAG + ValidationStaticValues.VALID_NAME)
	@Size(max = 256, message = ValidationStaticValues.START_FLAG + ValidationStaticValues.VALID_NAME)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
