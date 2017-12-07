package com.roof.fpa.charactercolor.service.impl;

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
import com.roof.fpa.DefaultUseableEnum;
import com.roof.fpa.cache.impl.CacheFactory;
import com.roof.fpa.cardunit.entity.CardUnit;
import com.roof.fpa.cardunit.entity.CardUnitVo;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.charactercolor.dao.api.ICharacterColorDao;
import com.roof.fpa.charactercolor.entity.CharacterColor;
import com.roof.fpa.charactercolor.entity.CharacterColorVo;
import com.roof.fpa.charactercolor.service.api.ICharacterColorService;
import org.roof.spring.ApplicationException;
import org.roof.web.dictionary.entity.Dictionary;
import org.roof.web.dictionary.service.api.IDictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CharacterColorService implements ICharacterColorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CharacterColorService.class);
    //private Cache<Object, Object> cache = CacheFactory.getInstance().getCharacterColorVoCache();

    @Autowired
    private ListeningExecutorService listeningExecutorService;
    private LoadingCache<Long, CharacterColorVo> cache = CacheBuilder.newBuilder()
            .maximumSize(10)
            .refreshAfterWrite(5, TimeUnit.MINUTES)
            .build(new CacheLoader<Long, CharacterColorVo>() {
                @Override
                public CharacterColorVo load(Long id) throws Exception {
                    return selectByColorId(id);
                }

                @Override
                public ListenableFuture<CharacterColorVo> reload(Long key, CharacterColorVo oldValue) throws Exception {
                    return listeningExecutorService.submit(new Callable<CharacterColorVo>() {
                        @Override
                        public CharacterColorVo call() throws Exception {
                            return selectByColorId(key);
                        }
                    });
                }
            });


    private ICharacterColorDao characterColorDao;

    @Autowired
    private IDictionaryService dictionaryService;

    public Serializable saveVo(CharacterColorVo characterColorVo) throws ApplicationException {
        if (characterColorVo.getColorIds() == null) {
            throw ApplicationException.newInstance(ApplicationException.DEFAULT_EXCEPTION_CODE);
        }
        CharacterColor characterColor = new CharacterColor();
        copyProperties(characterColorVo, characterColor);
        return this.save(characterColor);
    }

    private void copyProperties(CharacterColorVo characterColorVo, CharacterColor characterColor) {
        BeanUtils.copyProperties(characterColorVo, characterColor);
        characterColor.setUseable(DefaultUseableEnum.usable.getCode());
        if (characterColorVo.getColorIds().size() == 1) {
            characterColor.setColorId(characterColorVo.getColorIds().get(0));
            characterColor.setColorCode(dictionaryService.load(new Dictionary(characterColorVo.getColorIds().get(0))).getDescription());
            characterColor.setColor2Id(null);
            characterColor.setColor2Code(null);
        } else if (characterColorVo.getColorIds().size() == 2) {
            characterColor.setColorId(characterColorVo.getColorIds().get(0));
            characterColor.setColorCode(dictionaryService.load(new Dictionary(characterColorVo.getColorIds().get(0))).getDescription());
            characterColor.setColor2Id(characterColorVo.getColorIds().get(1));
            characterColor.setColor2Code(dictionaryService.load(new Dictionary(characterColorVo.getColorIds().get(1))).getDescription());
        }
    }

    public Serializable save(CharacterColor characterColor) {
        characterColor.setUseable(DefaultUseableEnum.usable.getCode());
        return characterColorDao.save(characterColor);
    }

    public void delete(CharacterColor characterColor) {
        characterColorDao.delete(characterColor);
    }

    public void deleteByExample(CharacterColor characterColor) {
        characterColorDao.deleteByExample(characterColor);
    }

    public void update(CharacterColor characterColor) {
        characterColorDao.update(characterColor);
    }

    public void updateIgnoreNullVo(CharacterColorVo characterColorVo) throws ApplicationException {
        if (characterColorVo.getColorIds() == null) {
            throw ApplicationException.newInstance(ApplicationException.DEFAULT_EXCEPTION_CODE);
        }
        CharacterColor characterColor = new CharacterColor();
        characterColor.setId(characterColorVo.getId());
        copyProperties(characterColorVo, characterColor);
        characterColorDao.update(characterColor);
    }


    public void updateIgnoreNull(CharacterColor characterColor) {
        characterColorDao.updateIgnoreNull(characterColor);
    }

    public void updateByExample(CharacterColor characterColor) {
        characterColorDao.update("updateByExampleCharacterColor", characterColor);
    }

    public CharacterColorVo load(CharacterColor characterColor) {
        return (CharacterColorVo) characterColorDao.reload(characterColor);
    }

    public CharacterColorVo selectForObject(CharacterColor characterColor) {
        return (CharacterColorVo) characterColorDao.selectForObject("selectCharacterColor", characterColor);
    }

    public List<CharacterColorVo> selectForList(CharacterColor characterColor) {
        return (List<CharacterColorVo>) characterColorDao.selectForList("selectCharacterColor", characterColor);
    }

    @Override
    public CharacterColorVo selectByColorId(Long colorId) {
        //TODO add Cache
        CharacterColor characterColor = new CharacterColor();
        characterColor.setColorId(colorId);
        List<CharacterColorVo> characterColorVos = selectForList(characterColor);
        if (characterColorVos == null || characterColorVos.size() == 0) {
            LOGGER.error("[selectByColorId ] error colorId: {}, size == 0", colorId);
            return null;
        }
        if (characterColorVos.size() > 1) {
            LOGGER.error("[selectByColorId ] error colorId: {}, size > 1", colorId);
        }
        return characterColorVos.get(0);
    }

    public Page page(Page page, CharacterColor characterColor) {
        return characterColorDao.page(page, characterColor);
    }

    @Override
    public CharacterColorVo selectByColorIdByCache(Long colorId) {
        try {
            return cache.get(colorId);
        } catch (ExecutionException e) {
            LOGGER.error(e.getMessage(),e);
        }
        return selectByColorId(colorId);
    }

    @Autowired
    public void setICharacterColorDao(
            @Qualifier("characterColorDao") ICharacterColorDao characterColorDao) {
        this.characterColorDao = characterColorDao;
    }

}
