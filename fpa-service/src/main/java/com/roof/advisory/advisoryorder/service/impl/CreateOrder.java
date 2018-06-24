package com.roof.advisory.advisoryorder.service.impl;

import com.roof.advisory.OrderStatusEnum;
import com.roof.advisory.advisoryorder.entity.AdvisoryOrder;
import com.roof.advisory.advisoryorder.entity.AdvisoryOrderVo;
import com.roof.advisory.advisoryorder.service.api.IAdvisoryOrderService;
import com.roof.advisory.advisoryorderrecord.service.api.IAdvisoryOrderRecordService;
import com.roof.advisory.advisorypricing.entity.AdvisoryPricing;
import com.roof.advisory.advisorypricing.entity.AdvisoryPricingVo;
import com.roof.advisory.advisorypricing.service.api.IAdvisoryPricingService;
import com.roof.advisory.advisorypricing.service.impl.AdvisoryPricingService;
import com.roof.advisory.advisoryproduct.entity.AdvisoryProduct;
import com.roof.advisory.advisoryproduct.entity.AdvisoryProductVo;
import com.roof.advisory.advisoryproduct.service.api.IAdvisoryProductService;
import com.roof.chain.api.ValueStack;
import com.roof.chain.support.NodeResult;
import com.roof.fpa.DefaultStateEnum;
import com.roof.fpa.customer.entity.Customer;
import com.roof.fpa.customer.entity.CustomerVo;
import com.roof.fpa.customer.service.api.ICustomerService;
import com.roof.fpa.order.entity.OrderStateEnum;
import com.roof.fpa.order.service.api.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * com.roof.advisory.advisoryorder.service.impl
 *
 * @author liht
 * @date 2018/6/19
 */
public class CreateOrder {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateOrder.class);


    private IAdvisoryOrderRecordService advisoryOrderRecordService;

    private IAdvisoryOrderService advisoryOrderService;

    public String doNode(AdvisoryOrderVo advisoryOrderVo, ValueStack valueStack) {
        //load product
        AdvisoryProductVo advisoryProductVo = (AdvisoryProductVo) valueStack.get("advisoryProductVo");
        //load pricing
        AdvisoryPricingVo advisoryPricingVo = (AdvisoryPricingVo) valueStack.get("advisoryPricingVo");
        //load customer
        CustomerVo customerVo = (CustomerVo) valueStack.get("customerVo");

        //new order
        AdvisoryOrder order = new AdvisoryOrder();
        BeanUtils.copyProperties(advisoryOrderVo, order);
        order.setOrderTime(new Date());
        order.setOrderAmount(advisoryPricingVo.getCurrentPrice());
        order.setOrderStatus(OrderStatusEnum.submitted.getCode());//订单状态
        order.setState(DefaultStateEnum.usable.getCode());
        order.setConsId(advisoryProductVo.getConsId());//咨询师id
        order.setOrderNum(advisoryOrderService.createOrderNum(new Date()));//订单编号
        order.setLenTime(Long.valueOf(valueStack.get("lenTime").toString()));//服务时长
//        order.setCommentRecordId();// 评价记录id
        advisoryOrderService.save(order);
        valueStack.set("advisoryOrder", order);

        //record order change
        advisoryOrderRecordService.saveOrderChange(null, order);

        return NodeResult.SUCCESS;
    }

    public void setAdvisoryOrderRecordService(IAdvisoryOrderRecordService advisoryOrderRecordService) {
        this.advisoryOrderRecordService = advisoryOrderRecordService;
    }

    public void setAdvisoryOrderService(IAdvisoryOrderService advisoryOrderService) {
        this.advisoryOrderService = advisoryOrderService;
    }


}
