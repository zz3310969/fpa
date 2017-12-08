package com.roof.fpa.customerwords.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： c_customer_words <br/>
 *         描述：客户留言 <br/>
 */
@ApiModel(value = "c_customer_words", description = "客户留言")
public class CustomerWords implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "主键")
	protected Long id;// 主键
	@ApiModelProperty(value = "客户名称")
	protected String name;// 客户名称
	@ApiModelProperty(value = "微信unionid")
	protected String wechatId;// 微信unionid
	@ApiModelProperty(value = "客户ID")
	protected Long customerId;// 客户ID
	@ApiModelProperty(value = "手机号")
	protected String mobile;// 手机号
	@ApiModelProperty(value = "邮箱")
	protected String mail;// 邮箱
	@ApiModelProperty(value = "留言")
	protected String words;// 留言

	public CustomerWords() {
		super();
	}

	public CustomerWords(Long id) {
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
	
	public String getWechatId() {
		return wechatId;
	}
	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getWords() {
		return words;
	}
	public void setWords(String words) {
		this.words = words;
	}
}
