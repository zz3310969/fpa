package com.roof.fpa.withdraw.controller;

import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.fpa.withdraw.entity.Withdraw;
import com.roof.fpa.withdraw.entity.WithdrawVo;
import com.roof.fpa.withdraw.service.api.IWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "withdraw", description = "提现记录管理")
@Controller
@RequestMapping("fpa")
public class WithdrawController {
	private IWithdrawService withdrawService;

	@ApiOperation(value = "获得提现记录基础信息")
	@RequestMapping(value = "withdraw/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得提现记录分页列表")
    @RequestMapping(value = "withdraw", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(Withdraw withdraw, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = withdrawService.page(page, withdraw);
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增提现记录")
	@RequestMapping(value = "withdraw", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody Withdraw withdraw) {
		if (withdraw != null) {
			withdrawService.save(withdraw);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载提现记录")
    @RequestMapping(value = "withdraw/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<WithdrawVo> load(@PathVariable Long id) {
		WithdrawVo withdrawVo = withdrawService.load(new Withdraw(id));
        return new Result(Result.SUCCESS,withdrawVo);
    }

	@ApiOperation(value = "根据ID更新提现记录")
	@RequestMapping(value = "withdraw/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody Withdraw withdraw) {
		if (id != null && withdraw != null) {
			withdraw.setId(id);
			withdrawService.updateIgnoreNull(withdraw);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除提现记录")
	@RequestMapping(value = "withdraw/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		withdrawService.delete(new Withdraw(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setWithdrawService(
			@Qualifier("withdrawService") IWithdrawService withdrawService) {
		this.withdrawService = withdrawService;
	}


}
