package com.roof.advisory.commenttemplate.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： z_comment_template <br/>
 *         描述：评价模版表 <br/>
 */
@ApiModel(value = "z_comment_template", description = "评价模版表")
public class CommentTemplate implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "模版id")
	protected Long id;// 模版id
	@ApiModelProperty(value = "模版名称")
	protected String name;// 模版名称
	@ApiModelProperty(value = "所属系统")
	protected Long appId;// 所属系统
	@ApiModelProperty(value = "逻辑删除使用")
	protected Integer state;// 逻辑删除使用

	public CommentTemplate() {
		super();
	}

	public CommentTemplate(Long id) {
		super();
		this.id = id;
	}
	
	@Id// 主键
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}
