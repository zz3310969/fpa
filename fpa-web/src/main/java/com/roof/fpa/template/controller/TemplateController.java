package com.roof.fpa.template.controller;

import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.fpa.template.entity.Template;
import com.roof.fpa.template.entity.TemplateVo;
import com.roof.fpa.template.service.api.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "template", description = "模板管理")
@Controller
@RequestMapping("fpa")
public class TemplateController {
	private ITemplateService templateService;

	@ApiOperation(value = "获得模板基础信息")
	@RequestMapping(value = "template/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得模板分页列表")
    @RequestMapping(value = "template", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(Template template, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = templateService.page(page, template);
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增模板")
	@RequestMapping(value = "template", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody Template template) {
		if (template != null) {
			templateService.save(template);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载模板")
    @RequestMapping(value = "template/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<TemplateVo> load(@PathVariable Long id) {
		TemplateVo templateVo = templateService.load(new Template(id));
        return new Result(Result.SUCCESS,templateVo);
    }

	@ApiOperation(value = "根据ID更新模板")
	@RequestMapping(value = "template/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody Template template) {
		if (id != null && template != null) {
			template.setId(id);
			templateService.updateIgnoreNull(template);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除模板")
	@RequestMapping(value = "template/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		templateService.delete(new Template(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setTemplateService(
			@Qualifier("templateService") ITemplateService templateService) {
		this.templateService = templateService;
	}


}
