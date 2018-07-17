package com.roof.advisory.customer.controller;

import com.roof.advisory.OrderStatusEnum;
import com.roof.advisory.advisoryorder.entity.AdvisoryOrder;
import com.roof.advisory.advisoryorder.entity.AdvisoryOrderVo;
import com.roof.advisory.advisoryorder.service.api.IAdvisoryOrderService;
import com.roof.advisory.advisoryproduct.service.api.IAdvisoryProductService;
import com.roof.advisory.consultant.entity.Consultant;
import com.roof.advisory.consultant.entity.ConsultantVo;
import com.roof.advisory.consultant.service.api.IConsultantService;
import com.roof.advisory.im.service.ImRequest;
import com.roof.advisory.im.service.api.IImService;
import com.roof.fpa.customer.entity.CustomerVo;
import com.roof.fpa.customer.service.api.ICustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.spring.Result;
import org.roof.web.user.entity.User;
import org.roof.web.user.service.api.BaseUserContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * com.roof.advisory.wechat.customer.controller
 *
 * @author liht
 * @date 2018/3/13
 */
@Api(value = "customerAdvisory", description = "顾客咨询管理")
@Controller
@RequestMapping("advisory")
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
    @RequestMapping(value = "customer/closeseesion", method = {RequestMethod.POST})
    public @ResponseBody
    Result close(@RequestBody Map map,  HttpServletRequest request) {

        User user = (User) BaseUserContext.getCurrentUser(request);

        String token = (String) map.get("token");
        String weixinOpenId = (String) map.get("weixinOpenId");
        Long consultantId = Long.valueOf(map.get("consultantId")+"");

        Long sessionId = (Long) map.get("sessionId");

        //创建订单,绑定sessionId
        /*AdvisoryOrder order = new AdvisoryOrder();
        order.setSessionId(sessionId);*/

        advisoryOrderService.close(sessionId);

        CustomerVo customerVo = customerService.loadByOpenid(weixinOpenId);
        //开始远程调用openseesion
        ImRequest imRequest = new ImRequest();
        imRequest.setToken(token);
        imRequest.setSender(weixinOpenId);
        imRequest.setReceiver(user.getUsername());
        imRequest.setRequestType(ImRequest.IM_CLOSENSESSION);
        imRequest.setEndTime(System.currentTimeMillis());
        imService.closeSession(imRequest);

        return new Result(Result.SUCCESS);
    }


    @ApiOperation(value = "客户咨询初始化")
    @RequestMapping(value = "customer/ok", method = {RequestMethod.POST})
    public @ResponseBody
    Result close(@RequestBody String orderNum,  HttpServletRequest request) {
        AdvisoryOrderVo orderVo = advisoryOrderService.loadByOrdernum(orderNum);
        AdvisoryOrder order
                = new AdvisoryOrder();
        BeanUtils.copyProperties(orderVo, order);
        advisoryOrderService.sendOkSystemMessage(order);
        return new Result(Result.SUCCESS);
    }
}
