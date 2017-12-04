package com.roof.fpa.cardtestresultdetail.service.impl;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;
import com.roof.fpa.cardtestresult.entity.CardTestResultDto;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.cardtestresultdetail.dao.api.ICardTestResultDetailDao;
import com.roof.fpa.cardtestresultdetail.entity.CardTestResultDetail;
import com.roof.fpa.cardtestresultdetail.entity.CardTestResultDetailVo;
import com.roof.fpa.cardtestresultdetail.service.api.ICardTestResultDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CardTestResultDetailService implements ICardTestResultDetailService {
	private ICardTestResultDetailDao cardTestResultDetailDao;

	public Serializable save(CardTestResultDetail cardTestResultDetail){
		return cardTestResultDetailDao.save(cardTestResultDetail);
	}

	public void delete(CardTestResultDetail cardTestResultDetail){
		cardTestResultDetailDao.delete(cardTestResultDetail);
	}
	
	public void deleteByExample(CardTestResultDetail cardTestResultDetail){
		cardTestResultDetailDao.deleteByExample(cardTestResultDetail);
	}

	public void update(CardTestResultDetail cardTestResultDetail){
		cardTestResultDetailDao.update(cardTestResultDetail);
	}
	
	public void updateIgnoreNull(CardTestResultDetail cardTestResultDetail){
		cardTestResultDetailDao.updateIgnoreNull(cardTestResultDetail);
	}
		
	public void updateByExample(CardTestResultDetail cardTestResultDetail){
		cardTestResultDetailDao.update("updateByExampleCardTestResultDetail", cardTestResultDetail);
	}

	public CardTestResultDetailVo load(CardTestResultDetail cardTestResultDetail){
		return (CardTestResultDetailVo)cardTestResultDetailDao.reload(cardTestResultDetail);
	}
	
	public CardTestResultDetailVo selectForObject(CardTestResultDetail cardTestResultDetail){
		return (CardTestResultDetailVo)cardTestResultDetailDao.selectForObject("selectCardTestResultDetail",cardTestResultDetail);
	}
	
	public List<CardTestResultDetailVo> selectForList(CardTestResultDetail cardTestResultDetail){
		return (List<CardTestResultDetailVo>)cardTestResultDetailDao.selectForList("selectCardTestResultDetail",cardTestResultDetail);
	}
	
	public Page page(Page page, CardTestResultDetail cardTestResultDetail) {
		return cardTestResultDetailDao.page(page, cardTestResultDetail);
	}

	@Override
	public void saveList(List<CardTestResultDto> cardTestResultDtoList, Long resultId) {
		List<CardTestResultDetail> details = Lists.newArrayList();
		for (CardTestResultDto dto :cardTestResultDtoList){
			CardTestResultDetail detail = new CardTestResultDetail();
			detail.setCardSlotId(dto.getCardSlotId());
			detail.setCardUnitId(dto.getCardUnitId());
			detail.setResultId(resultId);
			details.add(detail);
			//this.save(detail);

		}
		this.cardTestResultDetailDao.batchinsert(details);

	}

	@Autowired
	public void setICardTestResultDetailDao(
			@Qualifier("cardTestResultDetailDao") ICardTestResultDetailDao  cardTestResultDetailDao) {
		this.cardTestResultDetailDao = cardTestResultDetailDao;
	}
	

}
