package com.roof.advisory.level.controller;

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
import com.roof.advisory.level.entity.Level;
import com.roof.advisory.level.entity.LevelVo;
import com.roof.advisory.level.service.api.ILevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "level", description = "咨询师等级管理")
@Controller
@RequestMapping("advisory")
public class LevelController {
	private ILevelService levelService;
	@Autowired
	private IApplicationService applicationService;

	@ApiOperation(value = "获得咨询师等级基础信息")
	@RequestMapping(value = "level/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		List<ApplicationVo> apps = applicationService.selectForList(new Application());
		map.put("apps",apps);
		DefaultStatusEnum[] status = DefaultStatusEnum.values();
		map.put("status",status);
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得咨询师等级分页列表")
    @RequestMapping(value = "level", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(Level level, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = levelService.page(page, level);
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增咨询师等级")
	@RequestMapping(value = "level", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody Level level) {
		if (level != null) {
			levelService.save(level);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载咨询师等级")
    @RequestMapping(value = "level/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<LevelVo> load(@PathVariable Long id) {
		LevelVo levelVo = levelService.load(new Level(id));
        return new Result(Result.SUCCESS,levelVo);
    }

	@ApiOperation(value = "根据ID更新咨询师等级")
	@RequestMapping(value = "level/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody Level level) {
		if (id != null && level != null) {
			level.setId(id);
			levelService.updateIgnoreNull(level);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除咨询师等级")
	@RequestMapping(value = "level/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		levelService.delete(new Level(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setLevelService(
			@Qualifier("levelService") ILevelService levelService) {
		this.levelService = levelService;
	}


}
