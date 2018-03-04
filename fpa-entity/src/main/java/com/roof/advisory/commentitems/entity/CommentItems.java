package com.roof.advisory.commentitems.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： z_comment_items <br/>
 *         描述：评价项表 <br/>
 */
@ApiModel(value = "z_comment_items", description = "评价项表")
public class CommentItems implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "评价项id")
	protected Long id;// 评价项id
	@ApiModelProperty(value = "评价项")
	protected String name;// 评价项
	@ApiModelProperty(value = "评价方式")
	protected String evalMode;// 评价方式
	@ApiModelProperty(value = "总分")
	protected Double totalScore;// 总分
	@ApiModelProperty(value = "展现方式")
	protected String prosetaionType;// 展现方式
	@ApiModelProperty(value = "状态")
	protected Integer status;// 状态
	@ApiModelProperty(value = "逻辑删除状态")
	protected Integer state;// 逻辑删除状态
	@ApiModelProperty(value = "评价模版id")
	protected Long commentTemplate;// 评价模版id

	public CommentItems() {
		super();
	}

	public CommentItems(Long id) {
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
	
	public String getEvalMode() {
		return evalMode;
	}
	public void setEvalMode(String evalMode) {
		this.evalMode = evalMode;
	}
	
	public Double getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}
	
	public String getProsetaionType() {
		return prosetaionType;
	}
	public void setProsetaionType(String prosetaionType) {
		this.prosetaionType = prosetaionType;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	public Long getCommentTemplate() {
		return commentTemplate;
	}
	public void setCommentTemplate(Long commentTemplate) {
		this.commentTemplate = commentTemplate;
	}
}
