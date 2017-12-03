package com.roof.fpa.cardslot.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： c_card_slot <br/>
 *         描述：卡槽 <br/>
 */
@ApiModel(value = "c_card_slot", description = "卡槽")
public class CardSlot implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "主键")
	protected Long id;// 主键
	@ApiModelProperty(value = "编号")
	protected Integer numb;// 编号
	@ApiModelProperty(value = "权重")
	protected Integer weight;// 权重
	@ApiModelProperty(value = "x偏移系数")
	protected Double xVal;// x偏移系数
	@ApiModelProperty(value = "y偏移系数")
		protected Double yVal;// y偏移系数
	@ApiModelProperty(value = "x偏移量")
	protected Double xOffset;// x偏移量
	@ApiModelProperty(value = "y偏移量")
	protected Double yOffset;// y偏移量
	@ApiModelProperty(value = "描述")
	protected String description;// 描述
	@ApiModelProperty(value = "场景id")
	protected Long sceneId;// 场景id

	public CardSlot() {
		super();
	}

	public CardSlot(Long id) {
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
	
	public Integer getNumb() {
		return numb;
	}
	public void setNumb(Integer numb) {
		this.numb = numb;
	}
	
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
	public Double getXVal() {
		return xVal;
	}
	public void setXVal(Double xVal) {
		this.xVal = xVal;
	}
	
	public Double getYVal() {
		return yVal;
	}
	public void setYVal(Double yVal) {
		this.yVal = yVal;
	}
	
	public Double getXOffset() {
		return xOffset;
	}
	public void setXOffset(Double xOffset) {
		this.xOffset = xOffset;
	}
	
	public Double getYOffset() {
		return yOffset;
	}
	public void setYOffset(Double yOffset) {
		this.yOffset = yOffset;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Long getSceneId() {
		return sceneId;
	}
	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}
}
