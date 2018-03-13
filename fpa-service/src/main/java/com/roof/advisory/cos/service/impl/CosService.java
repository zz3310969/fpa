package com.roof.advisory.cos.service.impl;

import com.google.common.collect.Maps;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.auth.COSSigner;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.utils.UrlEncoderUtils;
import com.roof.advisory.cos.service.api.ICosService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.roof.commons.RoofDateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class CosService implements ICosService {

    @Value("${tencent.ocs.appId}")
    String appid = "";
    @Value("${tencent.ocs.SecretId}")
    String secret_id = "";
    @Value("${tencent.ocs.SecretKey}")
    String secret_key = "";
    @Value("${tencent.ocs.region}")
    String region = "";
    @Value("${tencent.ocs.bucket}")
    String bucket = "";
    @Value("${tencent.ocs.pathPrefix}")
    private String pathPrefix;
    @Value("${tencent.ocs.cosUrl}")
    private String cosUrl;


    private Long times = 60L * 1000;

    @Override
    public Map<String,Object> getSign(Map<String,String> params) {
        Map<String,Object> map = Maps.newHashMap();
        COSCredentials cred = new BasicCOSCredentials( secret_id, secret_key);
        COSSigner cosSigner = new COSSigner();
        Date expiredTime =  RoofDateUtils.addHours(new Date(),2);
        HttpMethodName methodName = HttpMethodName.POST;
        if(StringUtils.isNoneBlank(params.get("Method"))){
            methodName = HttpMethodName.valueOf(params.get("Method"));
        }
        String resouce_path = "/";
        if(StringUtils.isNoneBlank(params.get("Key"))){
            resouce_path = UrlEncoderUtils.encode(params.get("Key")) ;
        }

        String sign = cosSigner.buildAuthorizationStr(methodName, resouce_path, cred, expiredTime);
        map.put("expiredTime",expiredTime.getTime() - times);
        map.put("sign",sign);
        return map;
    }

    @Override
    public Map<String, Object> getConfiguration() {
        Map<String,Object> map = Maps.newHashMap();
        map.put("bucket",bucket + "-" + appid);
        map.put("region",region);
        map.put("path", pathPrefix + "/" + DateFormatUtils.format(new Date(), "yyyyMM") + "/");
        map.put("cosUrl",cosUrl);
        return map;
    }
}
