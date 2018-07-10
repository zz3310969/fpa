package com.roof.advisory.wechat.advisoryproduct.controller;

import com.google.common.collect.Maps;
import com.roof.advisory.DefaultStatusEnum;
import com.roof.advisory.advisorymodes.entity.AdvisoryModes;
import com.roof.advisory.advisorymodes.entity.AdvisoryModesVo;
import com.roof.advisory.advisorymodes.service.api.IAdvisoryModesService;
import com.roof.advisory.advisorypricing.entity.AdvisoryPricing;
import com.roof.advisory.advisorypricing.entity.AdvisoryPricingVo;
import com.roof.advisory.advisorypricing.service.api.IAdvisoryPricingService;
import com.roof.advisory.advisoryproduct.entity.AdvisoryProduct;
import com.roof.advisory.advisoryproduct.entity.AdvisoryProductVo;
import com.roof.advisory.advisoryproduct.service.api.IAdvisoryProductService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Api(value = "advisoryproduct", description = "服务产品管理")
@Controller
@RequestMapping("/fpa/wechat/advisory")
public class AdvisoryProductController {
    private IAdvisoryProductService advisoryProductService;

    @ApiOperation(value = "获得服务产品分页列表")
    @RequestMapping(value = "advisoryproduct", method = {RequestMethod.GET})
    public @ResponseBody
    Result<Page> list(AdvisoryProduct advisoryProduct, HttpServletRequest request) {
        Page page = PageUtils.createPage(request);
        page = advisoryProductService.page(page, advisoryProduct);
        return new Result(Result.SUCCESS, page);
    }


    @ApiOperation(value = "根据ID加载服务产品")
    @RequestMapping(value = "advisoryproduct/{id}", method = {RequestMethod.GET})
    public @ResponseBody
    Result<AdvisoryProductVo> load(@PathVariable Long id) {
        AdvisoryProductVo advisoryProductVo = advisoryProductService.load(new AdvisoryProduct(id));
        return new Result(Result.SUCCESS, advisoryProductVo);
    }


    @Autowired(required = true)
    public void setAdvisoryProductService(
            @Qualifier("advisoryProductService") IAdvisoryProductService advisoryProductService
    ) {
        this.advisoryProductService = advisoryProductService;
    }


}
