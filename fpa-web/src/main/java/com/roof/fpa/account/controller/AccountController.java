package com.roof.fpa.account.controller;

import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.fpa.account.entity.Account;
import com.roof.fpa.account.entity.AccountVo;
import com.roof.fpa.account.service.api.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "account", description = "账户管理")
@Controller
@RequestMapping("fpa")
public class AccountController {
	private IAccountService accountService;

	@ApiOperation(value = "获得账户基础信息")
	@RequestMapping(value = "account/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得账户分页列表")
    @RequestMapping(value = "account", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(Account account, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = accountService.page(page, account);
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增账户")
	@RequestMapping(value = "account", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody Account account) {
		if (account != null) {
			accountService.save(account);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载账户")
    @RequestMapping(value = "account/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<AccountVo> load(@PathVariable Long id) {
		AccountVo accountVo = accountService.load(new Account(id));
        return new Result(Result.SUCCESS,accountVo);
    }

	@ApiOperation(value = "根据ID更新账户")
	@RequestMapping(value = "account/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody Account account) {
		if (id != null && account != null) {
			account.setId(id);
			accountService.updateIgnoreNull(account);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除账户")
	@RequestMapping(value = "account/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		accountService.delete(new Account(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setAccountService(
			@Qualifier("accountService") IAccountService accountService) {
		this.accountService = accountService;
	}


}
