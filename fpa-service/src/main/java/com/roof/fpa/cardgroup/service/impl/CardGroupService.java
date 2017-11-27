package com.roof.fpa.cardgroup.service.impl;

import java.io.Serializable;
import java.util.List;
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

	@Autowired
	public void setICardGroupDao(
			@Qualifier("cardGroupDao") ICardGroupDao  cardGroupDao) {
		this.cardGroupDao = cardGroupDao;
	}
	

}
