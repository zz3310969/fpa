package com.roof.fpa.cache.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.roof.fpa.cardslot.entity.CardSlot;
import com.roof.fpa.cardslot.entity.CardSlotVo;
import com.roof.fpa.cardunit.entity.CardUnitVo;
import com.roof.fpa.charactercolor.entity.CharacterColorVo;
import com.roof.fpa.scene.entity.SceneVo;
import org.roof.web.dictionary.entity.Dictionary;
import org.springframework.cache.CacheManager;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.support.SimpleCacheManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhenglt on 2017/12/5.
 */
public class CacheFactory {

    private static class SingletonHolder {
        private static final CacheFactory INSTANCE = new CacheFactory();
    }

    private CacheFactory() {
    }

    public static final CacheFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }


    /*private Cache<Object, Object> picCache = CacheBuilder.newBuilder()
            .maximumSize(50).expireAfterWrite(5, TimeUnit.MINUTES)
            .build();*/

    //private Cache<Object, Object> cardSlotVoCache = CacheBuilder.newBuilder().maximumSize(50).expireAfterWrite(5, TimeUnit.MINUTES).build();
    //private Cache<Object, Object> characterColorVoCache = CacheBuilder.newBuilder().maximumSize(10).expireAfterWrite(5, TimeUnit.MINUTES).build();
    //private Cache<Object, Object> sceneVoCache = CacheBuilder.newBuilder().maximumSize(5).expireAfterWrite(5, TimeUnit.MINUTES).build();
    //private Cache<Object, Object> cardUnitVoCache = CacheBuilder.newBuilder().maximumSize(50).expireAfterWrite(5, TimeUnit.MINUTES).build();
    private Cache<Object, Object> dicCache = CacheBuilder.newBuilder().maximumSize(50).expireAfterWrite(5, TimeUnit.MINUTES).build();







    public Cache<Object, Object> getDicCache() {
        return dicCache;
    }



}
