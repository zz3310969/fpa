package com.roof.advisory.advisorytheme.controller;

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
import com.roof.advisory.advisorytheme.entity.AdvisoryTheme;
import com.roof.advisory.advisorytheme.entity.AdvisoryThemeVo;
import com.roof.advisory.advisorytheme.service.api.IAdvisoryThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "advisorytheme", description = "服务主题管理")
@Controller
@RequestMapping("advisory")
public class AdvisoryThemeController {
	private IAdvisoryThemeService advisoryThemeService;
	@Autowired
	private IApplicationService applicationService;

	@ApiOperation(value = "获得服务主题基础信息")
	@RequestMapping(value = "advisorytheme/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		List<ApplicationVo> apps = applicationService.selectForList(new Application());
		map.put("apps",apps);
		DefaultStatusEnum[] status = DefaultStatusEnum.values();
		map.put("status",status);
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得服务主题分页列表")
    @RequestMapping(value = "advisorytheme", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(AdvisoryTheme advisoryTheme, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = advisoryThemeService.page(page, advisoryTheme);
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增服务主题")
	@RequestMapping(value = "advisorytheme", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody AdvisoryTheme advisoryTheme) {
		if (advisoryTheme != null) {
			advisoryThemeService.save(advisoryTheme);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载服务主题")
    @RequestMapping(value = "advisorytheme/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<AdvisoryThemeVo> load(@PathVariable Long id) {
		AdvisoryThemeVo advisoryThemeVo = advisoryThemeService.load(new AdvisoryTheme(id));
        return new Result(Result.SUCCESS,advisoryThemeVo);
    }

	@ApiOperation(value = "根据ID更新服务主题")
	@RequestMapping(value = "advisorytheme/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody AdvisoryTheme advisoryTheme) {
		if (id != null && advisoryTheme != null) {
			advisoryTheme.setId(id);
			advisoryThemeService.updateIgnoreNull(advisoryTheme);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除服务主题")
	@RequestMapping(value = "advisorytheme/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		advisoryThemeService.delete(new AdvisoryTheme(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setAdvisoryThemeService(
			@Qualifier("advisoryThemeService") IAdvisoryThemeService advisoryThemeService) {
		this.advisoryThemeService = advisoryThemeService;
	}


}
