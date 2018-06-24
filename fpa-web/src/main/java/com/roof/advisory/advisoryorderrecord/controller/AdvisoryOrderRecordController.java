package com.roof.advisory.advisoryorderrecord.controller;

import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.advisory.advisoryorderrecord.entity.AdvisoryOrderRecord;
import com.roof.advisory.advisoryorderrecord.entity.AdvisoryOrderRecordVo;
import com.roof.advisory.advisoryorderrecord.service.api.IAdvisoryOrderRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "advisoryorderrecord", description = "订单变更记录表管理")
@Controller
@RequestMapping("fpa")
public class AdvisoryOrderRecordController {
	private IAdvisoryOrderRecordService advisoryOrderRecordService;

	@ApiOperation(value = "获得订单变更记录表基础信息")
	@RequestMapping(value = "advisoryorderrecord/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得订单变更记录表分页列表")
    @RequestMapping(value = "advisoryorderrecord", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(AdvisoryOrderRecord advisoryOrderRecord, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = advisoryOrderRecordService.page(page, advisoryOrderRecord);
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增订单变更记录表")
	@RequestMapping(value = "advisoryorderrecord", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody AdvisoryOrderRecord advisoryOrderRecord) {
		if (advisoryOrderRecord != null) {
			advisoryOrderRecordService.save(advisoryOrderRecord);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载订单变更记录表")
    @RequestMapping(value = "advisoryorderrecord/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<AdvisoryOrderRecordVo> load(@PathVariable Long id) {
		AdvisoryOrderRecordVo advisoryOrderRecordVo = advisoryOrderRecordService.load(new AdvisoryOrderRecord(id));
        return new Result(Result.SUCCESS,advisoryOrderRecordVo);
    }

	@ApiOperation(value = "根据ID更新订单变更记录表")
	@RequestMapping(value = "advisoryorderrecord/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody AdvisoryOrderRecord advisoryOrderRecord) {
		if (id != null && advisoryOrderRecord != null) {
			advisoryOrderRecord.setId(id);
			advisoryOrderRecordService.updateIgnoreNull(advisoryOrderRecord);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除订单变更记录表")
	@RequestMapping(value = "advisoryorderrecord/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		advisoryOrderRecordService.delete(new AdvisoryOrderRecord(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setAdvisoryOrderRecordService(
			@Qualifier("advisoryOrderRecordService") IAdvisoryOrderRecordService advisoryOrderRecordService) {
		this.advisoryOrderRecordService = advisoryOrderRecordService;
	}


}
