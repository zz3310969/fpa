package com.roof.advisory.im.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.roof.advisory.consultant.service.api.IConsultantService;
import com.roof.advisory.im.service.ImRequest;
import com.roof.advisory.im.service.ImResponse;
import com.roof.advisory.im.service.api.IImService;
import com.roof.advisory.wxsession.service.api.IWxSessionService;
import com.roof.fpa.core.http.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImService implements IImService {

    private static final Logger logger = LoggerFactory.getLogger(ImService.class);

    @Autowired
    private IConsultantService consultantService;

    @Value("${im.baseUrl}")
    private String imBaseUrl;

    public static final String ONLINE_USERS = "online_users";

    @Autowired
    private IWxSessionService wxSessionService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Long openSession(ImRequest imRequest) {
        Long sessionId = null;
        imRequest.setSeq(System.currentTimeMillis() + "");
        try {
            String str = HttpClientUtil.post(imBaseUrl + "session/open", JSON.toJSONString(imRequest));
            ImResponse response = JSON.parseObject(str, ImResponse.class);
            if (!StringUtils.equals(response.getState(), "success")) {
                logger.error("openSession出错:", response.getMessage());
            } else {
                Map map = response.getResult();
                sessionId = Long.valueOf(map.get("id").toString());
            }
            logger.info(str);
        } catch (IOException e) {
            logger.error("openSession出错:", e.getCause());
        }
        return sessionId;
    }

    @Override
    public void closeSession(ImRequest imRequest) {
        imRequest.setSeq(System.currentTimeMillis() + "");
        try {
            String str = HttpClientUtil.post(imBaseUrl + "session/close", JSON.toJSONString(imRequest));
            ImResponse response = JSON.parseObject(str, ImResponse.class);
            if (!StringUtils.equals(response.getState(), "success")) {
                logger.error("closeSession出错:", response.getMessage());
            } else {

            }
        } catch (IOException e) {
            logger.error("closeSession出错:", e.getCause());
        }

    }

    @Async
    @Override
    public void getAllUsers() {
        try {
            Map map = new HashMap<String, Object>();
            map.put("requestType", "queryOnline");
            map.put("token", "");
            map.put("users", consultantService.selectConsultantUsernames());

            String str = HttpClientUtil.post(imBaseUrl + "/user/queryOnline", JSON.toJSONString(map));
            logger.info(str);
            JSONObject response = JSON.parseObject(str);
            if (!StringUtils.equals(response.get("state").toString(), "success")) {
                logger.error("getAllUsers出错:");
            } else {
                JSONArray array = response.getJSONArray("result");
                List<String> list = array.toJavaList(String.class);
                BoundValueOperations<String, List<String>> operations = redisTemplate.boundValueOps(ImService.ONLINE_USERS);
                operations.set(list);
            }
        } catch (Exception e) {
            logger.error("getAllUsers出错:", e.getCause());
        }
    }
}
