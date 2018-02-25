package com.roof.advisory.advisorymodes.controller;

import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;

import com.roof.advisory.DefaultStatusEnum;
import com.roof.advisory.application.entity.Application;
import com.roof.advisory.application.entity.ApplicationVo;
import com.roof.advisory.application.service.api.IApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.advisory.advisorymodes.entity.AdvisoryModes;
import com.roof.advisory.advisorymodes.entity.AdvisoryModesVo;
import com.roof.advisory.advisorymodes.service.api.IAdvisoryModesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "advisorymodes", description = "咨询模式管理")
@Controller
@RequestMapping("advisory")
public class AdvisoryModesController {
	private IAdvisoryModesService advisoryModesService;
	@Autowired
	private IApplicationService applicationService;

	@ApiOperation(value = "获得咨询模式基础信息")
	@RequestMapping(value = "advisorymodes/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		List<ApplicationVo> apps = applicationService.selectForList(new Application());
		map.put("apps",apps);
		DefaultStatusEnum[] status = DefaultStatusEnum.values();
		map.put("status",status);
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得咨询模式分页列表")
    @RequestMapping(value = "advisorymodes", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(AdvisoryModes advisoryModes, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = advisoryModesService.page(page, advisoryModes);
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增咨询模式")
	@RequestMapping(value = "advisorymodes", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody AdvisoryModes advisoryModes) {
		if (advisoryModes != null) {
			advisoryModesService.save(advisoryModes);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载咨询模式")
    @RequestMapping(value = "advisorymodes/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<AdvisoryModesVo> load(@PathVariable Long id) {
		AdvisoryModesVo advisoryModesVo = advisoryModesService.load(new AdvisoryModes(id));
        return new Result(Result.SUCCESS,advisoryModesVo);
    }

	@ApiOperation(value = "根据ID更新咨询模式")
	@RequestMapping(value = "advisorymodes/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody AdvisoryModes advisoryModes) {
		if (id != null && advisoryModes != null) {
			advisoryModes.setId(id);
			advisoryModesService.updateIgnoreNull(advisoryModes);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除咨询模式")
	@RequestMapping(value = "advisorymodes/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		advisoryModesService.delete(new AdvisoryModes(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setAdvisoryModesService(
			@Qualifier("advisoryModesService") IAdvisoryModesService advisoryModesService) {
		this.advisoryModesService = advisoryModesService;
	}


}
