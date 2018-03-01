package com.roof.advisory.applicationuser.controller;

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
import com.roof.advisory.applicationuser.entity.ApplicationUser;
import com.roof.advisory.applicationuser.entity.ApplicationUserVo;
import com.roof.advisory.applicationuser.service.api.IApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "applicationuser", description = "接入系统用户管理")
@Controller
@RequestMapping("advisory")
public class ApplicationUserController {
	private IApplicationUserService applicationUserService;
	@Autowired
	private IApplicationService applicationService;

	@ApiOperation(value = "获得接入系统用户基础信息")
	@RequestMapping(value = "applicationuser/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		List<ApplicationVo> apps = applicationService.selectForList(new Application());
		map.put("apps",apps);
		DefaultStatusEnum[] status = DefaultStatusEnum.values();
		map.put("status",status);
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得接入系统用户分页列表")
    @RequestMapping(value = "applicationuser", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(ApplicationUser applicationUser, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = applicationUserService.page(page, applicationUser);
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增接入系统用户")
	@RequestMapping(value = "applicationuser", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody ApplicationUser applicationUser) {
		if (applicationUser != null) {
			applicationUserService.save(applicationUser);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载接入系统用户")
    @RequestMapping(value = "applicationuser/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<ApplicationUserVo> load(@PathVariable Long id) {
		ApplicationUserVo applicationUserVo = applicationUserService.load(new ApplicationUser(id));
        return new Result(Result.SUCCESS,applicationUserVo);
    }

	@ApiOperation(value = "根据ID更新接入系统用户")
	@RequestMapping(value = "applicationuser/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody ApplicationUser applicationUser) {
		if (id != null && applicationUser != null) {
			applicationUser.setId(id);
			applicationUserService.updateIgnoreNull(applicationUser);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除接入系统用户")
	@RequestMapping(value = "applicationuser/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		applicationUserService.delete(new ApplicationUser(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setApplicationUserService(
			@Qualifier("applicationUserService") IApplicationUserService applicationUserService) {
		this.applicationUserService = applicationUserService;
	}


}
