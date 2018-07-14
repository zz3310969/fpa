package com.roof.advisory.wechat.customer.controller;

import com.roof.advisory.OrderStatusEnum;
import com.roof.advisory.WechatRecordTypeEnum;
import com.roof.advisory.advisoryorder.entity.AdvisoryOrder;
import com.roof.advisory.advisoryorder.entity.AdvisoryOrderVo;
import com.roof.advisory.advisoryorder.service.api.IAdvisoryOrderService;
import com.roof.advisory.advisoryorder.service.impl.XMLUtil;
import com.roof.advisory.advisoryorderrecord.service.api.IAdvisoryOrderRecordService;
import com.roof.advisory.advisorypayrecord.entity.AdvisoryPayRecord;
import com.roof.advisory.advisorypayrecord.service.api.IAdvisoryPayRecordService;
import com.roof.advisory.advisoryproduct.service.api.IAdvisoryProductService;
import com.roof.advisory.consultant.entity.Consultant;
import com.roof.advisory.consultant.entity.ConsultantVo;
import com.roof.advisory.consultant.service.api.IConsultantService;
import com.roof.advisory.im.service.ImRequest;
import com.roof.advisory.im.service.api.IImService;
import com.roof.chain.api.Chain;
import com.roof.chain.api.ValueStack;
import com.roof.chain.support.GenericValueStack;
import com.roof.fpa.customer.entity.CustomerVo;
import com.roof.fpa.customer.service.api.ICustomerService;
import com.roof.fpa.order.entity.OrderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jdom2.JDOMException;
import org.roof.spring.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;

/**
 * com.roof.advisory.wechat.customer.controller
 *
 * @author liht
 * @date 2018/3/13
 */
