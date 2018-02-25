package com.roof.advisory.consultant.controller;

import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;

import com.roof.advisory.DefaultStatusEnum;
import com.roof.advisory.advisorytheme.entity.AdvisoryTheme;
import com.roof.advisory.advisorytheme.entity.AdvisoryThemeVo;
import com.roof.advisory.advisorytheme.service.api.IAdvisoryThemeService;
import com.roof.advisory.application.entity.Application;
import com.roof.advisory.application.entity.ApplicationVo;
import com.roof.advisory.application.service.api.IApplicationService;
import com.roof.advisory.level.entity.Level;
import com.roof.advisory.level.entity.LevelVo;
import com.roof.advisory.level.service.api.ILevelService;
import com.roof.fpa.DefaultStateEnum;
import com.roof.fpa.GenderEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.advisory.consultant.entity.Consultant;
import com.roof.advisory.consultant.entity.ConsultantVo;
import com.roof.advisory.consultant.service.api.IConsultantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "consultant", description = "咨询师管理")
@Controller
@RequestMapping("advisory")
public class ConsultantController {
	private IConsultantService consultantService;
	@Autowired
	private IApplicationService applicationService;
	@Autowired
	private IAdvisoryThemeService advisoryThemeService;
	@Autowired
	private ILevelService levelService;

	@ApiOperation(value = "获得咨询师基础信息")
	@RequestMapping(value = "consultant/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		List<ApplicationVo> apps = applicationService.selectForList(new Application());
		map.put("apps",apps);
		List<LevelVo> levels = levelService.selectForList(new Level());
		map.put("levels",levels);
		List<AdvisoryThemeVo> advisoryThemes = advisoryThemeService.selectForList(new AdvisoryTheme());
		map.put("advisoryThemes",advisoryThemes);
		GenderEnum[] genderEnums = GenderEnum.AllGender();
		map.put("genders",genderEnums);
		DefaultStatusEnum[] status = DefaultStatusEnum.values();
		map.put("status",status);
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得咨询师分页列表")
    @RequestMapping(value = "consultant", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(Consultant consultant, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = consultantService.page(page, consultant);
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增咨询师")
	@RequestMapping(value = "consultant", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody Consultant consultant) {
		if (consultant != null) {
			consultantService.save(consultant);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载咨询师")
    @RequestMapping(value = "consultant/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<ConsultantVo> load(@PathVariable Long id) {
		ConsultantVo consultantVo = consultantService.load(new Consultant(id));
        return new Result(Result.SUCCESS,consultantVo);
    }

	@ApiOperation(value = "根据ID更新咨询师")
	@RequestMapping(value = "consultant/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody Consultant consultant) {
		if (id != null && consultant != null) {
			consultant.setId(id);
			consultantService.updateIgnoreNull(consultant);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除咨询师")
	@RequestMapping(value = "consultant/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		consultantService.delete(new Consultant(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setConsultantService(
			@Qualifier("consultantService") IConsultantService consultantService) {
		this.consultantService = consultantService;
	}


}
