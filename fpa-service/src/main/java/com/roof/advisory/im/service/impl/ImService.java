package com.roof.advisory.im.service.impl;

import com.alibaba.fastjson.JSON;
import com.roof.advisory.consultant.service.api.IConsultantService;
import com.roof.advisory.im.service.ImRequest;
import com.roof.advisory.im.service.ImResponse;
import com.roof.advisory.im.service.api.IImService;
import com.roof.fpa.core.http.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Service
public class ImService implements IImService {

    private static final Logger logger = LoggerFactory.getLogger(ImService.class);

    @Autowired
    private IConsultantService consultantService;

    @Value("${im.baseUrl}")
    private String imBaseUrl;

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
            String str = HttpClientUtil.get(imBaseUrl + "/user/queryOnline");
            logger.info(str);
            ImResponse response = JSON.parseObject(str, ImResponse.class);
            if (!StringUtils.equals(response.getState(), "success")) {
                logger.error("getAllUsers出错:", response.getMessage());
            } else {
                logger.info(str);
//                String[] usernames = response.getMessage();
//                consultantService.selectForListByUsernames(usernames);
            }
        } catch (Exception e) {
            logger.error("getAllUsers出错:", e.getCause());
        }
    }
}
