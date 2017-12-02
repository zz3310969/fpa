package com.roof.fpa.cardgroup.service.impl;

import java.io.Serializable;
import java.util.List;

import com.roof.fpa.card.entity.Card;
import com.roof.fpa.card.entity.CardVo;
import com.roof.fpa.card.service.api.ICardService;
import com.roof.fpa.cardunit.entity.CardUnit;
import com.roof.fpa.cardunit.entity.CardUnitVo;
import com.roof.fpa.cardunit.service.api.ICardUnitService;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.cardgroup.dao.api.ICardGroupDao;
import com.roof.fpa.cardgroup.entity.CardGroup;
import com.roof.fpa.cardgroup.entity.CardGroupVo;
import com.roof.fpa.cardgroup.service.api.ICardGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CardGroupService implements ICardGroupService {
	private ICardGroupDao cardGroupDao;

	@Autowired
	private ICardUnitService cardUnitService;
	@Autowired
	private ICardService cardService;

	public Serializable save(CardGroup cardGroup){
		return cardGroupDao.save(cardGroup);
	}

	public void delete(CardGroup cardGroup){
		cardGroupDao.delete(cardGroup);
	}
	
	public void deleteByExample(CardGroup cardGroup){
		cardGroupDao.deleteByExample(cardGroup);
	}

	public void update(CardGroup cardGroup){
		cardGroupDao.update(cardGroup);
	}
	
	public void updateIgnoreNull(CardGroup cardGroup){
		cardGroupDao.updateIgnoreNull(cardGroup);
	}
		
	public void updateByExample(CardGroup cardGroup){
		cardGroupDao.update("updateByExampleCardGroup", cardGroup);
	}

	public CardGroupVo load(CardGroup cardGroup){
		return (CardGroupVo)cardGroupDao.reload(cardGroup);
	}
	
	public CardGroupVo selectForObject(CardGroup cardGroup){
		return (CardGroupVo)cardGroupDao.selectForObject("selectCardGroup",cardGroup);
	}
	
	public List<CardGroupVo> selectForList(CardGroup cardGroup){
		return (List<CardGroupVo>)cardGroupDao.selectForList("selectCardGroup",cardGroup);
	}
	
	public Page page(Page page, CardGroup cardGroup) {
		return cardGroupDao.page(page, cardGroup);
	}

	@Override
	public CardGroupVo loadCardByCardGroupId(Long cardGroupId) {
		CardGroupVo vo = load(new CardGroup(cardGroupId));
		Card card = new Card();
		card.setCardGroupId(cardGroupId);
		List<CardVo> cardVos = cardService.selectForList(card);
		for (CardVo cardVo :cardVos){
			CardUnit cardUnit = new CardUnit();
			cardUnit.setCardId(cardVo.getId());
			List<CardUnitVo> cardUnitVos = cardUnitService.selectForList(cardUnit);
			cardVo.setCardUnitList(cardUnitVos);
		}
		vo.setCardList(cardVos);
		return vo;
	}

	@Autowired
	public void setICardGroupDao(
			@Qualifier("cardGroupDao") ICardGroupDao  cardGroupDao) {
		this.cardGroupDao = cardGroupDao;
	}
	

}
