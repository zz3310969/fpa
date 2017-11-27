package com.roof.fpa.card.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： c_card <br/>
 *         描述：卡牌 <br/>
 */
@ApiModel(value = "c_card", description = "卡牌")
public class Card implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "主键")
	protected Long id;// 主键
	@ApiModelProperty(value = "所属套牌")
	protected Long cardGroupId;// 所属套牌

	public Card() {
		super();
	}

	public Card(Long id) {
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
	
	public Long getCardGroupId() {
		return cardGroupId;
	}
	public void setCardGroupId(Long cardGroupId) {
		this.cardGroupId = cardGroupId;
	}
}
