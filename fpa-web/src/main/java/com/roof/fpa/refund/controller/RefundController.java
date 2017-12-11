package com.roof.fpa.refund.controller;

import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.fpa.refund.entity.Refund;
import com.roof.fpa.refund.entity.RefundVo;
import com.roof.fpa.refund.service.api.IRefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "refund", description = "退款记录管理")
@Controller
@RequestMapping("fpa")
public class RefundController {
	private IRefundService refundService;

	@ApiOperation(value = "获得退款记录基础信息")
	@RequestMapping(value = "refund/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得退款记录分页列表")
    @RequestMapping(value = "refund", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(Refund refund, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = refundService.page(page, refund);
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增退款记录")
	@RequestMapping(value = "refund", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody Refund refund) {
		if (refund != null) {
			refundService.save(refund);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载退款记录")
    @RequestMapping(value = "refund/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<RefundVo> load(@PathVariable Long id) {
		RefundVo refundVo = refundService.load(new Refund(id));
        return new Result(Result.SUCCESS,refundVo);
    }

	@ApiOperation(value = "根据ID更新退款记录")
	@RequestMapping(value = "refund/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody Refund refund) {
		if (id != null && refund != null) {
			refund.setId(id);
			refundService.updateIgnoreNull(refund);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除退款记录")
	@RequestMapping(value = "refund/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		refundService.delete(new Refund(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setRefundService(
			@Qualifier("refundService") IRefundService refundService) {
		this.refundService = refundService;
	}


}
