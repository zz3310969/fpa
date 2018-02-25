package com.roof.advisory.commissionpricing.controller;

import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;

import com.roof.advisory.DefaultStatusEnum;
import com.roof.advisory.advisorymodes.entity.AdvisoryModes;
import com.roof.advisory.advisorymodes.entity.AdvisoryModesVo;
import com.roof.advisory.advisorymodes.service.api.IAdvisoryModesService;
import com.roof.advisory.application.entity.Application;
import com.roof.advisory.application.entity.ApplicationVo;
import com.roof.advisory.application.service.api.IApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.advisory.commissionpricing.entity.CommissionPricing;
import com.roof.advisory.commissionpricing.entity.CommissionPricingVo;
import com.roof.advisory.commissionpricing.service.api.ICommissionPricingService;
import org.roof.web.dictionary.entity.Dictionary;
import org.roof.web.dictionary.service.api.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "commissionpricing", description = "咨询佣金定价管理")
@Controller
@RequestMapping("advisory")
public class CommissionPricingController {
	private ICommissionPricingService commissionPricingService;
	@Autowired
	private IApplicationService applicationService;
	@Autowired
	private IDictionaryService dictionaryService;
	@Autowired
	private IAdvisoryModesService advisoryModesService;

	@ApiOperation(value = "获得咨询佣金定价基础信息")
	@RequestMapping(value = "commissionpricing/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		List<ApplicationVo> apps = applicationService.selectForList(new Application());
		map.put("apps",apps);
		DefaultStatusEnum[] status = DefaultStatusEnum.values();
		map.put("status",status);
		List<Dictionary> fix_types = dictionaryService.findByType("FIX_TYPE");
		map.put("fix_types",fix_types);
		List<AdvisoryModesVo> advisoryModes =advisoryModesService.selectForList(new AdvisoryModes());
		map.put("advisoryModes",advisoryModes);
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得咨询佣金定价分页列表")
    @RequestMapping(value = "commissionpricing", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(CommissionPricing commissionPricing, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = commissionPricingService.page(page, commissionPricing);
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增咨询佣金定价")
	@RequestMapping(value = "commissionpricing", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody CommissionPricing commissionPricing) {
		if (commissionPricing != null) {
			commissionPricingService.save(commissionPricing);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载咨询佣金定价")
    @RequestMapping(value = "commissionpricing/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<CommissionPricingVo> load(@PathVariable Long id) {
		CommissionPricingVo commissionPricingVo = commissionPricingService.load(new CommissionPricing(id));
        return new Result(Result.SUCCESS,commissionPricingVo);
    }

	@ApiOperation(value = "根据ID更新咨询佣金定价")
	@RequestMapping(value = "commissionpricing/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody CommissionPricing commissionPricing) {
		if (id != null && commissionPricing != null) {
			commissionPricing.setId(id);
			commissionPricingService.updateIgnoreNull(commissionPricing);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除咨询佣金定价")
	@RequestMapping(value = "commissionpricing/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		commissionPricingService.delete(new CommissionPricing(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setCommissionPricingService(
			@Qualifier("commissionPricingService") ICommissionPricingService commissionPricingService) {
		this.commissionPricingService = commissionPricingService;
	}


}
