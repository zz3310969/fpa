package com.roof.advisory.consultant.controller;

import java.util.*;
import java.util.stream.Collectors;

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
import com.roof.fpa.cache.api.ICacheHander;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.advisory.consultant.entity.Consultant;
import com.roof.advisory.consultant.entity.ConsultantVo;
import com.roof.advisory.consultant.service.api.IConsultantService;
import org.roof.web.dictionary.entity.Dictionary;
import org.roof.web.dictionary.service.impl.DictionaryService;
import org.roof.web.user.entity.User;
import org.roof.web.user.service.api.BaseUserContext;
import org.springframework.beans.BeanUtils;
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

	@Autowired
	private DictionaryService dictionaryService;

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
		List<Map<String,String>> maps = new ArrayList<>(advisoryThemes.size());
		for (AdvisoryThemeVo advisoryThemeVo:advisoryThemes){
			Map<String,String> map1 = new HashMap<>(2);
			map1.put("label",advisoryThemeVo.getName());
			map1.put("value",advisoryThemeVo.getName());
			maps.add(map1);
		}
		map.put("themeList",maps);

		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得咨询师分页列表")
    @RequestMapping(value = "consultant", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(Consultant consultant, HttpServletRequest request) {

		User user = (User) BaseUserContext.getCurrentUser(request);
		Consultant consultant1 = new Consultant();
		consultant1.setUserId(user.getId());
		ConsultantVo consultantVo = consultantService.selectForObject(consultant1);
		if(consultantVo != null){
			consultant.setId(consultantVo.getId());
		}
		Page page = PageUtils.createPage(request);
	    page = consultantService.page(page, consultant);
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增咨询师")
	@RequestMapping(value = "consultant", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody ConsultantVo consultantVo) {
		if (consultantVo != null) {
			Consultant consultant = new Consultant();
			BeanUtils.copyProperties(consultantVo,consultant);

			if(consultantVo.getThemeList() != null ){
				consultant.setIntroduction(consultantVo.getThemeList().stream()
						.collect(Collectors.joining(",")));
			}
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
		if(StringUtils.isNotEmpty(consultantVo.getThemes())){
			consultantVo.setThemeList(Arrays.asList(consultantVo.getThemes().split(",")));

		}
        return new Result(Result.SUCCESS,consultantVo);
    }

	@ApiOperation(value = "根据ID更新咨询师")
	@RequestMapping(value = "consultant/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody ConsultantVo consultantVo) {
		if (id != null && consultantVo != null) {
			consultantVo.setId(id);
			Consultant consultant = new Consultant();
			BeanUtils.copyProperties(consultantVo,consultant);

			if(consultantVo.getThemeList() != null ){
				consultant.setThemes(consultantVo.getThemeList().stream()
						.collect(Collectors.joining(",")));
			}

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
