package org.roof.account.wechat.service;

import com.alibaba.fastjson.JSON;
import org.roof.account.api.AccountType;
import org.roof.account.api.IAccountService;
import org.roof.account.api.IAccountServiceContainer;
import org.roof.account.entity.Account;
import org.roof.account.exceptions.UnsupportedMethodException;
import org.roof.account.impl.AccountRequest;
import org.roof.account.jdbc.mapper.AccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class WechatAccountService implements IAccountService, InitializingBean {

    private AccountType accountType = AccountType.wechat;
    private static final Logger LOGGER = LoggerFactory.getLogger(WechatAccountService.class);

    @Value("${fpa.mini.appid}")
    private String appid;
    @Value("${wechat.pay.mch_id}")
    private String mchid;
    @Value("${wechat.pay.notify_url}")
    private String notify_url;
    @Value("${wechat.pay.unifiedorder.url}")
    private String messageservice_sendurl;


    @Autowired
    private IAccountServiceContainer accountServiceContainer;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account load(Long id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Account create(AccountRequest accountRequest) {
        Assert.notNull(accountRequest.getRequestParameters("openId"), "openId must not null");

        Account account = new Account();
        account.setUserId(accountRequest.getUserId());
        account.setAccountType(accountType.name());
        account.setCreateTime(new Date());
        account.setThirdPartId(accountRequest.getRequestParameters("openId"));
        account.setId(accountMapper.insert(account));
        return account;
    }

    public String createAccountNum() {
        return null;
    }


    @Override
    public void freeze(Account account) {
        throw new UnsupportedMethodException("not supported freeze");
    }

    @Override
    public IAccountService get(AccountType type) {
        if (accountType.equals(type)) {
            return this;
        } else {
            return null;
        }
    }

    @Override
    public IAccountService get(String type) {
        if (accountType.name().equals(type)) {
            return this;
        } else {
            return null;
        }
    }

    @Override
    public void recharge(Account account, int money, Map<String, String> map) {
        throw new UnsupportedMethodException("not supported recharge");
    }

    @Override
    public SortedMap<Object, Object> deduction(Account account, int money, Map<String, String> map) {
        Assert.notNull(account.getThirdPartId(), "openId must not null");
        SortedMap<Object, Object> packageP = new TreeMap<Object, Object>();
        //
        String times = System.currentTimeMillis() + "";

        SortedMap<Object, Object> param = new TreeMap<Object, Object>();
        param.put("appid", appid);
        param.put("body", map.get("body"));
        param.put("mch_id", mchid);
        param.put("nonce_str", times);
        param.put("notify_url", notify_url);
        param.put("openid", account.getThirdPartId());
        param.put("out_trade_no", map.get("out_trade_no"));
        param.put("spbill_create_ip", map.get("ip"));
        param.put("total_fee", money);
        param.put("trade_type", "JSAPI");
        String sign = PayCommonUtil.createSign("UTF-8", param);
        param.put("sign", sign);
        String requestXML = PayCommonUtil.getRequestXml(param);
        try {
            LOGGER.info("发送的文本的消息格式为：" + requestXML);
            String resXml = HttpClientUtil.post(messageservice_sendurl, requestXML);
            LOGGER.info("微信返回结果为：" + resXml);
            Map map1 = XMLUtil.doXMLParse(resXml);
            //System.out.println(map);
            //得到prepay_id
            String prepay_id = (String) map1.get("prepay_id");
            packageP.put("appId", appid);//！！！注意，这里是appId,上面是appid，真怀疑写这个东西的人。。。
            packageP.put("nonceStr", times);//时间戳
            packageP.put("package", "prepay_id=" + prepay_id);//必须把package写成 "prepay_id="+prepay_id这种形式
            packageP.put("signType", "MD5");//paySign加密
            packageP.put("timeStamp", (System.currentTimeMillis() / 1000) + "");
            //得到paySign
            String paySign = PayCommonUtil.createSign("UTF-8", packageP);
            packageP.put("paySign", paySign);
            packageP.put("result_code", map1.get("result_code"));
            LOGGER.info(JSON.toJSONString(packageP));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return packageP;
    }

    @Override
    public void thaw(Account account) {
        throw new UnsupportedMethodException("not supported thaw");

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        accountServiceContainer.addAccountService(this);
    }
}
