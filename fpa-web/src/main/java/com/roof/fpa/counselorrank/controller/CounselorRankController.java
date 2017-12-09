package com.roof.fpa.counselorrank.controller;

import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.fpa.counselorrank.entity.CounselorRank;
import com.roof.fpa.counselorrank.entity.CounselorRankVo;
import com.roof.fpa.counselorrank.service.api.ICounselorRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "counselorrank", description = "咨询师等级管理")
@Controller
@RequestMapping("fpa")
public class CounselorRankController {
	private ICounselorRankService counselorRankService;

	@ApiOperation(value = "获得咨询师等级基础信息")
	@RequestMapping(value = "counselorrank/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得咨询师等级分页列表")
    @RequestMapping(value = "counselorrank", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(CounselorRank counselorRank, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = counselorRankService.page(page, counselorRank);
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增咨询师等级")
	@RequestMapping(value = "counselorrank", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody CounselorRank counselorRank) {
		if (counselorRank != null) {
			counselorRankService.save(counselorRank);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载咨询师等级")
    @RequestMapping(value = "counselorrank/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<CounselorRankVo> load(@PathVariable Long id) {
		CounselorRankVo counselorRankVo = counselorRankService.load(new CounselorRank(id));
        return new Result(Result.SUCCESS,counselorRankVo);
    }

	@ApiOperation(value = "根据ID更新咨询师等级")
	@RequestMapping(value = "counselorrank/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody CounselorRank counselorRank) {
		if (id != null && counselorRank != null) {
			counselorRank.setId(id);
			counselorRankService.updateIgnoreNull(counselorRank);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除咨询师等级")
	@RequestMapping(value = "counselorrank/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		counselorRankService.delete(new CounselorRank(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setCounselorRankService(
			@Qualifier("counselorRankService") ICounselorRankService counselorRankService) {
		this.counselorRankService = counselorRankService;
	}


}
