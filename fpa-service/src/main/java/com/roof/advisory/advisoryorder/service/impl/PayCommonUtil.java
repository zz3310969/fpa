package com.roof.advisory.advisoryorder.service.impl;

import org.roof.commons.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.*;

/**
 * com.roof.coupon.order.impl
 *
 * @author liht
 * @date 2018/6/11
 */
public class PayCommonUtil {

    private static final Logger logger = LoggerFactory.getLogger(PayCommonUtil.class);

    private static String paykey = PropertiesUtil.getPorpertyString("wechat.pay.paykey");


    /**
     * 定义签名，微信根据参数字段的ASCII码值进行排序 加密签名,故使用SortMap进行参数排序
     *
     * @param characterEncoding "UTF-8"
     * @param parameters
     * @return
     */
    public static String createSign(String
                                            characterEncoding, SortedMap<Object, Object> parameters
    ) {
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }

        sb.append("key=" + paykey);
        logger.info("字符串:" + sb.toString());
        String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
        return sign;
    }


    /**
     * @param parameters 请求参数
     * @return
     * @author
     * @Description：将请求参数转换为xml格式的string
     */
    public static String getRequestXml(SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = entry.getKey().toString();
            String v = entry.getValue().toString();
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
            } else {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }
}
