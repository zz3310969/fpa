package com.roof.advisory.advisorypayrecord.controller;

import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.advisory.advisorypayrecord.entity.AdvisoryPayRecord;
import com.roof.advisory.advisorypayrecord.entity.AdvisoryPayRecordVo;
import com.roof.advisory.advisorypayrecord.service.api.IAdvisoryPayRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "advisorypayrecord", description = "付款记录管理")
@Controller
@RequestMapping("fpa")
public class AdvisoryPayRecordController {
	private IAdvisoryPayRecordService advisoryPayRecordService;

	@ApiOperation(value = "获得付款记录基础信息")
	@RequestMapping(value = "advisorypayrecord/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得付款记录分页列表")
    @RequestMapping(value = "advisorypayrecord", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(AdvisoryPayRecord advisoryPayRecord, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = advisoryPayRecordService.page(page, advisoryPayRecord);
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增付款记录")
	@RequestMapping(value = "advisorypayrecord", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody AdvisoryPayRecord advisoryPayRecord) {
		if (advisoryPayRecord != null) {
			advisoryPayRecordService.save(advisoryPayRecord);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载付款记录")
    @RequestMapping(value = "advisorypayrecord/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<AdvisoryPayRecordVo> load(@PathVariable Long id) {
		AdvisoryPayRecordVo advisoryPayRecordVo = advisoryPayRecordService.load(new AdvisoryPayRecord(id));
        return new Result(Result.SUCCESS,advisoryPayRecordVo);
    }

	@ApiOperation(value = "根据ID更新付款记录")
	@RequestMapping(value = "advisorypayrecord/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody AdvisoryPayRecord advisoryPayRecord) {
		if (id != null && advisoryPayRecord != null) {
			advisoryPayRecord.setId(id);
			advisoryPayRecordService.updateIgnoreNull(advisoryPayRecord);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除付款记录")
	@RequestMapping(value = "advisorypayrecord/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		advisoryPayRecordService.delete(new AdvisoryPayRecord(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setAdvisoryPayRecordService(
			@Qualifier("advisoryPayRecordService") IAdvisoryPayRecordService advisoryPayRecordService) {
		this.advisoryPayRecordService = advisoryPayRecordService;
	}


}
