package com.roof.fpa.weixin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.roof.fpa.core.http.HttpClientUtil;
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
public class WeChatHander {
    private Logger LOGGER = LoggerFactory.getLogger(WeChatHander.class);

    private RedisTemplate<String,String> redisTemplate;

    private String appid = PropertiesUtil.getPorpertyString("fpa.wx.appid");
    private String secret = PropertiesUtil.getPorpertyString("fpa.wx.secret");
    private String token_url = PropertiesUtil.getPorpertyString("fpa.wx.access_token_url");
    private String html_token_url = PropertiesUtil.getPorpertyString("fpa.wx.html_access_token_url");
    private String grant_type = PropertiesUtil.getPorpertyString("fpa.wx.grant_type");

    private String redis_key = "wechat:token";




    /**
     * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
     */
    public String post(String url, String code) throws IOException {
        Map<String, Object> postData = new HashMap<String, Object>();
        postData.put("appid", appid);
        postData.put("secret", secret);
        postData.put("code", code);
        postData.put("grant_type", grant_type);
        return HttpClientUtil.post(url, postData);
    }

    /**
     * 获得微信用户的openid
     *
     * @param code
     * @return
     */
    public String getOpenid(String code) throws IOException {
        String body = this.post(html_token_url, code);
        JSONObject obj = JSON.parseObject(body);
        String openid = null;
        if (obj.containsKey("openid")) {
            openid = obj.getString("openid");
        }
        return openid;
    }

}
