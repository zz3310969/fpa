package com.roof.advisory.advisoryproduct.controller;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import javax.servlet.http.HttpServletRequest;

import com.roof.advisory.DefaultStatusEnum;
import com.roof.advisory.advisorymodes.entity.AdvisoryModes;
import com.roof.advisory.advisorymodes.entity.AdvisoryModesVo;
import com.roof.advisory.advisorymodes.service.api.IAdvisoryModesService;
import com.roof.advisory.advisorypricing.entity.AdvisoryPricing;
import com.roof.advisory.advisorypricing.entity.AdvisoryPricingVo;
import com.roof.advisory.advisorypricing.service.api.IAdvisoryPricingService;
import com.roof.advisory.application.entity.Application;
import com.roof.advisory.application.entity.ApplicationVo;
import com.roof.advisory.application.service.api.IApplicationService;
import com.roof.advisory.consultant.entity.Consultant;
import com.roof.advisory.consultant.entity.ConsultantVo;
import com.roof.advisory.consultant.service.api.IConsultantService;
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

    @Autowired
    private IApplicationService applicationService;
    @Autowired
    private IAdvisoryModesService advisoryModesService;

    @Autowired
    private IConsultantService consultantService;

    @Autowired
    private IAdvisoryPricingService advisoryPricingService;

    @ApiOperation(value = "获得服务产品基础信息")
    @RequestMapping(value = "advisoryproduct/base", method = {RequestMethod.GET})
    public @ResponseBody
    Result<Map<String, Object>> base(HttpServletRequest request) {
        Map<String, Object> map = Maps.newHashMap();
        List<ApplicationVo> apps = applicationService.selectForList(new Application());
        map.put("apps", apps);
        List<AdvisoryModesVo> modes = advisoryModesService.selectForList(new AdvisoryModes());
        map.put("modes", modes);
        //咨询师
        Consultant consultant = new Consultant();
        consultant.setState(1);
        List<ConsultantVo> consultants = consultantService.selectForList(consultant);
        map.put("consultants", consultants);
        //咨询定价
        AdvisoryPricing advisoryPricing = new AdvisoryPricing();
        advisoryPricing.setState(1);
        List<AdvisoryPricingVo> pricings = advisoryPricingService.selectForList(advisoryPricing);
        map.put("pricings", pricings);
        //
        DefaultStatusEnum[] status = DefaultStatusEnum.values();
        map.put("status", status);
        return new Result(Result.SUCCESS, map);
    }

    @ApiOperation(value = "获得服务产品分页列表")
    @RequestMapping(value = "advisoryproduct", method = {RequestMethod.GET})
    public @ResponseBody
    Result<Page> list(AdvisoryProduct advisoryProduct, HttpServletRequest request) {
        Page page = PageUtils.createPage(request);
        page = advisoryProductService.page(page, advisoryProduct);
        return new Result(Result.SUCCESS, page);
    }


    @ApiOperation(value = "新增服务产品")
    @RequestMapping(value = "advisoryproduct", method = {RequestMethod.POST})
    public @ResponseBody
    Result create(@RequestBody AdvisoryProduct advisoryProduct) {
        if (advisoryProduct != null) {
            advisoryProductService.save(advisoryProduct);
            return new Result("保存成功!");
        } else {
            return new Result(Result.FAIL, "数据传输失败!");
        }
    }

    @ApiOperation(value = "根据ID加载服务产品")
    @RequestMapping(value = "advisoryproduct/{id}", method = {RequestMethod.GET})
    public @ResponseBody
    Result<AdvisoryProductVo> load(@PathVariable Long id) {
        AdvisoryProductVo advisoryProductVo = advisoryProductService.load(new AdvisoryProduct(id));
        return new Result(Result.SUCCESS, advisoryProductVo);
    }

    @ApiOperation(value = "根据ID更新服务产品")
    @RequestMapping(value = "advisoryproduct/{id}", method = {RequestMethod.PUT})
    public @ResponseBody
    Result update(@PathVariable Long id, @RequestBody AdvisoryProduct advisoryProduct) {
        if (id != null && advisoryProduct != null) {
            advisoryProduct.setId(id);
            advisoryProductService.updateIgnoreNull(advisoryProduct);
            return new Result("保存成功!");
        } else {
            return new Result(Result.FAIL, "数据传输失败!");
        }
    }

    @ApiOperation(value = "根据ID删除服务产品")
    @RequestMapping(value = "advisoryproduct/{id}", method = {RequestMethod.DELETE})
    public @ResponseBody
    Result delete(@PathVariable Long id) {
        AdvisoryProduct advisoryProduct = new AdvisoryProduct(id);
        advisoryProduct.setState(0);
        advisoryProductService.updateIgnoreNull(advisoryProduct);
        return new Result("删除成功!");
    }

    @Autowired(required = true)
    public void setAdvisoryProductService(
            @Qualifier("advisoryProductService") IAdvisoryProductService advisoryProductService
    ) {
        this.advisoryProductService = advisoryProductService;
    }


}
