package com.roof.advisory.advisoryproduct.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： z_advisory_product <br/>
 *         描述：服务产品 <br/>
 */
@ApiModel(value = "z_advisory_product", description = "服务产品")
public class AdvisoryProduct implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "主键")
	protected Long id;// 主键
	@ApiModelProperty(value = "产品名称")
	protected String name;// 产品名称
	@ApiModelProperty(value = "产品编号")
	protected String code;// 产品编号
	@ApiModelProperty(value = "所属应用")
	protected Long appId;// 所属应用
	@ApiModelProperty(value = "所属咨询师")
	protected Long consId;// 所属咨询师
	@ApiModelProperty(value = "备注")
	protected String remark;// 备注
	@ApiModelProperty(value = "服务模式")
	protected Long modesId;// 服务模式
	@ApiModelProperty(value = "状态")
	protected Integer status;// 状态
	@ApiModelProperty(value = "逻辑删除状态")
	protected Integer state;// 逻辑删除状态
	@ApiModelProperty(value = "咨询定价id")
	protected Long advisId;// 咨询定价id
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "开始时间")
	protected Date validityStartTime;// 开始时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "结束时间")
	protected Date validityEndTime;// 结束时间

	public AdvisoryProduct() {
		super();
	}

	public AdvisoryProduct(Long id) {
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
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	
	public Long getConsId() {
		return consId;
	}
	public void setConsId(Long consId) {
		this.consId = consId;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Long getModesId() {
		return modesId;
	}
	public void setModesId(Long modesId) {
		this.modesId = modesId;
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
	
	public Long getAdvisId() {
		return advisId;
	}
	public void setAdvisId(Long advisId) {
		this.advisId = advisId;
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
