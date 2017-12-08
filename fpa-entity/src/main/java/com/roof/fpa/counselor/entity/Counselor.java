package com.roof.fpa.counselor.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： c_counselor <br/>
 *         描述：咨询师 <br/>
 */
@ApiModel(value = "c_counselor", description = "咨询师")
public class Counselor implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "主键")
	protected Long id;// 主键
	@ApiModelProperty(value = "咨询师编号")
	protected String numb;// 咨询师编号
	@ApiModelProperty(value = "姓名")
	protected String name;// 姓名
	@ApiModelProperty(value = "电话")
	protected String mobile;// 电话
	@ApiModelProperty(value = "性别(0:女,1:男)")
	protected Integer gender;// 性别(0:女,1:男)
	@ApiModelProperty(value = "特长")
	protected String specialty;// 特长
	@ApiModelProperty(value = "微信号")
	protected String wechat;// 微信号
	@ApiModelProperty(value = "级别")
	protected Long rank;// 级别
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "注册时间")
	protected Date regTime;// 注册时间
	@ApiModelProperty(value = "状态")
	protected Integer state;// 状态
	@ApiModelProperty(value = "是否可用,用于删除")
	protected Integer usable;// 是否可用,用于删除

	public Counselor() {
		super();
	}

	public Counselor(Long id) {
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
	
	public String getNumb() {
		return numb;
	}
	public void setNumb(String numb) {
		this.numb = numb;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	
	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
	}
	
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	public Integer getUsable() {
		return usable;
	}
	public void setUsable(Integer usable) {
		this.usable = usable;
	}
}
