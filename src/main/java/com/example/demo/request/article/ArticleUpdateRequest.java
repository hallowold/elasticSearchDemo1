package com.example.demo.request.article;

import com.example.demo.common.config.ValidationStaticValues;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 修改文章时使用的request对象
 * @author liuqitian	
 * @date 2018年6月12日 
 *
 */
public class ArticleUpdateRequest {

	@NotNull
	@Pattern(regexp = ValidationStaticValues.REGULAR_ID_ES,
			message = ValidationStaticValues.START_FLAG + ValidationStaticValues.VALID_ID_ES)
	@Size(min = 18, max = 18, message = ValidationStaticValues.START_FLAG + ValidationStaticValues.VALID_ID_ES)
	private String id;

	@NotNull
	@Pattern(regexp = ValidationStaticValues.REGULAR_NAME,
			message = ValidationStaticValues.START_FLAG + ValidationStaticValues.VALID_NAME)
	@Size(max = 256, message = ValidationStaticValues.START_FLAG + ValidationStaticValues.VALID_NAME)
	private String name;

	private Date createDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
