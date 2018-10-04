package com.roof.advisory.companypay;


import com.jpay.weixin.api.WxPayApi;

import java.util.Map;

/**
 * com.roof.advisory.companypay
 *
 * @author liht
 * @date 2018/10/1
 */
public class CompanyPayService {
    public static final String TRANSFERS_URL = "";
    public static final String GETTRANSFERINFO_URL = "";

    /**
     * 企业付款到零钱
     *
     * @param params       请求参数
     * @param certPath     证书文件目录
     * @param certPassword 证书密码
     * @return {String}
     */
    public static String transfers(Map<String, String> params, String certPath, String certPassword) {
        return WxPayApi.doPostSSL(TRANSFERS_URL, params, certPath, certPassword);
    }


    /**
     * 查询企业付款到零钱
     *
     * @param params       请求参数
     * @param certPath     证书文件目录
     * @param certPassword 证书密码
     * @return {String}
     */
    public static String getTransferInfo(Map<String, String> params, String certPath, String certPassword) {
        return WxPayApi.doPostSSL(GETTRANSFERINFO_URL, params, certPath, certPassword);
    }

}
