package com.roof.fpa.cardtestresult.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.cardtestresult.dao.api.ICardTestResultDao;
import com.roof.fpa.cardtestresult.entity.CardTestResult;
import com.roof.fpa.cardtestresult.entity.CardTestResultVo;
import com.roof.fpa.cardtestresult.service.api.ICardTestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CardTestResultService implements ICardTestResultService {
	private ICardTestResultDao cardTestResultDao;

	public Serializable save(CardTestResult cardTestResult){
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
	
	public Page page(Page page, CardTestResult cardTestResult) {
		return cardTestResultDao.page(page, cardTestResult);
	}

	@Autowired
	public void setICardTestResultDao(
			@Qualifier("cardTestResultDao") ICardTestResultDao  cardTestResultDao) {
		this.cardTestResultDao = cardTestResultDao;
	}
	

}
