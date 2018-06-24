package com.roof.advisory.advisoryorder.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.roof.commons.RoofDateUtils;
import org.roof.roof.dataaccess.api.Page;
import com.roof.advisory.advisoryorder.dao.api.IAdvisoryOrderDao;
import com.roof.advisory.advisoryorder.entity.AdvisoryOrder;
import com.roof.advisory.advisoryorder.entity.AdvisoryOrderVo;
import com.roof.advisory.advisoryorder.service.api.IAdvisoryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class AdvisoryOrderService implements IAdvisoryOrderService {
    private IAdvisoryOrderDao advisoryOrderDao;

    @Autowired
    private RedisTemplate redisTemplate;

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
        Assert.isNull(orderNum, "订单号不能为空");
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
