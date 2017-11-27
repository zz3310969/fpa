package com.roof.fpa.cardtestresultdetail.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_card_test_result_detail <br/>
 *         描述：卡牌测试结果详情 <br/>
 */
public class CardTestResultDetailVo extends CardTestResultDetail {

	private List<CardTestResultDetailVo> cardTestResultDetailList;

	public CardTestResultDetailVo() {
		super();
	}

	public CardTestResultDetailVo(Long id) {
		super();
		this.id = id;
	}

	public List<CardTestResultDetailVo> getCardTestResultDetailList() {
		return cardTestResultDetailList;
	}

	public void setCardTestResultDetailList(List<CardTestResultDetailVo> cardTestResultDetailList) {
		this.cardTestResultDetailList = cardTestResultDetailList;
	}

}
