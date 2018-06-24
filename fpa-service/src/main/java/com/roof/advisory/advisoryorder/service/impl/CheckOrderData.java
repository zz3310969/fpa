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
import java.util.SortedMap;

/**
 * com.roof.advisory.advisoryorder.service.impl
 *
 * @author liht
 * @date 2018/6/19
 */
public class CheckOrderData {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckOrderData.class);


    private IAdvisoryPricingService advisoryPricingService;

    private IAdvisoryProductService advisoryProductService;

    private ICustomerService customerService;

    private IAdvisoryOrderService advisoryOrderService;


    public String doNode(AdvisoryOrderVo advisoryOrderVo, ValueStack valueStack) {
        Assert.notNull(advisoryOrderVo, "order Data must not null");
        Assert.notNull(advisoryOrderVo.getCustomId(), "customerId must not null");
        Assert.notNull(advisoryOrderVo.getIp(), " ip must not null");
        Assert.notNull(advisoryOrderVo.getProductId(), "产品id不能为空");

        //load product
        AdvisoryProductVo advisoryProductVo = advisoryProductService.load(new AdvisoryProduct(advisoryOrderVo.getProductId()));
        if (advisoryProductVo.getAdvisId() == null) {
            LOGGER.error("advisoryProductVo.getAdvisId() is null");
            return NodeResult.FAIL;
        }
        valueStack.set("advisoryProductVo", advisoryProductVo);
        //load pricing
        AdvisoryPricingVo advisoryPricingVo = advisoryPricingService.load(new AdvisoryPricing(advisoryProductVo.getAdvisId()));
        if (advisoryPricingVo == null) {
            LOGGER.error("advisoryPricingVo is null");
            return NodeResult.FAIL;
        }
        valueStack.set("advisoryPricingVo", advisoryPricingVo);
        //load customer
        CustomerVo customerVo = customerService.load(new Customer(advisoryOrderVo.getCustomId()));
        if (customerVo == null) {
            LOGGER.error("customerVo is null");
            return NodeResult.FAIL;
        }
        valueStack.set("customerVo", customerVo);
        return NodeResult.SUCCESS;
    }

    public void setAdvisoryPricingService(IAdvisoryPricingService advisoryPricingService) {
        this.advisoryPricingService = advisoryPricingService;
    }

    public void setAdvisoryProductService(IAdvisoryProductService advisoryProductService) {
        this.advisoryProductService = advisoryProductService;
    }

    public void setCustomerService(ICustomerService customerService) {
        this.customerService = customerService;
    }

    public void setAdvisoryOrderService(IAdvisoryOrderService advisoryOrderService) {
        this.advisoryOrderService = advisoryOrderService;
    }
}
