package com.roof.fpa.order.controller;

import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.fpa.order.entity.Order;
import com.roof.fpa.order.entity.OrderVo;
import com.roof.fpa.order.service.api.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "order", description = "订单管理")
@Controller
@RequestMapping("fpa")
public class OrderController {
	private IOrderService orderService;

	@ApiOperation(value = "获得订单基础信息")
	@RequestMapping(value = "order/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得订单分页列表")
    @RequestMapping(value = "order", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(Order order, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = orderService.page(page, order);
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增订单")
	@RequestMapping(value = "order", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody Order order) {
		if (order != null) {
			orderService.save(order);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载订单")
    @RequestMapping(value = "order/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<OrderVo> load(@PathVariable Long id) {
		OrderVo orderVo = orderService.load(new Order(id));
        return new Result(Result.SUCCESS,orderVo);
    }

	@ApiOperation(value = "根据ID更新订单")
	@RequestMapping(value = "order/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody Order order) {
		if (id != null && order != null) {
			order.setId(id);
			orderService.updateIgnoreNull(order);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除订单")
	@RequestMapping(value = "order/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		orderService.delete(new Order(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setOrderService(
			@Qualifier("orderService") IOrderService orderService) {
		this.orderService = orderService;
	}


}
