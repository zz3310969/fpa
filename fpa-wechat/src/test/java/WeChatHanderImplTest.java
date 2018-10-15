import com.alibaba.fastjson.JSON;
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
import com.roof.advisory.consultant.service.api.IConsultantService;
import com.roof.advisory.im.service.api.IImService;
import com.roof.chain.api.Chain;
import com.roof.chain.api.ValueStack;
import com.roof.chain.support.GenericValueStack;
import com.roof.fpa.cache.api.ICacheHander;
import com.roof.fpa.customer.service.api.ICustomerService;
import com.roof.fpa.weixin.service.api.IWeChatHander;
import org.apache.commons.lang3.time.DateUtils;
import org.jdom2.JDOMException;
import org.junit.Test;
import org.roof.account.api.AccountType;
import org.roof.account.api.IAccountOperateService;
import org.roof.account.entity.Account;
import org.roof.web.dictionary.entity.Dictionary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

/**
 * Created by liangl on 2018/1/6.
 */
@ContextConfiguration(locations = {"classpath*:spring.xml"})
@WebAppConfiguration
public class WeChatHanderImplTest extends AbstractJUnit4SpringContextTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatHanderImplTest.class);

    @Autowired
    private IWeChatHander weChatHander;


    @Autowired
    private IImService imService;

    @Autowired
    private ICacheHander cacheHander;

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

    @Autowired
    private IAccountOperateService accountOperateService;


    @org.junit.Test
    public void getacode() throws Exception {
        weChatHander.getacode();
    }

    protected Chain chatOrderCreateChain;

    @Test
    public void dochain() {
        AdvisoryOrderVo advisoryOrderVo = new AdvisoryOrderVo();
        advisoryOrderVo.setConsId(1L);//咨询师
        advisoryOrderVo.setCustomId(152L);//
        advisoryOrderVo.setProductId(1L);

        ValueStack valueStack = new GenericValueStack();
        try {
            String addr = InetAddress.getLocalHost().getHostAddress();
            valueStack.set("ip", addr);
            advisoryOrderVo.setIp(addr);
            valueStack.set("advisoryOrderVo", advisoryOrderVo);
            chatOrderCreateChain.doChain(valueStack);

            SortedMap<Object, Object> packageP = (SortedMap<Object, Object>) valueStack.get("packageP");
            LOGGER.info(JSON.toJSONString(packageP));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    @Test
    public void extendOrderChain() {
        AdvisoryOrderVo advisoryOrderVo = new AdvisoryOrderVo();
        advisoryOrderVo.setConsId(1L);//咨询师
        advisoryOrderVo.setCustomId(152L);//
        advisoryOrderVo.setProductId(1L);

        ValueStack valueStack = new GenericValueStack();
        try {
            String addr = InetAddress.getLocalHost().getHostAddress();
            valueStack.set("ip", addr);
            valueStack.set("hasLink", "extend");
            valueStack.set("advisoryOrderVo", advisoryOrderVo);
            chatOrderCreateChain.doChain(valueStack);

            SortedMap<Object, Object> packageP = (SortedMap<Object, Object>) valueStack.get("packageP");
            LOGGER.info(JSON.toJSONString(packageP));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    @Test
    public void testxufei() throws IOException, ParseException {
        String result = "<xml><appid><![CDATA[wx53141e44187bdced]]></appid>\n" +
                "<bank_type><![CDATA[CFT]]></bank_type>\n" +
                "<cash_fee><![CDATA[10]]></cash_fee>\n" +
                "<fee_type><![CDATA[CNY]]></fee_type>\n" +
                "<is_subscribe><![CDATA[N]]></is_subscribe>\n" +
                "<mch_id><![CDATA[1439540202]]></mch_id>\n" +
                "<nonce_str><![CDATA[1539585554858]]></nonce_str>\n" +
                "<openid><![CDATA[om2wi0S4Fkxzm9rLzinOrfgG8NeE]]></openid>\n" +
                "<out_trade_no><![CDATA[D-20181015-000011]]></out_trade_no>\n" +
                "<result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "<return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "<sign><![CDATA[EDBEB2A6392059D6A496C2872248E481]]></sign>\n" +
                "<time_end><![CDATA[20181015143933]]></time_end>\n" +
                "<total_fee>10</total_fee>\n" +
                "<trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "<transaction_id><![CDATA[4200000178201810153999566672]]></transaction_id>\n" +
                "</xml>";
        Map<String, String> map = null;
        try {
            /**
             * 解析微信通知返回的信息
             */
            map = XMLUtil.doXMLParse(result);
        } catch (JDOMException e) {
            logger.error(e.getMessage(), e);
        }
        logger.info("=========:" + result);
        // 若支付成功，则告知微信服务器收到通知
        if (map.get("result_code").equals("SUCCESS")) {
            logger.info("充值成功！");
            //更新订单？
            AdvisoryOrderVo advisoryOrderVo = advisoryOrderService.loadByOrdernum(map.get("out_trade_no"));
            logger.info("out_trade_no：" + map.get("out_trade_no") + "加载的数据为：" + JSON.toJSONString(advisoryOrderVo));
            Account account = accountOperateService.queryByUserType(advisoryOrderVo.getConsId(), AccountType.local);
            //consId
            Map<String, String> map1 = new HashMap<>();

            Dictionary SHARING_RATIO = cacheHander.loadDictionaryById(241L);
            Integer cash_fee = Integer.valueOf(map.get("cash_fee"));
            BigDecimal ratio = new BigDecimal(SHARING_RATIO.getVal());
            map1.put("remark", "咨询分成");
            map1.put("tag1", map.get("out_trade_no"));
            accountOperateService.recharge(account.getId(), ratio.multiply(BigDecimal.valueOf(cash_fee)).setScale(0).intValue(), map1);
            if (advisoryOrderVo.getPayTime() == null) {
                //更新订单
                AdvisoryOrder order = new AdvisoryOrder();
                BeanUtils.copyProperties(advisoryOrderVo, order);
                AdvisoryOrder oldOrder = order;
                order.setPayTime(new Date());
                order.setOrderStatus(OrderStatusEnum.payed.getCode());
                order.setPayAmount(map.get("cash_fee") == null ? 0 : Integer.valueOf(map.get("cash_fee")));
                //判断是否有父订单
                if (order.getParentOrderId() != null) {
                    AdvisoryOrderVo parentOrderVo = advisoryOrderService.load(new AdvisoryOrder(order.getParentOrderId()));
                    Date startTime = parentOrderVo.getImEndTime();
                    order.setImStartTime(startTime);
                    order.setImEndTime(DateUtils.addMinutes(startTime, order.getLenTime().intValue()));
                } else {
                    Date startTime = new Date();
                    order.setImStartTime(startTime);
                    order.setImEndTime(DateUtils.addMinutes(startTime, order.getLenTime().intValue()));
                }
                logger.info("最后更新订单数据为：" + JSON.toJSONString(order));
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
                //发送打开会话
                advisoryOrderService.sendOpenSeesion(order);
            }

            String notifyStr = XMLUtil.setXML("SUCCESS", "");
        }
    }

    @Autowired
    public void setChatOrderCreateChain(@Qualifier("chatOrderCreateChain") Chain chatOrderCreateChain) {
        this.chatOrderCreateChain = chatOrderCreateChain;
    }
}