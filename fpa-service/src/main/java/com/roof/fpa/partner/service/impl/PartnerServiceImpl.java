package com.roof.fpa.partner.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.roof.fpa.core.http.HttpClientUtil;
import com.roof.fpa.customer.entity.Customer;
import com.roof.fpa.customer.service.api.ICustomerService;
import com.roof.fpa.partner.service.api.IPartnerService;
import org.apache.commons.codec.digest.DigestUtils;
import org.roof.commons.PropertiesUtil;
import org.roof.spring.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by zhenglt on 2018/1/6.
 */
@Service
public class PartnerServiceImpl implements IPartnerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartnerServiceImpl.class);

    private String appSecret = PropertiesUtil.getPorpertyString("fpa.xiaoe.appSecret");
    private String appid = PropertiesUtil.getPorpertyString("fpa.xiaoe.appId");

    private String xiaoe_bind_url = PropertiesUtil.getPorpertyString("fpa.xiaoe.bind.url");
    private String xiaoe_query_url = PropertiesUtil.getPorpertyString("fpa.xiaoe.query.url");

    @Autowired
    private ICustomerService customerService;

    @Override
    public Boolean bind(String unionid,Long customerId) {

        Customer partner = customerService.loadByUnionid(unionid);
        Customer customer = customerService.load(new Customer(customerId));

        if(partner == null && partner.getUnionid() == null){
            return false;
        }

        if(customer == null && customer.getUnionid() == null){
            return false;
        }

        Map<String,Object> map = Maps.newHashMap();
        map.put("shared_user_info",copy(partner));
        map.put("customer_user_info",copy(customer));
        map.put("expire_at","1577808000");//秒

        TreeMap<String,Object> map2= new TreeMap<String,Object>(new Comparator<String>(){
            public int compare(String a,String b){
                return a.compareTo(b);
            }
        });

        map2.put("app_id",appid);
        map2.put("timestamp",new Date().getTime() /1000);
        map2.put("use_type",0);
        map2.put("data",map);



        map2.put("sign",getSign(map2));


        try {
            String result = HttpClientUtil.post(xiaoe_bind_url, JSON.toJSONString(map2));
            JSONObject obj = JSON.parseObject(result);
            if(obj.containsKey("code") && "0".equals(obj.getString("code"))){
                LOGGER.info("绑定合伙人成功:");
            }else{
                LOGGER.error("绑定合伙人失败:",result);
            }
        } catch (IOException e) {
            //e.printStackTrace();
            LOGGER.error("绑定合伙人出错:",e);
        }

        return false;
    }

    public String getSign(TreeMap<String, Object> data) {
        String str = "";
        for (String key : data.keySet()) {
            if (!(str == "")) {
                str += '&';
            }
            try {
                Object value = data.get(key);
                String vstr;
                if (!(value instanceof String)) {
                    vstr = JSON.toJSONString(value);
                } else {
                    vstr = value.toString();
                }
                str += key + '='
                        + vstr;

            } catch (Exception e) {
                return "";
            }
        }
        str += "&app_secret=" + this.appSecret;
        String sign = "";
        LOGGER.debug(str);
        sign = DigestUtils.md5Hex(str);
        //sign = MD5.stringToMD5(str);
        LOGGER.debug(sign);
        return sign;
    }

    private XiaoeUserInfo copy(Customer customer){
        XiaoeUserInfo xiaoeUserInfo = new XiaoeUserInfo();
        xiaoeUserInfo.setWx_union_id(customer.getUnionid());
        xiaoeUserInfo.setGender(customer.getGender());
        xiaoeUserInfo.setNick_name(xiaoeUserInfo.getNick_name());
        return  xiaoeUserInfo;

    }

    @Override
    public Boolean isPartner(String unionid) {
        String str = HttpClientUtil.get(xiaoe_query_url+"?unionid="+unionid);
        Result result = JSON.parseObject(str,Result.class);
        if(result != null){
            if(result.getData() != null){
                return true;
            }
        }
        return false;
    }
}
