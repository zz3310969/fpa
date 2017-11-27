package com.roof.fpa.cardslot.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_card_slot <br/>
 *         描述：卡槽 <br/>
 */
public class CardSlotVo extends CardSlot {

	private List<CardSlotVo> cardSlotList;

	public CardSlotVo() {
		super();
	}

	public CardSlotVo(Long id) {
		super();
		this.id = id;
	}

	public List<CardSlotVo> getCardSlotList() {
		return cardSlotList;
	}

	public void setCardSlotList(List<CardSlotVo> cardSlotList) {
		this.cardSlotList = cardSlotList;
	}

}
