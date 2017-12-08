package com.roof.fpa.customerwords.controller;

import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.fpa.customerwords.entity.CustomerWords;
import com.roof.fpa.customerwords.entity.CustomerWordsVo;
import com.roof.fpa.customerwords.service.api.ICustomerWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "customerwords", description = "客户留言管理")
@Controller
@RequestMapping("fpa")
public class CustomerWordsController {
	private ICustomerWordsService customerWordsService;

	@ApiOperation(value = "获得客户留言基础信息")
	@RequestMapping(value = "customerwords/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得客户留言分页列表")
    @RequestMapping(value = "customerwords", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(CustomerWords customerWords, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = customerWordsService.page(page, customerWords);
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增客户留言")
	@RequestMapping(value = "customerwords", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody CustomerWords customerWords) {
		if (customerWords != null) {
			customerWordsService.save(customerWords);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载客户留言")
    @RequestMapping(value = "customerwords/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<CustomerWordsVo> load(@PathVariable Long id) {
		CustomerWordsVo customerWordsVo = customerWordsService.load(new CustomerWords(id));
        return new Result(Result.SUCCESS,customerWordsVo);
    }

	@ApiOperation(value = "根据ID更新客户留言")
	@RequestMapping(value = "customerwords/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody CustomerWords customerWords) {
		if (id != null && customerWords != null) {
			customerWords.setId(id);
			customerWordsService.updateIgnoreNull(customerWords);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除客户留言")
	@RequestMapping(value = "customerwords/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		customerWordsService.delete(new CustomerWords(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setCustomerWordsService(
			@Qualifier("customerWordsService") ICustomerWordsService customerWordsService) {
		this.customerWordsService = customerWordsService;
	}


}