@Api(value = "customerAdvisory", description = "顾客咨询管理")
@Controller
@RequestMapping("fpa/wechat/advisory")
public class CustomerAdvisoryController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerAdvisoryController.class);

    @Autowired
    private IImService imService;

    @Autowired
    private IConsultantService consultantService;

    @Autowired
    private IAdvisoryOrderService advisoryOrderService;

    @Autowired
    private IAdvisoryOrderRecordService advisoryOrderRecordService;

    @Autowired
    private IAdvisoryPayRecordService advisoryPayRecordService;

    @Autowired
    private IAdvisoryProductService advisoryProductService;

    @Autowired
    private ICustomerService customerService;

    protected Chain chatOrderCreateChain;

    /**
     * @param advisoryOrderVo
     * @param request
     * @return
     * @apiNote 订单续时必须包含 一个产品，这个产品针对续时几分钟，如果时公共产品，必须带着咨询师是谁，这样才可以找到，客户和咨询师之间未完成订单。
     */
    @ApiOperation(value = "订单续时")
    @RequestMapping(value = "customer/orderExtendTime", method = {RequestMethod.POST})
    protected @ResponseBody
    Result orderExtendTime(AdvisoryOrderVo advisoryOrderVo, HttpServletRequest request) {
        ValueStack valueStack = new GenericValueStack();
        try {
            String addr = InetAddress.getLocalHost().getHostAddress();
            valueStack.set("ip", addr);
            valueStack.set("hasLink", "extend");
            valueStack.set("advisoryOrderVo", advisoryOrderVo);
            chatOrderCreateChain.doChain(valueStack);

            SortedMap<Object, Object> packageP = (SortedMap<Object, Object>) valueStack.get("packageP");
            return new Result(Result.SUCCESS, packageP);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result(Result.FAIL, e.getMessage());
        }
    }


    @ApiOperation(value = "创建订单")
    @RequestMapping(value = "customer/createOrder", method = {RequestMethod.GET})
    protected @ResponseBody
    Result createOrder(AdvisoryOrderVo advisoryOrderVo, HttpServletRequest request) {
        ValueStack valueStack = new GenericValueStack();
        try {
            String addr = InetAddress.getLocalHost().getHostAddress();
            valueStack.set("ip", addr);
            valueStack.set("advisoryOrderVo", advisoryOrderVo);
            chatOrderCreateChain.doChain(valueStack);

            SortedMap<Object, Object> packageP = (SortedMap<Object, Object>) valueStack.get("packageP");
            return new Result(Result.SUCCESS, packageP);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result(Result.FAIL, e.getMessage());
        }
    }

    @ApiOperation(value = "微信支付回调")
    @RequestMapping(value = "wechatPayResponse")
    public @ResponseBody
    void wechatPayResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
            //更新订单？
            AdvisoryOrderVo advisoryOrderVo = advisoryOrderService.loadByOrdernum(map.get("out_trade_no"));
            if (advisoryOrderVo.getPayTime() == null) {
                //更新订单
                AdvisoryOrder order = new AdvisoryOrder();
                BeanUtils.copyProperties(advisoryOrderVo, order);
                AdvisoryOrder oldOrder = order;
                order.setPayTime(new Date());
                order.setOrderStatus(OrderStatusEnum.payed.getCode());
                order.setPayAmount(map.get("cash_fee") == null ? 0 : Integer.valueOf(map.get("cash_fee")));
                advisoryOrderService.updateIgnoreNull(order);
                //记录订单变更记录
                advisoryOrderRecordService.saveOrderChange(oldOrder, order);
                //保存微信调用记录
                AdvisoryPayRecord advisoryPayRecord = new AdvisoryPayRecord();
                advisoryPayRecord.setFee(order.getPayAmount());
                advisoryPayRecord.setOrderId(order.getId());
                advisoryPayRecord.setPlatform("wechat");
                advisoryPayRecord.setRecordType(WechatRecordTypeEnum.wechatNoticePayded.getCode());
                advisoryPayRecord.setRequestData(result);
                advisoryPayRecord.setResponseData("success");
                advisoryPayRecordService.save(advisoryPayRecord);
                //同步发送系统消息至im
                advisoryOrderService.sendSystemMessage(order);
            }

            String notifyStr = XMLUtil.setXML("SUCCESS", "");
            writer.write(notifyStr);
            writer.flush();
        }
    }


    @ApiOperation(value = "客户咨询初始化")
    @RequestMapping(value = "customer/openseesion", method = {RequestMethod.POST})
    public @ResponseBody
    Result list(@RequestBody Map map, HttpServletRequest request) {
        String token = (String) map.get("token");
        String weixinOpenId = (String) map.get("weixinOpenId");
        Long consultantId = Long.valueOf(map.get("consultantId") + "");

        ConsultantVo consultantVo = consultantService.load(new Consultant(consultantId));
        CustomerVo customerVo = customerService.loadByOpenid(weixinOpenId);
        //开始远程调用openseesion
        ImRequest imRequest = new ImRequest();
        imRequest.setToken(token);
        imRequest.setSender(weixinOpenId);
        imRequest.setReceiver(consultantVo.getUsername());
        imRequest.setStartTime(System.currentTimeMillis());
        imRequest.setRequestType(ImRequest.IM_OPENSESSION);
        imRequest.setEndTime(System.currentTimeMillis() + 1000 * 60 * 60 * 12);
        Long sessionId = imService.openSession(imRequest);
        //seesion已经存在
        if (sessionId == null) {
            return new Result(Result.SUCCESS);
        }
        //创建订单,绑定sessionId
        AdvisoryOrder order = new AdvisoryOrder();
        order.setSessionId(sessionId);
        order.setCustomId(customerVo.getId());
        order.setOrderNum(advisoryOrderService.createOrderNum(new Date()));
        order.setProductId((Long) advisoryProductService.findAndCreate(consultantId));
        order.setState(1);
        order.setOrderStatus(OrderStatusEnum.submitted.getCode());
        order.setOrderTime(new Date());
        advisoryOrderService.save(order);

        return new Result(Result.SUCCESS);
    }

    @Autowired
    public void setChatOrderCreateChain(@Qualifier("chatOrderCreateChain") Chain chatOrderCreateChain) {
        this.chatOrderCreateChain = chatOrderCreateChain;
    }
}
