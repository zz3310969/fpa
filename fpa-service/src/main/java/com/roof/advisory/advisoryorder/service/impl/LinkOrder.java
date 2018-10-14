package com.roof.advisory.advisoryorder.service.impl;

import com.roof.advisory.OrderStatusEnum;
import com.roof.advisory.advisoryorder.entity.AdvisoryOrder;
import com.roof.advisory.advisoryorder.entity.AdvisoryOrderVo;
import com.roof.advisory.advisoryorder.service.api.IAdvisoryOrderService;
import com.roof.advisory.advisoryorderrecord.service.api.IAdvisoryOrderRecordService;
import com.roof.chain.api.ValueStack;
import com.roof.chain.support.NodeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * com.roof.advisory.advisoryorder.service.impl
 *
 * @author liht
 * @date 2018/6/19
 */
public class LinkOrder {
    private static final Logger LOGGER = LoggerFactory.getLogger(LinkOrder.class);

    private IAdvisoryOrderService advisoryOrderService;

    private IAdvisoryOrderRecordService advisoryOrderRecordService;

    public String doNode(AdvisoryOrder advisoryOrder, ValueStack valueStack) {
        if (valueStack.get("hasLink") != null) {
            //找到这个客户的上级订单
            //判断是否已有未完成的订单，拒绝购买先？
            AdvisoryOrder param = new AdvisoryOrder();
            param.setCustomId(advisoryOrder.getCustomId());
            param.setOrderStatus(OrderStatusEnum.payed.getCode());
            Long sessionId = (Long) valueStack.get("sessionId");
            param.setSessionId(sessionId);
            List<AdvisoryOrderVo> advisoryOrderVoList = advisoryOrderService.selectForList(param);
            Long parentOrderId = 0L;
            if (advisoryOrderVoList != null && advisoryOrderVoList.size() == 1) {
                parentOrderId = advisoryOrderVoList.get(0).getId();
            } else {
                LOGGER.error("不存在老订单，无法进行关联");
                return NodeResult.FAIL;
            }
            //更新新订单
            AdvisoryOrder oldOrder = advisoryOrder;
            advisoryOrder.setParentOrderId(parentOrderId);
            advisoryOrderService.updateIgnoreNull(advisoryOrder);
            //订单变更记录
            advisoryOrderRecordService.saveOrderChange(oldOrder, advisoryOrder);
        }
        return NodeResult.SUCCESS;
    }

    public void setAdvisoryOrderRecordService(IAdvisoryOrderRecordService advisoryOrderRecordService) {
        this.advisoryOrderRecordService = advisoryOrderRecordService;
    }

    public void setAdvisoryOrderService(IAdvisoryOrderService advisoryOrderService) {
        this.advisoryOrderService = advisoryOrderService;
    }
}
