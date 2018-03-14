package com.roof.advisory.wechat.customer.controller;

import com.roof.advisory.OrderStatusEnum;
import com.roof.advisory.advisoryorder.entity.AdvisoryOrder;
import com.roof.advisory.advisoryorder.service.api.IAdvisoryOrderService;
import com.roof.advisory.advisoryproduct.service.api.IAdvisoryProductService;
import com.roof.advisory.consultant.entity.Consultant;
import com.roof.advisory.consultant.entity.ConsultantVo;
import com.roof.advisory.consultant.service.api.IConsultantService;
import com.roof.advisory.im.service.ImRequest;
import com.roof.advisory.im.service.api.IImService;
import com.roof.fpa.customer.entity.Customer;
import com.roof.fpa.customer.entity.CustomerVo;
import com.roof.fpa.customer.service.api.ICustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.asm.Advice;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * com.roof.advisory.wechat.customer.controller
 *
 * @author liht
 * @date 2018/3/13
 */
@Api(value = "customerAdvisory", description = "顾客咨询管理")
@Controller
@RequestMapping("fpa/wechat/advisory")
public class CustomerAdvisoryController {

    @Autowired
    private IImService imService;

    @Autowired
    private IConsultantService consultantService;

    @Autowired
    private IAdvisoryOrderService advisoryOrderService;

    @Autowired
    private IAdvisoryProductService advisoryProductService;

    @Autowired
    private ICustomerService customerService;

    @ApiOperation(value = "客户咨询初始化")
    @RequestMapping(value = "customer/openseesion", method = {RequestMethod.POST})
    public @ResponseBody
    Result list(String token, Long consultantId, String weixinOpenId, HttpServletRequest request) {
        ConsultantVo consultantVo = consultantService.load(new Consultant(consultantId));
        CustomerVo customerVo = customerService.loadByOpenid(weixinOpenId);
        //开始远程调用openseesion
        ImRequest imRequest = new ImRequest();
        imRequest.setToken(token);
        imRequest.setSender(weixinOpenId);
        imRequest.setReceiver(consultantVo.getUsername());
        imRequest.setStartTime(System.currentTimeMillis());
        imRequest.setRequestType(ImRequest.IM_OPENSESSION);
        Long sessionId = imService.openSession(imRequest);
        //seesion已经存在
        if (sessionId != null) {
            return new Result(Result.SUCCESS);
        }
        //创建订单,绑定sessionId
        AdvisoryOrder order = new AdvisoryOrder();
        order.setSessionId(sessionId);
        order.setCustomId(customerVo.getId());
        order.setOrderNum(advisoryOrderService.createOrderNum(new Date()));
        order.setProductId((Long) advisoryProductService.findAndCreate(consultantId));
        order.setState(1);
        order.setOrderStatus(OrderStatusEnum.submitted.getCode());
        order.setOrderTime(new Date());
        advisoryOrderService.save(order);

        return new Result(Result.SUCCESS);
    }
}
