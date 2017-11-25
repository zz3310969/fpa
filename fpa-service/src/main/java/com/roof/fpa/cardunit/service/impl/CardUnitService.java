package com.roof.fpa.cardunit.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.cardunit.dao.api.ICardUnitDao;
import com.roof.fpa.cardunit.entity.CardUnit;
import com.roof.fpa.cardunit.entity.CardUnitVo;
import com.roof.fpa.cardunit.service.api.ICardUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CardUnitService implements ICardUnitService {
	private ICardUnitDao cardUnitDao;

	public Serializable save(CardUnit cardUnit){
		return cardUnitDao.save(cardUnit);
	}

	public void delete(CardUnit cardUnit){
		cardUnitDao.delete(cardUnit);
	}
	
	public void deleteByExample(CardUnit cardUnit){
		cardUnitDao.deleteByExample(cardUnit);
	}

	public void update(CardUnit cardUnit){
		cardUnitDao.update(cardUnit);
	}
	
	public void updateIgnoreNull(CardUnit cardUnit){
		cardUnitDao.updateIgnoreNull(cardUnit);
	}
		
	public void updateByExample(CardUnit cardUnit){
		cardUnitDao.update("updateByExampleCardUnit", cardUnit);
	}

	public CardUnitVo load(CardUnit cardUnit){
		return (CardUnitVo)cardUnitDao.reload(cardUnit);
	}
	
	public CardUnitVo selectForObject(CardUnit cardUnit){
		return (CardUnitVo)cardUnitDao.selectForObject("selectCardUnit",cardUnit);
	}
	
	public List<CardUnitVo> selectForList(CardUnit cardUnit){
		return (List<CardUnitVo>)cardUnitDao.selectForList("selectCardUnit",cardUnit);
	}
	
	public Page page(Page page, CardUnit cardUnit) {
		return cardUnitDao.page(page, cardUnit);
	}

	@Autowired
	public void setICardUnitDao(
			@Qualifier("cardUnitDao") ICardUnitDao  cardUnitDao) {
		this.cardUnitDao = cardUnitDao;
	}
	

}
