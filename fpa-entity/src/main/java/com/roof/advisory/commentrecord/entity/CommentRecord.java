package com.roof.advisory.commentrecord.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： z_comment_record <br/>
 *         描述：评价记录表 <br/>
 */
@ApiModel(value = "z_comment_record", description = "评价记录表")
public class CommentRecord implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "所属系统")
	protected Long appId;// 所属系统
	@ApiModelProperty(value = "评价项id")
	protected Long itemId;// 评价项id
	@ApiModelProperty(value = "咨询师id")
	protected Long consultantId;// 咨询师id
	@ApiModelProperty(value = "评价结果")
	protected String evalResult;// 评价结果
	@ApiModelProperty(value = "订单编号")
	protected String orderNumber;// 订单编号
	@ApiModelProperty(value = "评价人")
	protected Long evaluator;// 评价人
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "评价时间")
	protected Date evalTime;// 评价时间
	@ApiModelProperty(value = "状态")
	protected Integer status;// 状态
	@ApiModelProperty(value = "逻辑删除状态")
	protected Integer state;// 逻辑删除状态
	@ApiModelProperty(value = "主键")
	protected Long id;// 主键

	public CommentRecord() {
		super();
	}

	public CommentRecord(Long id) {
		super();
		this.id = id;
	}
	
	
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
	public Long getConsultantId() {
		return consultantId;
	}
	public void setConsultantId(Long consultantId) {
		this.consultantId = consultantId;
	}
	
	public String getEvalResult() {
		return evalResult;
	}
	public void setEvalResult(String evalResult) {
		this.evalResult = evalResult;
	}
	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	public Long getEvaluator() {
		return evaluator;
	}
	public void setEvaluator(Long evaluator) {
		this.evaluator = evaluator;
	}
	
	public Date getEvalTime() {
		return evalTime;
	}
	public void setEvalTime(Date evalTime) {
		this.evalTime = evalTime;
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
	@Id// 主键
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
