package com.roof.fpa.scene.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： c_scene <br/>
 *         描述：场景 <br/>
 */
@ApiModel(value = "c_scene", description = "场景")
public class Scene implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "场景")
	protected Long id;// 场景
	@ApiModelProperty(value = "名称")
	protected String name;// 名称
	@ApiModelProperty(value = "编号")
	protected String numb;// 编号
	@ApiModelProperty(value = "套牌id")
	protected Long cardGroupId;// 套牌id
	@ApiModelProperty(value = "可重复次数")
	protected Integer repeatCount;// 可重复次数
	@ApiModelProperty(value = "版本")
	protected String version;// 版本
	@ApiModelProperty(value = "主题ID")
	protected Long themeId;// 主题ID
	@ApiModelProperty(value = "状态")
	protected Integer state;// 状态
	@ApiModelProperty(value = "模板ID")
	private Long templateId; //模板ID

	private String weightOperator;

	public Scene() {
		super();
	}

	public Scene(Long id) {
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
	
	public String getNumb() {
		return numb;
	}
	public void setNumb(String numb) {
		this.numb = numb;
	}
	
	public Long getCardGroupId() {
		return cardGroupId;
	}
	public void setCardGroupId(Long cardGroupId) {
		this.cardGroupId = cardGroupId;
	}
	
	public Integer getRepeatCount() {
		return repeatCount;
	}
	public void setRepeatCount(Integer repeatCount) {
		this.repeatCount = repeatCount;
	}
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	public Long getThemeId() {
		return themeId;
	}
	public void setThemeId(Long themeId) {
		this.themeId = themeId;
	}
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public String getWeightOperator() {
		return weightOperator;
	}

	public void setWeightOperator(String weightOperator) {
		this.weightOperator = weightOperator;
	}
}
