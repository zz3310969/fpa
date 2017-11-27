package com.roof.fpa.card.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_card <br/>
 *         描述：卡牌 <br/>
 */
public class CardVo extends Card {

	private List<CardVo> cardList;

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

}
