package com.roof.advisory.advisoryorder.service.impl;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.roof.advisory.OrderStatusEnum;
import com.alibaba.fastjson.JSON;
import com.roof.advisory.advisoryorder.entity.ImSystemMessage;
import com.roof.advisory.advisoryproduct.entity.AdvisoryProduct;
import com.roof.advisory.advisoryproduct.entity.AdvisoryProductVo;
import com.roof.advisory.advisoryproduct.service.api.IAdvisoryProductService;
import com.roof.advisory.consultant.entity.Consultant;
import com.roof.advisory.consultant.entity.ConsultantVo;
import com.roof.advisory.consultant.service.api.IConsultantService;
import com.roof.advisory.im.service.ImRequest;
import com.roof.advisory.im.service.ImResponse;
import com.roof.advisory.im.service.impl.ImService;
import com.roof.advisory.wxsession.service.api.IWxSessionService;
import com.roof.fpa.core.http.HttpClientUtil;
import com.roof.fpa.customer.entity.Customer;
import com.roof.fpa.customer.entity.CustomerVo;
import com.roof.fpa.customer.service.api.ICustomerService;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.roof.commons.PropertiesUtil;
import org.roof.commons.RoofDateUtils;
import org.roof.roof.dataaccess.api.Page;
import com.roof.advisory.advisoryorder.dao.api.IAdvisoryOrderDao;
import com.roof.advisory.advisoryorder.entity.AdvisoryOrder;
import com.roof.advisory.advisoryorder.entity.AdvisoryOrderVo;
import com.roof.advisory.advisoryorder.service.api.IAdvisoryOrderService;
import org.roof.spring.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    private IAdvisoryProductService advisoryProductService;


    @Autowired
    private IAdvisoryOrderService advisoryOrderService;

    @Override
    public void sendOpenSeesion(AdvisoryOrder order) throws IOException, ParseException {
        Assert.notNull(order.getCustomId(), "客户id不能为空");
        Assert.notNull(order.getConsId(), "咨询师id不能为空");
        //load customer
        CustomerVo customerVo = customerService.load(new Customer(order.getCustomId()));
        //load cons
        ConsultantVo consultantVo = consultantService.load(new Consultant(order.getConsId()));

        String token = wxSessionService.getToken(customerVo.getWeixinOpenId());
        String receiver = consultantVo.getUsername();
        String sender = customerVo.getWeixinOpenId();
        Long startTime = System.currentTimeMillis();
        Long endTime = System.currentTimeMillis();
        if (order.getImStartTime() != null) {
            startTime = order.getImStartTime().getTime();
            endTime = order.getImEndTime().getTime();
        } else {
            endTime = startTime + order.getLenTime() * 60000;//1分钟=60000毫秒(ms)
        }


        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("sender", sender);
        map.put("receiver", receiver);
        map.put("requestType", "openSession");
        map.put("startTime", startTime);
        map.put("endTime", endTime);

        if (order.getSessionId() == null) {
            LOGGER.info("/session/open");

            String rs = null;
            try {
                rs = HttpClientUtil.post(IM_BASEURL + "/session/open?requestType=openSession", JSON.toJSONString(map));
                ImResponse response = JSON.parseObject(rs, ImResponse.class);
                if (!StringUtils.equals(response.getState(), "success")) {
                    LOGGER.error("openSession出错:", response.getMessage());
                } else {
                    Map map1 = response.getResult();
                    Long sessionId = Long.valueOf(map1.get("id").toString());
                    AdvisoryOrder advisoryOrder = new AdvisoryOrder(order.getId());
                    advisoryOrder.setSessionId(sessionId);

                    advisoryOrderService.updateIgnoreNull(advisoryOrder);

                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                throw e;
            }
            LOGGER.info(rs);

        } else {
            LOGGER.info("/session/start");

            map.put("sessionId", order.getSessionId());
            map.put("requestType", "startSession");

            String rs = null;
            try {
                rs = HttpClientUtil.post(IM_BASEURL + "/session/start", JSON.toJSONString(map));

            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                throw e;
            }
            LOGGER.info(rs);


        }


    }

    @Override
    public void sendSystemMessage(AdvisoryOrder order) {
        Assert.notNull(order.getCustomId(), "客户id不能为空");
        Assert.notNull(order.getConsId(), "咨询师id不能为空");
        Assert.notNull(order.getProductId(), "产品id不能为空");
        //load customer
        CustomerVo customerVo = customerService.load(new Customer(order.getCustomId()));
        //load cons
        ConsultantVo consultantVo = consultantService.load(new Consultant(order.getConsId()));
        //load product
        AdvisoryProductVo advisoryProductVo = advisoryProductService.load(new AdvisoryProduct(order.getProductId()));

        String rs = "";
        //请求im post

        AdvisoryOrderVo advisoryOrderVo = new AdvisoryOrderVo();
        BeanUtils.copyProperties(order, advisoryOrderVo);
        advisoryOrderVo.setConsName(consultantVo.getName());
        advisoryOrderVo.setCustomName(customerVo.getNickName());
        advisoryOrderVo.setProductName(advisoryProductVo.getName());
        advisoryOrderVo.setWeixinOpenId(customerVo.getWeixinOpenId());

        advisoryOrderVo.setViewWord("你有新的客户" + customerVo.getNickName() + "想进行(" + advisoryProductVo.getName() + ")咨询，是否接单");

        try {
            rs = HttpClientUtil.post(IM_BASEURL + "/system/message", generateSystemMessage(advisoryOrderVo, customerVo, consultantVo));
            LOGGER.info(rs);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

    }

    @Override
    public void sendOkSystemMessage(AdvisoryOrder order) {
        Assert.notNull(order.getCustomId(), "客户id不能为空");
        Assert.notNull(order.getConsId(), "咨询师id不能为空");
        Assert.notNull(order.getProductId(), "产品id不能为空");
        //load customer
        CustomerVo customerVo = customerService.load(new Customer(order.getCustomId()));
        //load cons
        ConsultantVo consultantVo = consultantService.load(new Consultant(order.getConsId()));
        //load product
        AdvisoryProductVo advisoryProductVo = advisoryProductService.load(new AdvisoryProduct(order.getProductId()));

        String rs = "";
        //请求im post

        AdvisoryOrderVo advisoryOrderVo = new AdvisoryOrderVo();
        BeanUtils.copyProperties(order, advisoryOrderVo);
        advisoryOrderVo.setConsName(consultantVo.getName());
        advisoryOrderVo.setCustomName(customerVo.getNickName());
        advisoryOrderVo.setProductName(advisoryProductVo.getName());

        advisoryOrderVo.setViewWord("咨询师已经接受你的订单");

        try {
            rs = HttpClientUtil.post(IM_BASEURL + "/system/message", generateSystemMessage(advisoryOrderVo, consultantVo, customerVo));
            LOGGER.info(rs);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

    }

    public String generateSystemMessage(AdvisoryOrderVo order, CustomerVo customerVo, ConsultantVo consultantVo) {
        ImSystemMessage message = new ImSystemMessage();
        message.setPayload(JSON.toJSONString(order));
        message.setToken(wxSessionService.getToken(customerVo.getWeixinOpenId()));
        message.setReceiver(consultantVo.getUsername());

        return JSON.toJSONString(message);
    }

    public String generateSystemMessage(AdvisoryOrderVo order, ConsultantVo consultantVo, CustomerVo customerVo) {
        ImSystemMessage message = new ImSystemMessage();
        message.setPayload(JSON.toJSONString(order));
        message.setReceiver(customerVo.getWeixinOpenId());
        message.setToken(wxSessionService.getToken(consultantVo.getUsername()));

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
    public AdvisoryOrderVo loadLastOrderBySeesionId(Long seesionId) {
        Assert.notNull(seesionId, "seesionId不能为空");
        AdvisoryOrder order = new AdvisoryOrder();
        order.setSessionId(seesionId);
        AdvisoryOrderVo advisoryOrderVo = (AdvisoryOrderVo) advisoryOrderDao.selectForObject("selectAdvisoryOrderBySeesionId", order);
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

    @Override
    public void close(Long sessionId) {
        AdvisoryOrder advisoryOrder = new AdvisoryOrder();
        advisoryOrder.setImEndTime(new Date());
        advisoryOrder.setOrderStatus(OrderStatusEnum.completed.getCode());
        advisoryOrderDao.update("closeAdvisoryOrder", advisoryOrder);

    }

    @Autowired
    public void setIAdvisoryOrderDao(
            @Qualifier("advisoryOrderDao") IAdvisoryOrderDao advisoryOrderDao
    ) {
        this.advisoryOrderDao = advisoryOrderDao;
    }


}
