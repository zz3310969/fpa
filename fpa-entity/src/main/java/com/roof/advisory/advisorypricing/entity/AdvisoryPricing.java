package com.roof.advisory.advisorypricing.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： z_advisory_pricing <br/>
 *         描述：咨询服务定价 <br/>
 */
@ApiModel(value = "z_advisory_pricing", description = "咨询服务定价")
public class AdvisoryPricing implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "id")
	protected Long id;// id
	@ApiModelProperty(value = "所属系统")
	protected Long appId;// 所属系统
	@ApiModelProperty(value = "咨询师")
	protected Long consultantId;// 咨询师
	@ApiModelProperty(value = "咨询模式")
	protected Long moldeId;// 咨询模式
	@ApiModelProperty(value = "定价类型")
	protected String fixType;// 定价类型
	@ApiModelProperty(value = "单位")
	protected String unit;// 单位 分钟
	@ApiModelProperty(value = "原单价")
	protected Integer originalPrice;// 原单价
	@ApiModelProperty(value = "现单价")
	protected Integer currentPrice;// 现单价 多少钱
 	@ApiModelProperty(value = "简介")
	protected String introduction;// 简介
	@ApiModelProperty(value = "状态")
	protected Integer status;// 状态
	@ApiModelProperty(value = "逻辑删除")
	protected Integer state;// 逻辑删除
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "有效期")
	protected Date validityStartTime;// 有效期
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "有效期")
	protected Date validityEndTime;// 有效期

	public AdvisoryPricing() {
		super();
	}

	public AdvisoryPricing(Long id) {
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
	
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	
	public Long getConsultantId() {
		return consultantId;
	}
	public void setConsultantId(Long consultantId) {
		this.consultantId = consultantId;
	}
	
	public Long getMoldeId() {
		return moldeId;
	}
	public void setMoldeId(Long moldeId) {
		this.moldeId = moldeId;
	}
	
	public String getFixType() {
		return fixType;
	}
	public void setFixType(String fixType) {
		this.fixType = fixType;
	}
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public Integer getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(Integer originalPrice) {
		this.originalPrice = originalPrice;
	}
	
	public Integer getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(Integer currentPrice) {
		this.currentPrice = currentPrice;
	}
	
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
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
	
	public Date getValidityStartTime() {
		return validityStartTime;
	}
	public void setValidityStartTime(Date validityStartTime) {
		this.validityStartTime = validityStartTime;
	}
	
	public Date getValidityEndTime() {
		return validityEndTime;
	}
	public void setValidityEndTime(Date validityEndTime) {
		this.validityEndTime = validityEndTime;
	}
}
