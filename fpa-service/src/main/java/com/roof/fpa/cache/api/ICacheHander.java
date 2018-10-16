package com.roof.fpa.cache.api;

import org.roof.web.dictionary.entity.Dictionary;

import java.util.List;

/**
 * Created by zhenglt on 2017/12/4.
 */
public interface ICacheHander {
    Dictionary loadDictionaryById(Long id);

    Dictionary loadDictionaryByType(String type, String val);



}
