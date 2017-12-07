package com.roof.fpa.cardunit.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.roof.fpa.cache.impl.CacheFactory;
import com.roof.fpa.cardslot.entity.CardSlot;
import com.roof.fpa.cardslot.entity.CardSlotVo;
import com.roof.fpa.scene.entity.SceneVo;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.cardunit.dao.api.ICardUnitDao;
import com.roof.fpa.cardunit.entity.CardUnit;
import com.roof.fpa.cardunit.entity.CardUnitVo;
import com.roof.fpa.cardunit.service.api.ICardUnitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CardUnitService implements ICardUnitService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CardUnitService.class);
	//private Cache<Object, Object> cache = CacheFactory.getInstance().getCardUnitVoCache();


	@Autowired
	private ListeningExecutorService listeningExecutorService;
	private LoadingCache<Long, CardUnitVo> cache = CacheBuilder.newBuilder()
			.maximumSize(50)
			.refreshAfterWrite(5, TimeUnit.MINUTES)
			.build(new CacheLoader<Long, CardUnitVo>() {
				@Override
				public CardUnitVo load(Long id) throws Exception {
					LOGGER.debug("CardUnitVo Cache load id ="+id);
					return (CardUnitVo) cardUnitDao.reload(new CardUnit(id));
				}

				@Override
				public ListenableFuture<CardUnitVo> reload(Long key, CardUnitVo oldValue) throws Exception {
					return listeningExecutorService.submit(new Callable<CardUnitVo>() {

						@Override
						public CardUnitVo call() throws Exception {
							LOGGER.debug("CardUnitVo Cache reload");
							return (CardUnitVo) cardUnitDao.reload(new CardUnit(key));
						}
					});
				}
			});


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



	@Override
	public CardUnitVo loadByCache(Long id) {
		try {
			return cache.get(id);
		} catch (ExecutionException e) {
			LOGGER.error(e.getMessage(),e);
		}
		return load(new CardUnit(id));
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
