package com.roof.fpa.card.entity;

import com.roof.fpa.cardunit.entity.CardUnitVo;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_card <br/>
 *         描述：卡牌 <br/>
 */
public class CardVo extends Card {

	private List<CardVo> cardList;

	List<CardUnitVo> cardUnitList;

	public CardVo() {
		super();
	}

	public CardVo(Long id) {
		super();
		this.id = id;
	}

	public List<CardVo> getCardList() {
		return cardList;
	}

	public void setCardList(List<CardVo> cardList) {
		this.cardList = cardList;
	}

	public List<CardUnitVo> getCardUnitList() {
		return cardUnitList;
	}

	public void setCardUnitList(List<CardUnitVo> cardUnitList) {
		this.cardUnitList = cardUnitList;
	}
}
