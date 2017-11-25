package com.roof.fpa.cardunit.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_card_unit <br/>
 *         描述：卡牌单元 <br/>
 */
public class CardUnitVo extends CardUnit {

	private List<CardUnitVo> cardUnitList;

	public CardUnitVo() {
		super();
	}

	public CardUnitVo(Long id) {
		super();
		this.id = id;
	}

	public List<CardUnitVo> getCardUnitList() {
		return cardUnitList;
	}

	public void setCardUnitList(List<CardUnitVo> cardUnitList) {
		this.cardUnitList = cardUnitList;
	}

}
