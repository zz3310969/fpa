package com.roof.advisory.advisorymodes.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： z_advisory_modes <br/>
 *         描述：咨询模式 <br/>
 */
@ApiModel(value = "z_advisory_modes", description = "咨询模式")
public class AdvisoryModes implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "id")
	protected Long id;// id
	@ApiModelProperty(value = "所属系统")
	protected Long appId;// 所属系统
	@ApiModelProperty(value = "mode_name")
	protected String modeName;// mode_name
	@ApiModelProperty(value = "状态")
	protected Integer status;// 状态
	@ApiModelProperty(value = "逻辑删除")
	protected Integer state;// 逻辑删除

	public AdvisoryModes() {
		super();
	}

	public AdvisoryModes(Long id) {
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
	
	public String getModeName() {
		return modeName;
	}
	public void setModeName(String modeName) {
		this.modeName = modeName;
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
}
