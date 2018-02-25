package com.roof.advisory.advisorytheme.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： z_advisory_theme <br/>
 *         描述：服务主题 <br/>
 */
@ApiModel(value = "z_advisory_theme", description = "服务主题")
public class AdvisoryTheme implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "id")
	protected Long id;// id
	@ApiModelProperty(value = "所属系统")
	protected Long appId;// 所属系统
	@ApiModelProperty(value = "主题名称")
	protected String name;// 主题名称
	@ApiModelProperty(value = "主题编码")
	protected String code;// 主题编码
	@ApiModelProperty(value = "备注")
	protected String remark;// 备注
	@ApiModelProperty(value = "状态")
	protected Integer status;// 状态
	@ApiModelProperty(value = "逻辑删除")
	protected Integer state;// 逻辑删除

	public AdvisoryTheme() {
		super();
	}

	public AdvisoryTheme(Long id) {
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
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
