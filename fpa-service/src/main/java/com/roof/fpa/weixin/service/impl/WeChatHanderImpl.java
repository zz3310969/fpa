package com.roof.fpa.weixin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.roof.fpa.core.http.HttpClientUtil;
import com.roof.fpa.wechat.bean.WeChatToken;
import com.roof.fpa.weixin.service.api.IWeChatHander;
import org.apache.commons.lang3.StringUtils;
import org.roof.commons.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private String appid = PropertiesUtil.getPorpertyString("fpa.mini.appid");
    private String secret = PropertiesUtil.getPorpertyString("fpa.mini.secret");
    private String html_token_url = PropertiesUtil.getPorpertyString("fpa.mini.openid.url");
    private String grant_type = PropertiesUtil.getPorpertyString("fpa.mini.grant_type");
    private String token_url = PropertiesUtil.getPorpertyString("fpa.mini.access_token_url");
    private String redis_key = "wechat:token";
    private String wxacodeunlimit = PropertiesUtil.getPorpertyString("fpa.mini.getwxacodeunlimit");


    public void getacode() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> map = new HashMap<String, Object>();
        String param = "{scene: 'zlt',page: 'pages/index/index'}";
        map.put("data", param);
//        rest.postForEntity(wxacodeunlimit+"?access_token="+getAccess_token(),byte[].class,);

        //该方法通过restTemplate请求远程restfulAPI
        HttpHeaders headers = new HttpHeaders();
//        headers.set("auth_token", "asdfgh");
//        headers.set("Other-Header", "othervalue");
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject parm = new JSONObject();
        parm.put("data", param);
        HttpEntity<JSONObject> entity = new HttpEntity<JSONObject>(parm, headers);
        HttpEntity<String> response = restTemplate.exchange(
                wxacodeunlimit + "?access_token=" + getAccess_token(), HttpMethod.POST, entity,String.class);

    }


    public String getAccess_tokenByHttp() throws Exception {
        String s = HttpClientUtil.get(token_url + "&appid=" + appid + "&secret=" + secret);
        WeChatToken token = JSON.parseObject(s, WeChatToken.class);
        if (token == null) {
            LOGGER.error("获取微信token失败");
            throw new Exception("获取微信token失败");
        } else {
            BoundValueOperations redis = redisTemplate.boundValueOps(redis_key);
            redis.set(token.getAccess_token());
            redis.expire(token.getExpires_in(), TimeUnit.SECONDS);
        }
        return token.getAccess_token();
    }

    ;

    public String getAccess_token() throws Exception {
        BoundValueOperations<String, String> redis = redisTemplate.boundValueOps(redis_key);
        String s = redis.get();
        if (StringUtils.isNotBlank(s)) {
            return s;
        } else {
            return getAccess_tokenByHttp();
        }
    }

    ;


    public String getOpenid(String url, String code) throws IOException {
        StringBuilder str = new StringBuilder(url);
        str.append("?appid=" + appid);
        str.append("&secret=" + secret);
        str.append("&js_code=" + code);
        str.append("&grant_type=" + grant_type);
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
