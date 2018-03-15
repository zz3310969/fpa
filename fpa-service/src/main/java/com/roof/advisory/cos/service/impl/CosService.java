package com.roof.advisory.cos.service.impl;

import com.google.common.collect.Maps;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.auth.COSSigner;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.utils.UrlEncoderUtils;
import com.roof.advisory.cos.service.api.ICosService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.roof.commons.RoofDateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
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

    @Value("${tencent.ocs.head.bucket}")
    private String headBucket;
    @Value("${tencent.ocs.head.cosUrl}")
    private String headCosUrl;


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

    @Override
    public String uploadHeadImg(String key, File file) {
        String  path = "head" + "/" + DateFormatUtils.format(new Date(), "yyyyMM") + "/"+key;

        // 设置秘钥
        COSCredentials cred = new BasicCOSCredentials(appid, secret_id, secret_key);
        // 设置区域, 这里设置为华北
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        // 生成 cos 客户端对象
        COSClient cosClient = new COSClient(cred, clientConfig);

        String bucketName = headBucket+"-"+appid;
        // 上传 object, 建议 20M 以下的文件使用该接口
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, path, file);

        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        //System.out.println(putObjectResult);

        // 关闭客户端 (关闭后台线程)

        cosClient.shutdown();
        return headCosUrl+path;
    }
}
