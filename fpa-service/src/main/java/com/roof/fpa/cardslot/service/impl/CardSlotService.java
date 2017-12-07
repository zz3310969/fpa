package com.roof.fpa.cardslot.service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.roof.fpa.cache.impl.CacheFactory;
import com.roof.fpa.cardslot.dao.api.ICardSlotDao;
import com.roof.fpa.cardslot.entity.CardSlot;
import com.roof.fpa.cardslot.entity.CardSlotVo;
import com.roof.fpa.cardslot.service.api.ICardSlotService;
import com.roof.fpa.cardunit.entity.CardUnit;
import com.roof.fpa.cardunit.entity.CardUnitVo;
import org.roof.roof.dataaccess.api.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.*;

@Service
public class CardSlotService implements ICardSlotService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CardSlotService.class);


    @Autowired
    private ListeningExecutorService listeningExecutorService;
    private LoadingCache<Long, CardSlotVo> cache = CacheBuilder.newBuilder()
            .maximumSize(50)
            .refreshAfterWrite(5, TimeUnit.MINUTES)
            .build(new CacheLoader<Long, CardSlotVo>() {
                @Override
                public CardSlotVo load(Long id) throws Exception {
                    return (CardSlotVo) cardSlotDao.reload(new CardSlot(id));
                }

                @Override
                public ListenableFuture<CardSlotVo> reload(Long key, CardSlotVo oldValue) throws Exception {
                    return listeningExecutorService.submit(new Callable<CardSlotVo>() {
                        @Override
                        public CardSlotVo call() throws Exception {
                            return (CardSlotVo) cardSlotDao.reload(new CardSlot(key));
                        }
                    });
                }
            });


    private ICardSlotDao cardSlotDao;

    public Serializable save(CardSlot cardSlot) {
        return cardSlotDao.save(cardSlot);
    }

    public void delete(CardSlot cardSlot) {
        cardSlotDao.delete(cardSlot);
    }

    public void deleteByExample(CardSlot cardSlot) {
        cardSlotDao.deleteByExample(cardSlot);
    }

    public void update(CardSlot cardSlot) {
        cardSlotDao.update(cardSlot);
    }

    public void updateIgnoreNull(CardSlot cardSlot) {
        cardSlotDao.updateIgnoreNull(cardSlot);
    }

    public void updateByExample(CardSlot cardSlot) {
        cardSlotDao.update("updateByExampleCardSlot", cardSlot);
    }

    @Override
    public CardSlotVo loadByCache(Long id) {
        try {
            return cache.get(id);
        } catch (ExecutionException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return load(new CardSlot(id));

    }

    public CardSlotVo load(CardSlot cardSlot) {
        return (CardSlotVo) cardSlotDao.reload(cardSlot);
    }

    public CardSlotVo selectForObject(CardSlot cardSlot) {
        return (CardSlotVo) cardSlotDao.selectForObject("selectCardSlot", cardSlot);
    }

    public List<CardSlotVo> selectForList(CardSlot cardSlot) {
        return (List<CardSlotVo>) cardSlotDao.selectForList("selectCardSlot", cardSlot);
    }

    /**
     * 通过场景ID和编号查找卡槽
     *
     * @param sceneId 场景ID
     * @param id      编号
     * @return 卡槽
     */
    @Override
    public CardSlotVo selectBySceneIdAndNumb(Long sceneId, Long id) {
        //TODO add cache
        CardSlot cardSlot = new CardSlot();
        cardSlot.setId(id);
        cardSlot.setSceneId(sceneId);
        List<CardSlotVo> cardSlotVos = selectForList(cardSlot);
        if (cardSlotVos == null || cardSlotVos.size() == 0) {
            LOGGER.error("[selectBySceneIdAndNumb error] sceneId: {}, numb: {} size == 0", sceneId, id);
            return null;
        }
        if (cardSlotVos.size() > 1) {
            LOGGER.error("[selectBySceneIdAndNumb error] sceneId: {}, numb: {} size > 1", sceneId, id);
        }
        return cardSlotVos.get(0);

    }

    public Page page(Page page, CardSlot cardSlot) {
        return cardSlotDao.page(page, cardSlot);
    }

    @Autowired
    public void setICardSlotDao(
            @Qualifier("cardSlotDao") ICardSlotDao cardSlotDao) {
        this.cardSlotDao = cardSlotDao;
    }


}
