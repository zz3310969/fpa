package com.roof.fpa.cardslot.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.cardslot.dao.api.ICardSlotDao;
import com.roof.fpa.cardslot.entity.CardSlot;
import com.roof.fpa.cardslot.entity.CardSlotVo;
import com.roof.fpa.cardslot.service.api.ICardSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CardSlotService implements ICardSlotService {
	private ICardSlotDao cardSlotDao;

	public Serializable save(CardSlot cardSlot){
		return cardSlotDao.save(cardSlot);
	}

	public void delete(CardSlot cardSlot){
		cardSlotDao.delete(cardSlot);
	}
	
	public void deleteByExample(CardSlot cardSlot){
		cardSlotDao.deleteByExample(cardSlot);
	}

	public void update(CardSlot cardSlot){
		cardSlotDao.update(cardSlot);
	}
	
	public void updateIgnoreNull(CardSlot cardSlot){
		cardSlotDao.updateIgnoreNull(cardSlot);
	}
		
	public void updateByExample(CardSlot cardSlot){
		cardSlotDao.update("updateByExampleCardSlot", cardSlot);
	}

	public CardSlotVo load(CardSlot cardSlot){
		return (CardSlotVo)cardSlotDao.reload(cardSlot);
	}
	
	public CardSlotVo selectForObject(CardSlot cardSlot){
		return (CardSlotVo)cardSlotDao.selectForObject("selectCardSlot",cardSlot);
	}
	
	public List<CardSlotVo> selectForList(CardSlot cardSlot){
		return (List<CardSlotVo>)cardSlotDao.selectForList("selectCardSlot",cardSlot);
	}
	
	public Page page(Page page, CardSlot cardSlot) {
		return cardSlotDao.page(page, cardSlot);
	}

	@Autowired
	public void setICardSlotDao(
			@Qualifier("cardSlotDao") ICardSlotDao  cardSlotDao) {
		this.cardSlotDao = cardSlotDao;
	}
	

}
