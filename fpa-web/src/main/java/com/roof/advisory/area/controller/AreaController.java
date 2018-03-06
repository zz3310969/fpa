package com.roof.advisory.area.controller;

import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;

import com.roof.advisory.area.entity.AreaTreeSelect;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.advisory.area.entity.Area;
import com.roof.advisory.area.entity.AreaVo;
import com.roof.advisory.area.service.api.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "area", description = "z_area管理")
@Controller
@RequestMapping("advisory")
public class AreaController {
	private IAreaService areaService;

	@ApiOperation(value = "获得z_area基础信息")
	@RequestMapping(value = "area/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得z_area分页列表")
    @RequestMapping(value = "area", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(Area area, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = areaService.page(page, area);
	    return new Result(Result.SUCCESS, page);
	}

	@ApiOperation(value = "获得z_area分页列表")
	@RequestMapping(value = "area/tree", method = {RequestMethod.GET})
	public @ResponseBody Result<List<AreaTreeSelect>> tree(Area area, HttpServletRequest request) {
		return new Result(Result.SUCCESS,areaService.tree(area));
	}


    @ApiOperation(value = "新增z_area")
	@RequestMapping(value = "area", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody Area area) {
		if (area != null) {
			areaService.save(area);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载z_area")
    @RequestMapping(value = "area/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<AreaVo> load(@PathVariable Long id) {
		AreaVo areaVo = areaService.load(new Area(id));
        return new Result(Result.SUCCESS,areaVo);
    }

	@ApiOperation(value = "根据ID更新z_area")
	@RequestMapping(value = "area/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody Area area) {
		if (id != null && area != null) {
			area.setId(id);
			areaService.updateIgnoreNull(area);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除z_area")
	@RequestMapping(value = "area/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		areaService.delete(new Area(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setAreaService(
			@Qualifier("areaService") IAreaService areaService) {
		this.areaService = areaService;
	}


}
