package com.roof.advisory.application.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： z_application <br/>
 *         描述：接入系统 <br/>
 */
@ApiModel(value = "z_application", description = "接入系统")
public class Application implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "id")
	protected Long id;// id
	@ApiModelProperty(value = "系统名称")
	protected String name;// 系统名称
	@ApiModelProperty(value = "logo")
	protected String logoImageUrl;// logo
	@ApiModelProperty(value = "系统编码")
	protected String appCode;// 系统编码
	@ApiModelProperty(value = "所属行业")
	protected String industry;// 所属行业
	@ApiModelProperty(value = "联系人")
	protected String contact;// 联系人
	@ApiModelProperty(value = "联系电话")
	protected String contactTel;// 联系电话
	@ApiModelProperty(value = "邮箱")
	protected String email;// 邮箱
	@ApiModelProperty(value = "密钥")
	protected String appSecret;// 密钥
	@ApiModelProperty(value = "状态")
	protected Integer status;// 状态
	@ApiModelProperty(value = "逻辑删除")
	protected Integer state;// 逻辑删除

	public Application() {
		super();
	}

	public Application(Long id) {
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
	
	public String getLogoImageUrl() {
		return logoImageUrl;
	}
	public void setLogoImageUrl(String logoImageUrl) {
		this.logoImageUrl = logoImageUrl;
	}
	
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
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
