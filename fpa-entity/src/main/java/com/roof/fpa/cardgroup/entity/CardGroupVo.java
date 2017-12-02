package com.roof.fpa.cardgroup.entity;

import com.roof.fpa.card.entity.CardVo;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_card_group <br/>
 *         描述：套牌 <br/>
 */
public class CardGroupVo extends CardGroup {

	private List<CardGroupVo> cardGroupList;

	List<CardVo> cardList;

	public CardGroupVo() {
		super();
	}

	public CardGroupVo(Long id) {
		super();
		this.id = id;
	}

	public List<CardGroupVo> getCardGroupList() {
		return cardGroupList;
	}

	public void setCardGroupList(List<CardGroupVo> cardGroupList) {
		this.cardGroupList = cardGroupList;
	}

	public List<CardVo> getCardList() {
		return cardList;
	}

	public void setCardList(List<CardVo> cardList) {
		this.cardList = cardList;
	}
}
