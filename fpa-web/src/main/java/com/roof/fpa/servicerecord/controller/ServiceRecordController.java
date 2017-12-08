package com.roof.fpa.servicerecord.controller;

import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.fpa.servicerecord.entity.ServiceRecord;
import com.roof.fpa.servicerecord.entity.ServiceRecordVo;
import com.roof.fpa.servicerecord.service.api.IServiceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "servicerecord", description = "服务记录管理")
@Controller
@RequestMapping("fpa")
public class ServiceRecordController {
	private IServiceRecordService serviceRecordService;

	@ApiOperation(value = "获得服务记录基础信息")
	@RequestMapping(value = "servicerecord/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得服务记录分页列表")
    @RequestMapping(value = "servicerecord", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(ServiceRecord serviceRecord, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = serviceRecordService.page(page, serviceRecord);
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增服务记录")
	@RequestMapping(value = "servicerecord", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody ServiceRecord serviceRecord) {
		if (serviceRecord != null) {
			serviceRecordService.save(serviceRecord);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载服务记录")
    @RequestMapping(value = "servicerecord/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<ServiceRecordVo> load(@PathVariable Long id) {
		ServiceRecordVo serviceRecordVo = serviceRecordService.load(new ServiceRecord(id));
        return new Result(Result.SUCCESS,serviceRecordVo);
    }

	@ApiOperation(value = "根据ID更新服务记录")
	@RequestMapping(value = "servicerecord/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody ServiceRecord serviceRecord) {
		if (id != null && serviceRecord != null) {
			serviceRecord.setId(id);
			serviceRecordService.updateIgnoreNull(serviceRecord);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除服务记录")
	@RequestMapping(value = "servicerecord/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		serviceRecordService.delete(new ServiceRecord(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setServiceRecordService(
			@Qualifier("serviceRecordService") IServiceRecordService serviceRecordService) {
		this.serviceRecordService = serviceRecordService;
	}


}
