package com.roof.fpa.cardtestresult.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.roof.fpa.cardtestresultdetail.entity.CardTestResultDetailVo;
import com.roof.fpa.customer.entity.CustomerVo;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 模版生成 <br/>
 *         表名： c_card_test_result <br/>
 *         描述：卡牌测试结果 <br/>
 */
public class CardTestResultVo extends CardTestResult {

	private List<CardTestResultVo> cardTestResultList;

	private List<CardTestResultDto> resultDtoList;
	private List<CardTestResultDetailVo> cardTestResultDetailVoList;

	private Score advantage;

	private Score weakness;

	private String practiceAdvice;

	private String customerName;

	private String customerHeadImageUrl;

	private String sceneName;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date testDateStart;// 测试时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date testDateEnd;// 测试时间

	private CustomerVo customer;

	@JSONField(format = "yyyy年MM月dd日")
	@ApiModelProperty(value = "测试时间")
	protected Date testDay;// 测试时间

	private List<Map<String,Object>> chats;


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

	public List<Map<String, Object>> getChats() {
		return chats;
	}

	public void setChats(List<Map<String, Object>> chats) {
		this.chats = chats;
	}

	public String getCustomerHeadImageUrl() {
		return customerHeadImageUrl;
	}

	public void setCustomerHeadImageUrl(String customerHeadImageUrl) {
		this.customerHeadImageUrl = customerHeadImageUrl;
	}

	public Score getAdvantage() {
		return advantage;
	}

	public void setAdvantage(Score advantage) {
		this.advantage = advantage;
	}

	public Score getWeakness() {
		return weakness;
	}

	public void setWeakness(Score weakness) {
		this.weakness = weakness;
	}

	public String getPracticeAdvice() {
		return practiceAdvice;
	}

	public void setPracticeAdvice(String practiceAdvice) {
		this.practiceAdvice = practiceAdvice;
	}

	public Date getTestDay() {
		return super.testDate;
	}


}
