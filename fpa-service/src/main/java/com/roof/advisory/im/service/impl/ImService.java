package com.roof.advisory.im.service.impl;

import com.alibaba.fastjson.JSON;
import com.roof.advisory.im.service.ImRequest;
import com.roof.advisory.im.service.ImResponse;
import com.roof.advisory.im.service.api.IImService;
import com.roof.fpa.core.http.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Service
public class ImService implements IImService {

    private static final Logger logger = LoggerFactory.getLogger(ImService.class);

    @Value("${im.baseUrl}")
    private String imBaseUrl;
    @Override
    public void openSession(ImRequest imRequest) {
        imRequest.setSeq(new Date().getTime()+"");
        try {
            String str = HttpClientUtil.post(imBaseUrl+"session/open", JSON.toJSONString(imRequest));
            ImResponse response = JSON.parseObject(str, ImResponse.class);
            if(!StringUtils.equals(response.getState(),"success")){
                logger.error("openSession出错:",response.getMessage());
            }else {
                Map map =  response.getResult();
                map.get("id");
            }
            logger.info(str);
        } catch (IOException e) {
            logger.error("openSession出错:",e.getCause());
        }

    }

    @Override
    public void closeSession(ImRequest imRequest) {
        imRequest.setSeq(new Date().getTime()+"");
        try {
            String str =  HttpClientUtil.post(imBaseUrl+"session/open",JSON.toJSONString(imRequest));
            ImResponse response = JSON.parseObject(str, ImResponse.class);
            if(!StringUtils.equals(response.getState(),"success")){
                logger.error("closeSession出错:",response.getMessage());
            }else {

            }
        } catch (IOException e) {
            logger.error("closeSession出错:",e.getCause());
        }

    }
}
