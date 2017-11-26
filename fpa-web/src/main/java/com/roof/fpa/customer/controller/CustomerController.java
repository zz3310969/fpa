package com.roof.fpa.customer.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Maps;
import com.roof.fpa.DefaultStateEnum;
import com.roof.fpa.GenderEnum;
import com.roof.fpa.cardunit.entity.CardUnit;
import com.roof.fpa.cardunit.entity.CardUnitVo;
import com.roof.fpa.charactercolor.entity.CharacterColor;
import com.roof.fpa.charactercolor.entity.CharacterColorVo;
import com.roof.fpa.theme.entity.Theme;
import com.roof.fpa.theme.entity.ThemeVo;
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

@Controller
@RequestMapping("fpa")
public class CustomerController {
	private ICustomerService customerService;

	@RequestMapping(value = "customer/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		GenderEnum[] genderEnums = GenderEnum.values();
		map.put("genders",genderEnums);
		return new Result(Result.SUCCESS, map);
	}




    @RequestMapping(value = "customer", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(CustomerVo customer, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = customerService.page(page, customer);
	    return new Result(Result.SUCCESS, page);
	}
	


	@RequestMapping(value = "customer", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody Customer customer) {
		if (customer != null) {
			customerService.save(customer);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
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
	
	@RequestMapping(value = "customer/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		customerService.delete(new Customer(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setCustomerService(
			@Qualifier("customerService") ICustomerService customerService) {
		this.customerService = customerService;
	}


}
