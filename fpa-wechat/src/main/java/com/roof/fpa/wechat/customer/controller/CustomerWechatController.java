package com.roof.fpa.wechat.customer.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Maps;
import com.roof.DateUtil;
import com.roof.fpa.DefaultStateEnum;
import com.roof.fpa.GenderEnum;
import com.roof.fpa.cardtestresult.entity.SimilerResult;
import com.roof.fpa.cardunit.entity.CardUnit;
import com.roof.fpa.cardunit.entity.CardUnitVo;
import com.roof.fpa.charactercolor.entity.CharacterColor;
import com.roof.fpa.charactercolor.entity.CharacterColorVo;
import com.roof.fpa.partner.service.api.IPartnerService;
import com.roof.fpa.theme.entity.Theme;
import com.roof.fpa.theme.entity.ThemeVo;
import com.roof.fpa.weixin.service.impl.WeChatDto;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.roof.account.api.AccountType;
import org.roof.account.api.IAccountOperateService;
import org.roof.account.entity.Account;
import org.roof.account.entity.AccountFlow;
import org.roof.roof.dataaccess.api.FastPage;
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

	@Autowired
	private IAccountOperateService accountOperateService;

	private ExecutorService executorService = Executors.newFixedThreadPool(1);


	@RequestMapping(value = "v2/customer/friends", method = {RequestMethod.GET})
	public @ResponseBody Result<List<CustomerVo>> listv2(CustomerVo customer, HttpServletRequest request) {
		List<CustomerVo> page = customerService.friends(customer);
		return new Result(Result.SUCCESS, page);
	}

	@RequestMapping(value = "customer/friends", method = {RequestMethod.GET})
	public @ResponseBody Result<Page> list(CustomerVo customer, HttpServletRequest request) {
		Page page = PageUtils.createPage(request);
		page = customerService.friendsPage(page, customer);
		return new Result(Result.SUCCESS, page);
	}

	@RequestMapping(value = "customer/best/friends", method = {RequestMethod.GET})
	public @ResponseBody Result<List<CustomerVo>> best_list(CustomerVo customer, HttpServletRequest request) {
		List<CustomerVo> page = customerService.bestFriends( customer);
		return new Result(Result.SUCCESS, page);
	}

	@RequestMapping(value = "customer/friends/similer", method = {RequestMethod.GET})
	public @ResponseBody Result similer(CustomerVo customer, HttpServletRequest request) {
		if (customer.getId() == null){
			return new Result(Result.ERROR,"id 不能为空");
		}
		if (customer.getFriendId() == null){
			return new Result(Result.ERROR,"friendId 不能为空");
		}
		SimilerResult similerResult = customerService.similer(customer.getId(), customer.getFriendId());
		return new Result(Result.SUCCESS, similerResult);
	}

	@RequestMapping(value = "customer", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody CustomerVo customer) {
		if (customer != null) {
			if(StringUtils.isEmpty(customer.getCode())){
				return new Result(Result.FAIL,"code不能为空");
			}
			WeChatDto weChatDto =  customerService.saveOrUpdate(customer);
			return new Result(Result.SUCCESS,weChatDto.getUserId());
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

	@RequestMapping(value = "v2/customer", method = {RequestMethod.POST})
	public @ResponseBody Result create_v2(@RequestBody CustomerVo customer) {
		if (customer != null) {
			if(StringUtils.isEmpty(customer.getCode())){
				return new Result(Result.FAIL,"code不能为空");
			}
			WeChatDto weChatDto =  customerService.saveOrUpdate(customer);
			return new Result(Result.SUCCESS,weChatDto);
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

	@RequestMapping(value = "customer/update/{id}", method = {RequestMethod.POST})
	public @ResponseBody Result updateMobile(@PathVariable Long id ,@RequestBody Customer customer) {
		if(StringUtils.isEmpty(customer.getMobile())){
			return new Result(Result.FAIL,"mobile不能为空");
		}
		if(StringUtils.isEmpty(String.valueOf(customer.getId()))){
			return new Result(Result.FAIL,"id不能为空");
		}
		if (id != null && customer != null) {
			customer.setId(id);
			customerService.updateIgnoreNull(customer);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

	@RequestMapping(value = "customer/bind", method = {RequestMethod.POST})
	public @ResponseBody Result bind(@RequestBody CustomerVo customer) {
		executorService.submit(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return partnerService.bind(customer.getFriendId(),customer.getId());
			}
		});
		return new Result(Result.SUCCESS);
	}



	@RequestMapping(value = "customer/account/{userId}", method = {RequestMethod.GET})
	public @ResponseBody
	Result<Account> account(@PathVariable Long userId) {
		Account account = accountOperateService.queryByUserType(userId, AccountType.local);
		return new Result(Result.SUCCESS, account);
	}

	@RequestMapping(value = "customer/account/balance/{userId}", method = {RequestMethod.GET})
	public @ResponseBody
	Result<Map> balance(@PathVariable Long userId) {
		Account account = accountOperateService.queryByUserType(userId, AccountType.local);
		//Long totalIncome = accountOperateService.queryByUserTypeTotalIncome(userId, AccountType.local);
		Long todayIncome = accountOperateService.queryByUserTypeTotalIncomeTime(userId, DateUtil.getNowDayStart(),DateUtil.getNowDayEnd());
		Map<String,Object> map = Maps.newHashMap();
		//map.put("totalIncome",totalIncome);
		map.put("todayIncome",todayIncome);
		map.put("deposit",account.getBalance());
		return new Result(Result.SUCCESS, map);
	}

	@RequestMapping(value = "customer/accountflow/{userId}", method = {RequestMethod.GET})
	public @ResponseBody
	Result<List<AccountFlow>> accountFlows(@PathVariable Long userId, HttpServletRequest request) {
		Page page = PageUtils.createPage(request);

		List<AccountFlow> accountFlows = accountOperateService.queryFlowByUser(userId, page.getStart(), page.getLimit());
		return new Result(Result.SUCCESS, accountFlows);
	}



	@Autowired(required = true)
	public void setCustomerService(
			@Qualifier("customerService") ICustomerService customerService) {
		this.customerService = customerService;
	}



}
