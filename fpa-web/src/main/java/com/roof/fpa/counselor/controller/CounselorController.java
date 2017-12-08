package com.roof.fpa.counselor.controller;

import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.fpa.counselor.entity.Counselor;
import com.roof.fpa.counselor.entity.CounselorVo;
import com.roof.fpa.counselor.service.api.ICounselorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "counselor", description = "咨询师管理")
@Controller
@RequestMapping("fpa")
public class CounselorController {
	private ICounselorService counselorService;

	@ApiOperation(value = "获得咨询师基础信息")
	@RequestMapping(value = "counselor/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得咨询师分页列表")
    @RequestMapping(value = "counselor", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(Counselor counselor, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = counselorService.page(page, counselor);
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增咨询师")
	@RequestMapping(value = "counselor", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody Counselor counselor) {
		if (counselor != null) {
			counselorService.save(counselor);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载咨询师")
    @RequestMapping(value = "counselor/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<CounselorVo> load(@PathVariable Long id) {
		CounselorVo counselorVo = counselorService.load(new Counselor(id));
        return new Result(Result.SUCCESS,counselorVo);
    }

	@ApiOperation(value = "根据ID更新咨询师")
	@RequestMapping(value = "counselor/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody Counselor counselor) {
		if (id != null && counselor != null) {
			counselor.setId(id);
			counselorService.updateIgnoreNull(counselor);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除咨询师")
	@RequestMapping(value = "counselor/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		counselorService.delete(new Counselor(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setCounselorService(
			@Qualifier("counselorService") ICounselorService counselorService) {
		this.counselorService = counselorService;
	}


}
