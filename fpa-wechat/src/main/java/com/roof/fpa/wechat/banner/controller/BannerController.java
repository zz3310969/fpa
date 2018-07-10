package com.roof.fpa.wechat.banner.controller;

import com.google.common.collect.Maps;
import com.roof.fpa.banner.entity.Banner;
import com.roof.fpa.banner.entity.BannerVo;
import com.roof.fpa.banner.service.api.IBannerService;
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
import java.util.Map;

@Controller
@RequestMapping("fpa/wechat")
public class BannerController {
    private IBannerService bannerService;


    @ApiOperation(value = "获得首页banner分页列表")
    @RequestMapping(value = "banner", method = {RequestMethod.GET})
    public @ResponseBody
    Result<Page> list(Banner banner, HttpServletRequest request) {
        Page page = PageUtils.createPage(request);
        page = bannerService.page(page, banner);
        return new Result(Result.SUCCESS, page);
    }


    @ApiOperation(value = "根据ID加载首页banner")
    @RequestMapping(value = "banner/{id}", method = {RequestMethod.GET})
    public @ResponseBody
    Result<BannerVo> load(@PathVariable Long id) {
        BannerVo bannerVo = bannerService.load(new Banner(id));
        return new Result(Result.SUCCESS, bannerVo);
    }


    @Autowired(required = true)
    public void setBannerService(
            @Qualifier("bannerService") IBannerService bannerService
    ) {
        this.bannerService = bannerService;
    }


}
