package com.roof.fpa.card.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.card.dao.api.ICardDao;
import com.roof.fpa.card.entity.Card;
import com.roof.fpa.card.entity.CardVo;
import com.roof.fpa.card.service.api.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CardService implements ICardService {
	private ICardDao cardDao;

	public Serializable save(Card card){
		return cardDao.save(card);
	}

	public void delete(Card card){
		cardDao.delete(card);
	}
	
	public void deleteByExample(Card card){
		cardDao.deleteByExample(card);
	}

	public void update(Card card){
		cardDao.update(card);
	}
	
	public void updateIgnoreNull(Card card){
		cardDao.updateIgnoreNull(card);
	}
		
	public void updateByExample(Card card){
		cardDao.update("updateByExampleCard", card);
	}

	public CardVo load(Card card){
		return (CardVo)cardDao.reload(card);
	}
	
	public CardVo selectForObject(Card card){
		return (CardVo)cardDao.selectForObject("selectCard",card);
	}
	
	public List<CardVo> selectForList(Card card){
		return (List<CardVo>)cardDao.selectForList("selectCard",card);
	}
	
	public Page page(Page page, Card card) {
		return cardDao.page(page, card);
	}

	@Autowired
	public void setICardDao(
			@Qualifier("cardDao") ICardDao  cardDao) {
		this.cardDao = cardDao;
	}
	

}
