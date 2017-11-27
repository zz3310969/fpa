package com.roof.fpa.cardtestresult.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_card_test_result <br/>
 *         描述：卡牌测试结果 <br/>
 */
public class CardTestResultVo extends CardTestResult {

	private List<CardTestResultVo> cardTestResultList;

	public CardTestResultVo() {
		super();
	}

	public CardTestResultVo(Long id) {
		super();
		this.id = id;
	}

	public List<CardTestResultVo> getCardTestResultList() {
		return cardTestResultList;
	}

	public void setCardTestResultList(List<CardTestResultVo> cardTestResultList) {
		this.cardTestResultList = cardTestResultList;
	}

}
