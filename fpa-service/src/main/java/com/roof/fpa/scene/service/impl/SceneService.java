package com.roof.fpa.scene.service.impl;

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
import com.roof.fpa.cardgroup.entity.CardGroupVo;
import com.roof.fpa.cardgroup.service.api.ICardGroupService;
import com.roof.fpa.cardslot.entity.CardSlot;
import com.roof.fpa.cardslot.entity.CardSlotVo;
import com.roof.fpa.cardslot.service.api.ICardSlotService;
import com.roof.fpa.charactercolor.entity.CharacterColorVo;
import com.roof.fpa.theme.service.api.IThemeService;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.scene.dao.api.ISceneDao;
import com.roof.fpa.scene.entity.Scene;
import com.roof.fpa.scene.entity.SceneVo;
import com.roof.fpa.scene.service.api.ISceneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SceneService implements ISceneService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SceneService.class);
	private ISceneDao sceneDao;

	@Autowired
	private ICardGroupService cardGroupService;
	@Autowired
	private ICardSlotService cardSlotService;

	//private Cache<Object, Object> cache = CacheFactory.getInstance().getSceneVoCache();

	@Autowired
	private ListeningExecutorService listeningExecutorService;
	private LoadingCache<Long, SceneVo> cache = CacheBuilder.newBuilder()
			.maximumSize(5)
			.refreshAfterWrite(5, TimeUnit.MINUTES)
			.build(new CacheLoader<Long, SceneVo>() {
				@Override
				public SceneVo load(Long id) throws Exception {
					return loadloadByBase(id);
				}

				@Override
				public ListenableFuture<SceneVo> reload(Long key, SceneVo oldValue) throws Exception {
					return listeningExecutorService.submit(new Callable<SceneVo>() {
						@Override
						public SceneVo call() throws Exception {
							return loadloadByBase(key);
						}
					});
				}
			});


	public Serializable save(Scene scene){
		return sceneDao.save(scene);
	}

	public void delete(Scene scene){
		sceneDao.delete(scene);
	}
	
	public void deleteByExample(Scene scene){
		sceneDao.deleteByExample(scene);
	}

	public void update(Scene scene){
		sceneDao.update(scene);
	}
	
	public void updateIgnoreNull(Scene scene){
		sceneDao.updateIgnoreNull(scene);
	}
		
	public void updateByExample(Scene scene){
		sceneDao.update("updateByExampleScene", scene);
	}

	public SceneVo load(Scene scene){
		return (SceneVo)sceneDao.reload(scene);
	}
	
	public SceneVo selectForObject(Scene scene){
		return (SceneVo)sceneDao.selectForObject("selectScene",scene);
	}
	
	public List<SceneVo> selectForList(Scene scene){
		return (List<SceneVo>)sceneDao.selectForList("selectScene",scene);
	}
	
	public Page page(Page page, Scene scene) {
		return sceneDao.page(page, scene);
	}

	public SceneVo loadByCache(Long id){
		try {
			return (SceneVo)cache.get(id);
		} catch (ExecutionException e) {
			LOGGER.error(e.getMessage(),e);
		}

		return  loadloadByBase(id);
	}

	private SceneVo loadloadByBase(Long id){
		SceneVo sceneVo = load(new Scene(id));
		CardSlot cardSlot = new CardSlot();
		List<CardSlotVo> cardSlotVos = cardSlotService.selectForList(cardSlot);
		sceneVo.setCardSlotList(cardSlotVos);
		CardGroupVo cardGroupVo = cardGroupService.loadCardByCardGroupId(id);
		sceneVo.setCardGroup(cardGroupVo);
		return  sceneVo;
	}

	@Autowired
	public void setISceneDao(
			@Qualifier("sceneDao") ISceneDao  sceneDao) {
		this.sceneDao = sceneDao;
	}
	

}
