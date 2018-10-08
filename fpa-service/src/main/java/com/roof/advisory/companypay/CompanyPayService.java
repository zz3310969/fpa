package com.roof.advisory.companypay;


import com.jpay.ext.kit.IpKit;
import com.jpay.ext.kit.PaymentKit;
import com.jpay.ext.kit.StrKit;
import com.jpay.weixin.api.WxPayApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * com.roof.advisory.companypay
 *
 * @author liht
 * @date 2018/10/1
 */
public class CompanyPayService {

    private static final Logger log = LoggerFactory.getLogger(CompanyPayService.class);

    public static final String TRANSFERS_URL = "";
    public static final String GETTRANSFERINFO_URL = "";

    /**
     * 企业付款到零钱
     */
    public String transfers(String openId, String appid, String mch_id, String ip, String certPath, String partnerKey) {
//        String openId = getSessionAttr("openId");
        Map<String, String> params = new HashMap<String, String>();
        params.put("mch_appid", appid);
        params.put("mchid", mch_id);
        String nonceStr = String.valueOf(System.currentTimeMillis());
        params.put("nonce_str", nonceStr);
        String partnerTradeNo = String.valueOf(System.currentTimeMillis());
        params.put("partner_trade_no", partnerTradeNo);
        params.put("openid", openId);
        params.put("check_name", "NO_CHECK");
        params.put("amount", "100");
        params.put("desc", "IJPay提现测试-By Javen");
//        String ip = IpKit.getRealIp(getRequest());
        if (StrKit.isBlank(ip)) {
            ip = "127.0.0.1";
        }
        params.put("spbill_create_ip", ip);

        params.put("sign", PaymentKit.createSign(params, partnerKey));
        System.out.println("certPath>" + certPath);
        // 提现
        String transfers = WxPayApi.transfers(params, certPath, mch_id);
        log.info("提现结果:" + transfers);
        System.out.println("提现结果:" + transfers);
        Map<String, String> map = PaymentKit.xmlToMap(transfers);
        String return_code = map.get("return_code");
        String result_code = null;
        if (("SUCCESS").equals(return_code)) {
            result_code = map.get("result_code");
            if (("SUCCESS").equals(result_code)) {
                //提现成功
            } else {
                //提现失败
            }
        }
        return transfers;
    }

    /**
     * 查询企业付款到零钱
     */
    public String transferInfo(String partner_trade_no, String mch_id, String appid, String partnerKey, String certPath) {
        try {
//            String partner_trade_no = getPara("partner_trade_no");
            Map<String, String> params = new HashMap<String, String>();
            params.put("nonce_str", System.currentTimeMillis() + "");
            params.put("partner_trade_no", partner_trade_no);
            params.put("mch_id", mch_id);
            params.put("appid", appid);
            params.put("sign", PaymentKit.createSign(params, partnerKey));
            String transferInfo = WxPayApi.getTransferInfo(params, certPath, mch_id);
            return transferInfo;
//            renderText(transferInfo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return e.getMessage();
//            renderText(e.getMessage());
        }
    }

}
