package com.roof.fpa.cardunit.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： c_card_unit <br/>
 *         描述：卡牌单元 <br/>
 */
@ApiModel(value = "c_card_unit", description = "卡牌单元")
public class CardUnit implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "主键")
	protected Long id;// 主键
	@ApiModelProperty(value = "颜色编码")
	protected String colorCode;// 颜色编码
	@ApiModelProperty(value = "所属卡牌")
	protected Long cardId;// 所属卡牌
	@ApiModelProperty(value = "名称")
	protected String name;// 名称
	@ApiModelProperty(value = "颜色")
	protected Long colorId;// 颜色
	@ApiModelProperty(value = "大图")
	protected String imageBig;// 大图
	@ApiModelProperty(value = "中图")
	protected String imageMid;// 中图
	@ApiModelProperty(value = "小图")
	protected String imageSmall;// 小图
	@ApiModelProperty(value = "分值")
	protected Integer score;// 分值
	@ApiModelProperty(value = "描述")
	protected String description;// 描述
	@ApiModelProperty(value = "卡牌单元类型(正面,反面)")
	protected Long unitType;// 卡牌单元类型(正面,反面)

	public CardUnit() {
		super();
	}

	public CardUnit(Long id) {
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
	
	public String getColorCode() {
		return colorCode;
	}
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}
	
	public Long getCardId() {
		return cardId;
	}
	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getColorId() {
		return colorId;
	}
	public void setColorId(Long colorId) {
		this.colorId = colorId;
	}
	
	public String getImageBig() {
		return imageBig;
	}
	public void setImageBig(String imageBig) {
		this.imageBig = imageBig;
	}
	
	public String getImageMid() {
		return imageMid;
	}
	public void setImageMid(String imageMid) {
		this.imageMid = imageMid;
	}
	
	public String getImageSmall() {
		return imageSmall;
	}
	public void setImageSmall(String imageSmall) {
		this.imageSmall = imageSmall;
	}
	
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Long getUnitType() {
		return unitType;
	}
	public void setUnitType(Long unitType) {
		this.unitType = unitType;
	}
}
