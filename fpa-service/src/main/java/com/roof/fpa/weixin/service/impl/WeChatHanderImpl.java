package com.roof.fpa.weixin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.roof.fpa.core.http.HttpClientUtil;
import com.roof.fpa.weixin.service.api.IWeChatHander;
import org.apache.commons.lang3.StringUtils;
import org.roof.commons.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by lenovo on 2017/8/17.
 */
@Service
public class WeChatHanderImpl implements IWeChatHander {
    private Logger LOGGER = LoggerFactory.getLogger(WeChatHanderImpl.class);

    private RedisTemplate<String,String> redisTemplate;

    private String appid = PropertiesUtil.getPorpertyString("fpa.mini.appid");
    private String secret = PropertiesUtil.getPorpertyString("fpa.mini.secret");
    private String html_token_url = PropertiesUtil.getPorpertyString("fpa.mini.openid.url");
    private String grant_type = PropertiesUtil.getPorpertyString("fpa.mini.grant_type");

    private String redis_key = "wechat:token";






    public String getOpenid(String url, String code) throws IOException {
        StringBuilder str = new StringBuilder(url);
        str.append("?appid="+appid);
        str.append("&secret="+secret);
        str.append("&js_code="+code);
        str.append("&grant_type="+grant_type);
        return HttpClientUtil.get(str.toString());
    }

    /**
     * 获得微信用户的openid
     *
     * @param code
     * @return
     */
    public String getOpenid(String code) throws IOException {
        String body = this.getOpenid(html_token_url, code);
        JSONObject obj = JSON.parseObject(body);
        String openid = null;
        if (obj.containsKey("openid")) {
            openid = obj.getString("openid");
        }
        return openid;
    }

}
