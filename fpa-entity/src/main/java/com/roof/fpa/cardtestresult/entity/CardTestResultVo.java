package com.roof.fpa.cardtestresult.entity;

import com.roof.fpa.cardtestresultdetail.entity.CardTestResultDetailVo;
import com.roof.fpa.customer.entity.CustomerVo;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_card_test_result <br/>
 *         描述：卡牌测试结果 <br/>
 */
public class CardTestResultVo extends CardTestResult {

	private List<CardTestResultVo> cardTestResultList;

	private List<CardTestResultDto> resultDtoList;
	private List<CardTestResultDetailVo> cardTestResultDetailVoList;

	private String customerName;

	private String sceneName;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date testDateStart;// 测试时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date testDateEnd;// 测试时间

	private CustomerVo customer;


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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

	public Date getTestDateStart() {
		return testDateStart;
	}

	public void setTestDateStart(Date testDateStart) {
		this.testDateStart = testDateStart;
	}

	public Date getTestDateEnd() {
		return testDateEnd;
	}

	public void setTestDateEnd(Date testDateEnd) {
		this.testDateEnd = testDateEnd;
	}

	public List<CardTestResultDto> getResultDtoList() {
		return resultDtoList;
	}

	public void setResultDtoList(List<CardTestResultDto> resultDtoList) {
		this.resultDtoList = resultDtoList;
	}

	public CustomerVo getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerVo customer) {
		this.customer = customer;
	}

	public List<CardTestResultDetailVo> getCardTestResultDetailVoList() {
		return cardTestResultDetailVoList;
	}

	public void setCardTestResultDetailVoList(List<CardTestResultDetailVo> cardTestResultDetailVoList) {
		this.cardTestResultDetailVoList = cardTestResultDetailVoList;
	}
}
