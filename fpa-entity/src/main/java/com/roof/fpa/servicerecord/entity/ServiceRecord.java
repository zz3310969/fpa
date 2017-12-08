package com.roof.fpa.servicerecord.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： c_service_record <br/>
 *         描述：服务记录 <br/>
 */
@ApiModel(value = "c_service_record", description = "服务记录")
public class ServiceRecord implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "主键")
	protected Long id;// 主键
	@ApiModelProperty(value = "咨询师ID")
	protected Long counselorId;// 咨询师ID
	@ApiModelProperty(value = "客户ID")
	protected Long customerId;// 客户ID
	@ApiModelProperty(value = "咨询主题ID")
	protected Long themeId;// 咨询主题ID
	@ApiModelProperty(value = "服务时长")
	protected Long duration;// 服务时长
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "服务开始时间")
	protected Date startTime;// 服务开始时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "服务结束时间")
	protected Date endTime;// 服务结束时间
	@ApiModelProperty(value = "服务评价")
	protected String evaluation;// 服务评价
	@ApiModelProperty(value = "星级")
	protected Integer evaluationRank;// 星级
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "评价时间")
	protected Date evaluationTime;// 评价时间

	public ServiceRecord() {
		super();
	}

	public ServiceRecord(Long id) {
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
	
	public Long getCounselorId() {
		return counselorId;
	}
	public void setCounselorId(Long counselorId) {
		this.counselorId = counselorId;
	}
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	public Long getThemeId() {
		return themeId;
	}
	public void setThemeId(Long themeId) {
		this.themeId = themeId;
	}
	
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public String getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}
	
	public Integer getEvaluationRank() {
		return evaluationRank;
	}
	public void setEvaluationRank(Integer evaluationRank) {
		this.evaluationRank = evaluationRank;
	}
	
	public Date getEvaluationTime() {
		return evaluationTime;
	}
	public void setEvaluationTime(Date evaluationTime) {
		this.evaluationTime = evaluationTime;
	}
}
