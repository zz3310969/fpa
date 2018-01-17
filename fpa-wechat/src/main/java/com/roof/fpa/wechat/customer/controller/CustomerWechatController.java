package com.roof.fpa.wechat.customer.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Maps;
import com.roof.fpa.DefaultStateEnum;
import com.roof.fpa.GenderEnum;
import com.roof.fpa.cardunit.entity.CardUnit;
import com.roof.fpa.cardunit.entity.CardUnitVo;
import com.roof.fpa.charactercolor.entity.CharacterColor;
import com.roof.fpa.charactercolor.entity.CharacterColorVo;
import com.roof.fpa.partner.service.api.IPartnerService;
import com.roof.fpa.theme.entity.Theme;
import com.roof.fpa.theme.entity.ThemeVo;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.fpa.customer.entity.Customer;
import com.roof.fpa.customer.entity.CustomerVo;
import com.roof.fpa.customer.service.api.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "customer", description = "顾客管理")
@Controller
@RequestMapping("fpa/wechat")
public class CustomerWechatController {
	private ICustomerService customerService;

	@Autowired
	private IPartnerService partnerService;

	private ExecutorService executorService = Executors.newFixedThreadPool(1);


	@RequestMapping(value = "customer/friends", method = {RequestMethod.GET})
	public @ResponseBody Result<Page> list(CustomerVo customer, HttpServletRequest request) {
		Page page = PageUtils.createPage(request);
		page = customerService.friendsPage(page, customer);
		return new Result(Result.SUCCESS, page);
	}

	@RequestMapping(value = "customer", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody CustomerVo customer) {
		if (customer != null) {
			if(StringUtils.isEmpty(customer.getCode())){
				return new Result(Result.FAIL,"code不能为空");
			}
			Long id = (Long) customerService.saveOrUpdate(customer);
			return new Result(Result.SUCCESS,id);
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

	@RequestMapping(value = "customer/{openid}", method = {RequestMethod.GET})
	public @ResponseBody Result<CustomerVo> loadByOpenId(@PathVariable String openid) {
		CustomerVo customerVo = customerService.loadByOpenid(openid);
		return new Result(Result.SUCCESS,customerVo);
	}

    @RequestMapping(value = "customer/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<CustomerVo> load(@PathVariable Long id) {
		CustomerVo customerVo = customerService.load(new Customer(id));
        return new Result(Result.SUCCESS,customerVo);
    }
	
	@RequestMapping(value = "customer/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody Customer customer) {
		if (id != null && customer != null) {
			customer.setId(id);
			customerService.updateIgnoreNull(customer);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

	@RequestMapping(value = "customer/bind", method = {RequestMethod.POST})
	public @ResponseBody Result bind(@RequestBody Customer customer) {
		executorService.submit(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return partnerService.bind(152L,153L);
			}
		});
		return new Result(Result.SUCCESS);
	}


	@Autowired(required = true)
	public void setCustomerService(
			@Qualifier("customerService") ICustomerService customerService) {
		this.customerService = customerService;
	}



}
