package com.roof.advisory.advisoryproduct.controller;

import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.advisory.advisoryproduct.entity.AdvisoryProduct;
import com.roof.advisory.advisoryproduct.entity.AdvisoryProductVo;
import com.roof.advisory.advisoryproduct.service.api.IAdvisoryProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "advisoryproduct", description = "服务产品管理")
@Controller
@RequestMapping("advisory")
public class AdvisoryProductController {
	private IAdvisoryProductService advisoryProductService;

	@ApiOperation(value = "获得服务产品基础信息")
	@RequestMapping(value = "advisoryproduct/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得服务产品分页列表")
    @RequestMapping(value = "advisoryproduct", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(AdvisoryProduct advisoryProduct, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = advisoryProductService.page(page, advisoryProduct);
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增服务产品")
	@RequestMapping(value = "advisoryproduct", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody AdvisoryProduct advisoryProduct) {
		if (advisoryProduct != null) {
			advisoryProductService.save(advisoryProduct);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载服务产品")
    @RequestMapping(value = "advisoryproduct/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<AdvisoryProductVo> load(@PathVariable Long id) {
		AdvisoryProductVo advisoryProductVo = advisoryProductService.load(new AdvisoryProduct(id));
        return new Result(Result.SUCCESS,advisoryProductVo);
    }

	@ApiOperation(value = "根据ID更新服务产品")
	@RequestMapping(value = "advisoryproduct/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody AdvisoryProduct advisoryProduct) {
		if (id != null && advisoryProduct != null) {
			advisoryProduct.setId(id);
			advisoryProductService.updateIgnoreNull(advisoryProduct);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除服务产品")
	@RequestMapping(value = "advisoryproduct/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		advisoryProductService.delete(new AdvisoryProduct(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setAdvisoryProductService(
			@Qualifier("advisoryProductService") IAdvisoryProductService advisoryProductService) {
		this.advisoryProductService = advisoryProductService;
	}


}
