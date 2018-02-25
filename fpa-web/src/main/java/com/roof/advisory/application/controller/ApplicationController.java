package com.roof.advisory.application.controller;

import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;

import com.roof.advisory.DefaultStatusEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.advisory.application.entity.Application;
import com.roof.advisory.application.entity.ApplicationVo;
import com.roof.advisory.application.service.api.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "application", description = "接入系统管理")
@Controller
@RequestMapping("advisory")
public class ApplicationController {
	private IApplicationService applicationService;

	@ApiOperation(value = "获得接入系统基础信息")
	@RequestMapping(value = "application/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		DefaultStatusEnum[] status = DefaultStatusEnum.values();
		map.put("status",status);
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得接入系统分页列表")
    @RequestMapping(value = "application", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(Application application, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = applicationService.page(page, application);
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增接入系统")
	@RequestMapping(value = "application", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody Application application) {
		if (application != null) {
			applicationService.save(application);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载接入系统")
    @RequestMapping(value = "application/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<ApplicationVo> load(@PathVariable Long id) {
		ApplicationVo applicationVo = applicationService.load(new Application(id));
        return new Result(Result.SUCCESS,applicationVo);
    }

	@ApiOperation(value = "根据ID更新接入系统")
	@RequestMapping(value = "application/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody Application application) {
		if (id != null && application != null) {
			application.setId(id);
			applicationService.updateIgnoreNull(application);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除接入系统")
	@RequestMapping(value = "application/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		applicationService.delete(new Application(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setApplicationService(
			@Qualifier("applicationService") IApplicationService applicationService) {
		this.applicationService = applicationService;
	}


}
