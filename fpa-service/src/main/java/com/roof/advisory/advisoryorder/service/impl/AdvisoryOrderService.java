package com.roof.advisory.advisoryorder.service.impl;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;
import com.roof.advisory.advisoryorder.entity.ImSystemMessage;
import com.roof.advisory.consultant.entity.Consultant;
import com.roof.advisory.consultant.entity.ConsultantVo;
import com.roof.advisory.consultant.service.api.IConsultantService;
import com.roof.advisory.wxsession.service.api.IWxSessionService;
import com.roof.fpa.core.http.HttpClientUtil;
import com.roof.fpa.customer.entity.Customer;
import com.roof.fpa.customer.entity.CustomerVo;
import com.roof.fpa.customer.service.api.ICustomerService;
import org.roof.commons.PropertiesUtil;
import org.roof.commons.RoofDateUtils;
import org.roof.roof.dataaccess.api.Page;
import com.roof.advisory.advisoryorder.dao.api.IAdvisoryOrderDao;
import com.roof.advisory.advisoryorder.entity.AdvisoryOrder;
import com.roof.advisory.advisoryorder.entity.AdvisoryOrderVo;
import com.roof.advisory.advisoryorder.service.api.IAdvisoryOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author liht
 */
@Service
public class AdvisoryOrderService implements IAdvisoryOrderService {
    private IAdvisoryOrderDao advisoryOrderDao;

    private Logger LOGGER = LoggerFactory.getLogger(AdvisoryOrderService.class);
    private String IM_BASEURL = PropertiesUtil.getPorpertyString("im.baseUrl");


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IConsultantService consultantService;

    @Autowired
    private IWxSessionService wxSessionService;

    @Override
    public void sendSystemMessage(AdvisoryOrder order) {
        Assert.notNull(order.getCustomId(), "客户id不能为空");
        Assert.notNull(order.getConsId(), "咨询师id不能为空");
        //load customer
        CustomerVo customerVo = customerService.load(new Customer(order.getCustomId()));
        //load cons
        ConsultantVo consultantVo = consultantService.load(new Consultant(order.getConsId()));
        String rs = "";
        //请求im post
        try {
            rs = HttpClientUtil.post(IM_BASEURL + "/system/message", generateSystemMessage(order, customerVo, consultantVo));
            LOGGER.info(rs);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

    }

    public String generateSystemMessage(AdvisoryOrder order, CustomerVo customerVo, ConsultantVo consultantVo) {
        ImSystemMessage message = new ImSystemMessage();
        message.setPayload(JSON.toJSONString(order));
        message.setToken(wxSessionService.createToken(customerVo.getWeixinOpenId()));
        message.setReceiver(consultantVo.getUsername());

        return JSON.toJSONString(message);
    }

    @Override
    public Serializable save(AdvisoryOrder advisoryOrder) {
        advisoryOrder.setState(1);
        return advisoryOrderDao.save(advisoryOrder);
    }

    @Override
    public void delete(AdvisoryOrder advisoryOrder) {
        advisoryOrderDao.delete(advisoryOrder);
    }

    @Override
    public void deleteByExample(AdvisoryOrder advisoryOrder) {
        advisoryOrderDao.deleteByExample(advisoryOrder);
    }

    @Override
    public void update(AdvisoryOrder advisoryOrder) {
        advisoryOrderDao.update(advisoryOrder);
    }

    @Override
    public void updateIgnoreNull(AdvisoryOrder advisoryOrder) {
        advisoryOrderDao.updateIgnoreNull(advisoryOrder);
    }

    @Override
    public void updateByExample(AdvisoryOrder advisoryOrder) {
        advisoryOrderDao.update("updateByExampleAdvisoryOrder", advisoryOrder);
    }

    @Override
    public AdvisoryOrderVo load(AdvisoryOrder advisoryOrder) {
        return (AdvisoryOrderVo) advisoryOrderDao.reload(advisoryOrder);
    }

    @Override
    public AdvisoryOrderVo loadByOrdernum(String orderNum) {
        Assert.notNull(orderNum, "订单号不能为空");
        AdvisoryOrder order = new AdvisoryOrder();
        order.setOrderNum(orderNum);
        AdvisoryOrderVo advisoryOrderVo = (AdvisoryOrderVo) advisoryOrderDao.selectForObject("selectAdvisoryOrder", order);
        return advisoryOrderVo;
    }

    @Override
    public AdvisoryOrderVo selectForObject(AdvisoryOrder advisoryOrder) {
        return (AdvisoryOrderVo) advisoryOrderDao.selectForObject("selectAdvisoryOrder", advisoryOrder);
    }

    @Override
    public List<AdvisoryOrderVo> selectForList(AdvisoryOrder advisoryOrder) {
        return (List<AdvisoryOrderVo>) advisoryOrderDao.selectForList("selectAdvisoryOrder", advisoryOrder);
    }

    @Override
    public Page page(Page page, AdvisoryOrder advisoryOrder) {
        return advisoryOrderDao.page(page, advisoryOrder);
    }

    @Override
    public Page pageVo(Page page, AdvisoryOrderVo advisoryOrderVo) {
        return advisoryOrderDao.pageVo(page, advisoryOrderVo);
    }

    @Override
    public String createOrderNum(Date date) {
        String key = "D-" + RoofDateUtils.dateToString(date, "yyyyMMdd");
        BoundValueOperations<String, Long> operations = redisTemplate.boundValueOps(key);// .increment(1);
        Long l = operations.increment(1);
        operations.expire(3, TimeUnit.DAYS);
        String s = "00000" + l;
        s = s.substring(s.length() - 6, s.length());
        String str = key + "-" + s;
        return str;
    }

    @Autowired
    public void setIAdvisoryOrderDao(
            @Qualifier("advisoryOrderDao") IAdvisoryOrderDao advisoryOrderDao
    ) {
        this.advisoryOrderDao = advisoryOrderDao;
    }


}
