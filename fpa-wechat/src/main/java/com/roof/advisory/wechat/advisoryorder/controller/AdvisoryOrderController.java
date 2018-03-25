package com.roof.advisory.wechat.advisoryorder.controller;

import com.google.common.collect.Maps;
import com.roof.advisory.OrderStatusEnum;
import com.roof.advisory.advisoryorder.entity.AdvisoryOrder;
import com.roof.advisory.advisoryorder.entity.AdvisoryOrderVo;
import com.roof.advisory.advisoryorder.service.api.IAdvisoryOrderService;
import com.roof.advisory.advisoryproduct.entity.AdvisoryProduct;
import com.roof.advisory.advisoryproduct.entity.AdvisoryProductVo;
import com.roof.advisory.advisoryproduct.service.api.IAdvisoryProductService;
import com.roof.fpa.customer.entity.Customer;
import com.roof.fpa.customer.entity.CustomerVo;
import com.roof.fpa.customer.service.api.ICustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Api(value = "advisoryorder", description = "订单管理")
@Controller
@RequestMapping("/fpa/wechat/advisory")
public class AdvisoryOrderController {
    private IAdvisoryOrderService advisoryOrderService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IAdvisoryProductService advisoryProductService;


    @ApiOperation(value = "获得订单分页列表")
    @RequestMapping(value = "advisoryorder", method = {RequestMethod.GET})
    public @ResponseBody
    Result<Page> list(AdvisoryOrderVo advisoryOrderVo, HttpServletRequest request) {
        Page page = PageUtils.createPage(request);
        page = advisoryOrderService.pageVo(page, advisoryOrderVo);
        return new Result(Result.SUCCESS, page);
    }


    @ApiOperation(value = "新增订单")
    @RequestMapping(value = "advisoryorder", method = {RequestMethod.POST})
    public @ResponseBody
    Result create(@RequestBody AdvisoryOrder advisoryOrder) {
        if (advisoryOrder != null) {
            advisoryOrderService.save(advisoryOrder);
            return new Result("保存成功!");
        } else {
            return new Result(Result.FAIL, "数据传输失败!");
        }
    }

    @ApiOperation(value = "根据ID加载订单")
    @RequestMapping(value = "advisoryorder/{id}", method = {RequestMethod.GET})
    public @ResponseBody
    Result<AdvisoryOrderVo> load(@PathVariable Long id) {
        AdvisoryOrderVo advisoryOrderVo = advisoryOrderService.load(new AdvisoryOrder(id));
        return new Result(Result.SUCCESS, advisoryOrderVo);
    }

    @ApiOperation(value = "根据ID更新订单")
    @RequestMapping(value = "advisoryorder/{id}", method = {RequestMethod.PUT})
    public @ResponseBody
    Result update(@PathVariable Long id, @RequestBody AdvisoryOrder advisoryOrder) {
        if (id != null && advisoryOrder != null) {
            advisoryOrder.setId(id);
            advisoryOrderService.updateIgnoreNull(advisoryOrder);
            return new Result("保存成功!");
        } else {
            return new Result(Result.FAIL, "数据传输失败!");
        }
    }

    @ApiOperation(value = "根据ID删除订单")
    @RequestMapping(value = "advisoryorder/{id}", method = {RequestMethod.DELETE})
    public @ResponseBody
    Result delete(@PathVariable Long id) {
        AdvisoryOrder advisoryOrder = new AdvisoryOrder(id);
        advisoryOrder.setState(0);
        advisoryOrderService.delete(advisoryOrder);
        return new Result("删除成功!");
    }

    @Autowired(required = true)
    public void setAdvisoryOrderService(
            @Qualifier("advisoryOrderService") IAdvisoryOrderService advisoryOrderService
    ) {
        this.advisoryOrderService = advisoryOrderService;
    }


}
