package com.roof.fpa.character.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： c_character <br/>
 *         描述：性格 <br/>
 */
@ApiModel(value = "c_character", description = "性格")
public class Character implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "主键")
	protected Long id;// 主键
	@ApiModelProperty(value = "名称")
	protected String name;// 名称
	@ApiModelProperty(value = "主题ID")
	protected Long themeId;// 主题ID
	@ApiModelProperty(value = "性格色彩ID")
	protected Long characterColorId;// 性格色彩ID
	@ApiModelProperty(value = "性别")
	protected Integer gender;// 性别
	@ApiModelProperty(value = "状态")
	protected Integer state;// 状态
	@ApiModelProperty(value = "描述")
	protected String description;// 描述
	@ApiModelProperty(value = "所属卡牌")
	protected Long cardUnitId;// 所属卡牌

	public Character() {
		super();
	}

	public Character(Long id) {
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
	
	public Long getThemeId() {
		return themeId;
	}
	public void setThemeId(Long themeId) {
		this.themeId = themeId;
	}
	
	public Long getCharacterColorId() {
		return characterColorId;
	}
	public void setCharacterColorId(Long characterColorId) {
		this.characterColorId = characterColorId;
	}
	
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Long getCardUnitId() {
		return cardUnitId;
	}
	public void setCardUnitId(Long cardUnitId) {
		this.cardUnitId = cardUnitId;
	}
}
