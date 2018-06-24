package com.roof.advisory.advisoryorder.service.impl;

import com.alibaba.fastjson.JSON;
import com.roof.advisory.OrderStatusEnum;
import com.roof.advisory.WechatRecordTypeEnum;
import com.roof.advisory.advisoryorder.entity.AdvisoryOrder;
import com.roof.advisory.advisoryorder.entity.AdvisoryOrderVo;
import com.roof.advisory.advisoryorder.service.api.IAdvisoryOrderService;
import com.roof.advisory.advisoryorderrecord.service.api.IAdvisoryOrderRecordService;
import com.roof.advisory.advisorypayrecord.entity.AdvisoryPayRecord;
import com.roof.advisory.advisorypayrecord.service.api.IAdvisoryPayRecordService;
import com.roof.advisory.advisoryproduct.entity.AdvisoryProductVo;
import com.roof.chain.api.ValueStack;
import com.roof.chain.support.NodeResult;
import com.roof.fpa.core.http.HttpClientUtil;
import com.roof.fpa.customer.entity.CustomerVo;
import org.roof.commons.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * com.roof.advisory.advisoryorder.service.impl
 *
 * @author liht
 * @date 2018/6/19
 */
public class WechatGetPrepayId {
    private static final Logger LOGGER = LoggerFactory.getLogger(WechatGetPrepayId.class);

    private String appid = PropertiesUtil.getPorpertyString("fpa.mini.appid");
    private String mchid = PropertiesUtil.getPorpertyString("wechat.pay.mch_id");
    private String notify_url = PropertiesUtil.getPorpertyString("wechat.pay.notify_url");
    private String messageservice_sendurl = PropertiesUtil.getPorpertyString("wechat.pay.unifiedorder.url");

    private IAdvisoryPayRecordService advisoryPayRecordService;

    private IAdvisoryOrderService advisoryOrderService;

    private IAdvisoryOrderRecordService advisoryOrderRecordService;

    public String doNode(String ip, AdvisoryOrder advisoryOrder, CustomerVo customerVo, AdvisoryProductVo advisoryProductVo, ValueStack valueStack) {
        SortedMap<Object, Object> packageP = new TreeMap<Object, Object>();
        //
        String times = System.currentTimeMillis() + "";

        SortedMap<Object, Object> param = new TreeMap<Object, Object>();
        param.put("appid", appid);
        param.put("body", advisoryProductVo.getName());
        param.put("mch_id", mchid);
        param.put("nonce_str", times);
        param.put("notify_url", notify_url);
        param.put("openid", customerVo.getWeixinOpenId());
        param.put("out_trade_no", advisoryOrder.getOrderNum());
        param.put("spbill_create_ip", ip);
        param.put("total_fee", advisoryOrder.getOrderAmount());
        param.put("trade_type", "JSAPI");

        String sign = PayCommonUtil.createSign("UTF-8", param);
        param.put("sign", sign);
        String requestXML = PayCommonUtil.getRequestXml(param);

        try {
            LOGGER.info("发送的文本的消息格式为：" + requestXML);
            String resXml = HttpClientUtil.post(messageservice_sendurl, requestXML);
            LOGGER.info("微信返回结果为：" + resXml);


            Map map = XMLUtil.doXMLParse(resXml);
            //System.out.println(map);

            //得到prepay_id
            String prepay_id = (String) map.get("prepay_id");

            packageP.put("appId", appid);//！！！注意，这里是appId,上面是appid，真怀疑写这个东西的人。。。
            packageP.put("nonceStr", times);//时间戳
            packageP.put("package", "prepay_id=" + prepay_id);//必须把package写成 "prepay_id="+prepay_id这种形式
            packageP.put("signType", "MD5");//paySign加密
            packageP.put("timeStamp", (System.currentTimeMillis() / 1000) + "");
            //得到paySign
            String paySign = PayCommonUtil.createSign("UTF-8", packageP);
            packageP.put("paySign", paySign);

            LOGGER.info(JSON.toJSONString(packageP));

            valueStack.set("packageP", packageP);


            //保存微信调用记录
            AdvisoryPayRecord advisoryPayRecord = new AdvisoryPayRecord();
            advisoryPayRecord.setFee(advisoryOrder.getOrderAmount());
            advisoryPayRecord.setOrderId(advisoryOrder.getId());
            advisoryPayRecord.setPlatform("wechat");
            advisoryPayRecord.setPrepayId(prepay_id);
            advisoryPayRecord.setRecordType(WechatRecordTypeEnum.getprepayid.getCode());
            advisoryPayRecord.setRequestData(requestXML);
            advisoryPayRecord.setResponseData(resXml);
            advisoryPayRecordService.save(advisoryPayRecord);

            //更新订单 保存订单变更记录
            AdvisoryOrder oldOrder = advisoryOrder;
            advisoryOrder.setOrderStatus(OrderStatusEnum.wechatPrepayed.getCode());
            advisoryOrderService.updateIgnoreNull(advisoryOrder);

            valueStack.set("advisoryOrder", advisoryOrder);

            advisoryOrderRecordService.saveOrderChange(oldOrder, advisoryOrder);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return NodeResult.SUCCESS;
    }


    public void setAdvisoryPayRecordService(IAdvisoryPayRecordService advisoryPayRecordService) {
        this.advisoryPayRecordService = advisoryPayRecordService;
    }

    public void setAdvisoryOrderService(IAdvisoryOrderService advisoryOrderService) {
        this.advisoryOrderService = advisoryOrderService;
    }

    public void setAdvisoryOrderRecordService(IAdvisoryOrderRecordService advisoryOrderRecordService) {
        this.advisoryOrderRecordService = advisoryOrderRecordService;
    }
}
