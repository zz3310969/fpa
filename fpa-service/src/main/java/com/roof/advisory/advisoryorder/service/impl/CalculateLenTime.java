package com.roof.advisory.advisoryorder.service.impl;

import com.roof.advisory.OrderStatusEnum;
import com.roof.advisory.advisoryorder.entity.AdvisoryOrder;
import com.roof.advisory.advisoryorder.entity.AdvisoryOrderVo;
import com.roof.advisory.advisoryorder.service.api.IAdvisoryOrderService;
import com.roof.advisory.advisorypricing.entity.AdvisoryPricing;
import com.roof.advisory.advisorypricing.entity.AdvisoryPricingVo;
import com.roof.advisory.advisorypricing.service.api.IAdvisoryPricingService;
import com.roof.advisory.advisoryproduct.entity.AdvisoryProduct;
import com.roof.advisory.advisoryproduct.entity.AdvisoryProductVo;
import com.roof.advisory.advisoryproduct.service.api.IAdvisoryProductService;
import com.roof.chain.api.ValueStack;
import com.roof.chain.support.NodeResult;
import com.roof.fpa.customer.entity.Customer;
import com.roof.fpa.customer.entity.CustomerVo;
import com.roof.fpa.customer.service.api.ICustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.List;

/**
 * com.roof.advisory.advisoryorder.service.impl
 *
 * @author liht
 * @date 2018/6/19
 */
public class CalculateLenTime {
    private static final Logger LOGGER = LoggerFactory.getLogger(CalculateLenTime.class);


    /**
     * @param advisoryOrderVo
     * @param valueStack
     * @return
     * @apiNote 计算时长，单位为s
     */
    public String doNode(AdvisoryOrderVo advisoryOrderVo, ValueStack valueStack) {
        Long lenTime = 1000L;
        valueStack.set("lenTime", lenTime);
        return NodeResult.SUCCESS;
    }

}
