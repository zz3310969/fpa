package com.roof.fpa.accountdetail.controller;

import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.fpa.accountdetail.entity.AccountDetail;
import com.roof.fpa.accountdetail.entity.AccountDetailVo;
import com.roof.fpa.accountdetail.service.api.IAccountDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "accountdetail", description = "账户金额变更详情管理")
@Controller
@RequestMapping("fpa")
public class AccountDetailController {
	private IAccountDetailService accountDetailService;

	@ApiOperation(value = "获得账户金额变更详情基础信息")
	@RequestMapping(value = "accountdetail/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得账户金额变更详情分页列表")
    @RequestMapping(value = "accountdetail", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(AccountDetail accountDetail, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = accountDetailService.page(page, accountDetail);
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增账户金额变更详情")
	@RequestMapping(value = "accountdetail", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody AccountDetail accountDetail) {
		if (accountDetail != null) {
			accountDetailService.save(accountDetail);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载账户金额变更详情")
    @RequestMapping(value = "accountdetail/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<AccountDetailVo> load(@PathVariable Long id) {
		AccountDetailVo accountDetailVo = accountDetailService.load(new AccountDetail(id));
        return new Result(Result.SUCCESS,accountDetailVo);
    }

	@ApiOperation(value = "根据ID更新账户金额变更详情")
	@RequestMapping(value = "accountdetail/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody AccountDetail accountDetail) {
		if (id != null && accountDetail != null) {
			accountDetail.setId(id);
			accountDetailService.updateIgnoreNull(accountDetail);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除账户金额变更详情")
	@RequestMapping(value = "accountdetail/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		accountDetailService.delete(new AccountDetail(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setAccountDetailService(
			@Qualifier("accountDetailService") IAccountDetailService accountDetailService) {
		this.accountDetailService = accountDetailService;
	}


}
