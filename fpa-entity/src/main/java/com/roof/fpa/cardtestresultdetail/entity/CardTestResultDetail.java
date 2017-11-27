package com.roof.fpa.cardtestresultdetail.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： c_card_test_result_detail <br/>
 *         描述：卡牌测试结果详情 <br/>
 */
@ApiModel(value = "c_card_test_result_detail", description = "卡牌测试结果详情")
public class CardTestResultDetail implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "主键")
	protected Long id;// 主键
	@ApiModelProperty(value = "卡槽位ID")
	protected Long cardSlotId;// 卡槽位ID
	@ApiModelProperty(value = "卡牌单元id")
	protected Long cardUnitId;// 卡牌单元id

	public CardTestResultDetail() {
		super();
	}

	public CardTestResultDetail(Long id) {
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
	
	public Long getCardSlotId() {
		return cardSlotId;
	}
	public void setCardSlotId(Long cardSlotId) {
		this.cardSlotId = cardSlotId;
	}
	
	public Long getCardUnitId() {
		return cardUnitId;
	}
	public void setCardUnitId(Long cardUnitId) {
		this.cardUnitId = cardUnitId;
	}
}
