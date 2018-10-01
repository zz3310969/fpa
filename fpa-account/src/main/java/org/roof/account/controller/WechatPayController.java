package org.roof.account.controller;

import org.apache.commons.lang3.time.DateUtils;
import org.jdom2.JDOMException;
import org.roof.account.event.WechatPayMessageEvent;
import org.roof.account.wechat.service.XMLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/")
public class WechatPayController implements ApplicationContextAware {




    private static final Logger logger = LoggerFactory.getLogger(WechatPayController.class);


    @RequestMapping(value = "wechatPayResponse")
    public @ResponseBody
    void wechatPayResponse(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        logger.info("微信支付回调");
        PrintWriter writer = response.getWriter();
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        String result = new String(outSteam.toByteArray(), "utf-8");
        logger.info("微信支付通知结果：" + result);
        Map<String, String> map = null;
        try {
            /**
             * 解析微信通知返回的信息
             */
            map = XMLUtil.doXMLParse(result);
        } catch (JDOMException e) {
            e.printStackTrace();
        }
        logger.info("=========:" + result);
        // 若支付成功，则告知微信服务器收到通知
        if (map.get("result_code").equals("SUCCESS")) {
            logger.info("充值成功！");
            map.get("out_trade_no");
            map.get("cash_fee");
            ApplicationEvent event = new WechatPayMessageEvent(this.applicationContext, map.get("out_trade_no"));

            applicationContext.publishEvent(event);

        }

        String notifyStr = XMLUtil.setXML("SUCCESS", "");
        writer.write(notifyStr);
        writer.flush();

    }

    private ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


}
