package com.roof.advisory.advisoryorder.service.impl;

import com.roof.advisory.advisoryorder.entity.AdvisoryOrder;
import com.roof.advisory.advisoryorder.entity.AdvisoryOrderVo;
import com.roof.advisory.advisoryorder.service.api.IAdvisoryOrderService;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * com.roof.advisory.advisoryorder.service.impl
 *
 * @author liht
 * @date 2018/7/12
 */
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:spring.xml"})
public class AdvisoryOrderServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private IAdvisoryOrderService advisoryOrderService;

    @Test
    public void sendSystemMessage() {
        AdvisoryOrderVo orderVo = advisoryOrderService.load(new AdvisoryOrder((125L)));
        AdvisoryOrder order
                = new AdvisoryOrder();
        BeanUtils.copyProperties(orderVo, order);
        advisoryOrderService.sendSystemMessage(order);
    }

    @Test
    public void sendOpenSeesion() {
        AdvisoryOrderVo orderVo = advisoryOrderService.load(new AdvisoryOrder((125L)));
        AdvisoryOrder order
                = new AdvisoryOrder();
        BeanUtils.copyProperties(orderVo, order);
        advisoryOrderService.sendOpenSeesion(order);
    }
}
