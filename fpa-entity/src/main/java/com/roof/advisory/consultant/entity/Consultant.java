package com.roof.advisory.consultant.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： z_consultant <br/>
 *         描述：咨询师 <br/>
 */
@ApiModel(value = "z_consultant", description = "咨询师")
public class Consultant implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "主键")
	protected Long id;// 主键
	@ApiModelProperty(value = "所属系统")
	protected Long appId;// 所属系统
	@ApiModelProperty(value = "咨询师姓名")
	protected String name;// 咨询师姓名
	@ApiModelProperty(value = "头像")
	protected String headImageUrl;// 头像
	@ApiModelProperty(value = "等级")
	protected Long levelId;// 等级
	@ApiModelProperty(value = "服务主题")
	protected Long themeId;// 服务主题
	@ApiModelProperty(value = "所在地区")
	protected Long areaId;// 所在地区
	@ApiModelProperty(value = "性别")
	protected Integer gender;// 性别
	@ApiModelProperty(value = "简介")
	protected String introduction;// 简介
	@ApiModelProperty(value = "状态")
	protected Integer status;// 状态
	@ApiModelProperty(value = "逻辑删除使用")
	protected Integer state;// 逻辑删除使用

	public Consultant() {
		super();
	}

	public Consultant(Long id) {
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
	
	public String getHeadImageUrl() {
		return headImageUrl;
	}
	public void setHeadImageUrl(String headImageUrl) {
		this.headImageUrl = headImageUrl;
	}
	
	public Long getLevelId() {
		return levelId;
	}
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	
	public Long getThemeId() {
		return themeId;
	}
	public void setThemeId(Long themeId) {
		this.themeId = themeId;
	}
	
	public Long getAreaId() {
		return areaId;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
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
}
