package com.roof.fpa.cache.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.roof.fpa.cache.api.ICacheHander;
import com.roof.fpa.cardunit.entity.CardUnit;
import com.roof.fpa.cardunit.entity.CardUnitVo;
import com.roof.fpa.cardunit.service.impl.CardUnitService;
import org.roof.web.dictionary.entity.Dictionary;
import org.roof.web.dictionary.service.impl.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhenglt on 2017/12/4.
 */
@Service("cacheHander")
public class CacheHanderImpl implements ICacheHander {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheHanderImpl.class);
    private Cache<Object, Object> dicCache = CacheFactory.getInstance().getDicCache();


    @Autowired
    private DictionaryService dictionaryService;

    @Override
    public Dictionary loadDictionaryById(Long id) {
        try {
            return (Dictionary)dicCache.get("Dictionary:" + id, new Callable<Dictionary>() {
                @Override
                public Dictionary call() throws Exception {
                    return dictionaryService.load(new Dictionary(id));
                }
            });
        } catch (ExecutionException e) {
            LOGGER.error(e.getMessage(),e);
        }
        return dictionaryService.load(new Dictionary(id));
    }

    @Override
    public Dictionary loadDictionaryByType(String type, String val) {
        try {
            return (Dictionary) dicCache.get("Dictionary_type:" + type+"_val:"+val, new Callable<Dictionary>() {
                @Override
                public Dictionary call() throws Exception {
                    return dictionaryService.load(type,val);
                }
            });
        } catch (ExecutionException e) {
            LOGGER.error(e.getMessage(),e);
        }
        return dictionaryService.load(type,val);
    }
}
