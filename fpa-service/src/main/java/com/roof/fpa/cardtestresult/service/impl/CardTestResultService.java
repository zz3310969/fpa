package com.roof.fpa.cardtestresult.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.roof.chain.api.Chain;
import com.roof.chain.api.ValueStack;
import com.roof.chain.support.GenericValueStack;
import com.roof.fpa.cardtestresult.entity.CardTestResultDto;
import com.roof.fpa.cardtestresult.entity.GeneralCardTestCustomerResult;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.cardtestresult.dao.api.ICardTestResultDao;
import com.roof.fpa.cardtestresult.entity.CardTestResult;
import com.roof.fpa.cardtestresult.entity.CardTestResultVo;
import com.roof.fpa.cardtestresult.service.api.ICardTestResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CardTestResultService implements ICardTestResultService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CardTestResultService.class);
	private Chain customerResultChain;
	private ICardTestResultDao cardTestResultDao;

	public Serializable save(CardTestResult cardTestResult){
		cardTestResult.setTestDate(new Date());
		return cardTestResultDao.save(cardTestResult);
	}

	public void delete(CardTestResult cardTestResult){
		cardTestResultDao.delete(cardTestResult);
	}
	
	public void deleteByExample(CardTestResult cardTestResult){
		cardTestResultDao.deleteByExample(cardTestResult);
	}

	public void update(CardTestResult cardTestResult){
		cardTestResultDao.update(cardTestResult);
	}
	
	public void updateIgnoreNull(CardTestResult cardTestResult){
		cardTestResultDao.updateIgnoreNull(cardTestResult);
	}
		
	public void updateByExample(CardTestResult cardTestResult){
		cardTestResultDao.update("updateByExampleCardTestResult", cardTestResult);
	}

	public CardTestResultVo load(CardTestResult cardTestResult){
		return (CardTestResultVo)cardTestResultDao.reload(cardTestResult);
	}
	
	public CardTestResultVo selectForObject(CardTestResult cardTestResult){
		return (CardTestResultVo)cardTestResultDao.selectForObject("selectCardTestResult",cardTestResult);
	}
	
	public List<CardTestResultVo> selectForList(CardTestResult cardTestResult){
		return (List<CardTestResultVo>)cardTestResultDao.selectForList("selectCardTestResult",cardTestResult);
	}

	@Override
	public GeneralCardTestCustomerResult calculate(CardTestResultVo cardTestResultVo) {
		GeneralCardTestCustomerResult generalCardTestCustomerResult = new GeneralCardTestCustomerResult();
		ValueStack valueStack = new GenericValueStack();
		valueStack.set("cardTestResultVo", cardTestResultVo);
		valueStack.set("generalCardTestCustomerResult", generalCardTestCustomerResult);
		try {
			customerResultChain.doChain(valueStack);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return generalCardTestCustomerResult;
	}
	
	public Page page(Page page, CardTestResult cardTestResult) {
		return cardTestResultDao.page(page, cardTestResult);
	}

	@Autowired
	public void setICardTestResultDao(
			@Qualifier("cardTestResultDao") ICardTestResultDao  cardTestResultDao) {
		this.cardTestResultDao = cardTestResultDao;
	}

	@Autowired
	public void setCustomerResultChain(@Qualifier("customerResultChain") Chain customerResultChain) {
		this.customerResultChain = customerResultChain;
	}
}
