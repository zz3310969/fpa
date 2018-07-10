package com.roof.fpa.banner.controller;

import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.fpa.banner.entity.Banner;
import com.roof.fpa.banner.entity.BannerVo;
import com.roof.fpa.banner.service.api.IBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "banner", description = "首页banner管理")
@Controller
@RequestMapping("fpa")
public class BannerController {
	private IBannerService bannerService;

	@ApiOperation(value = "获得首页banner基础信息")
	@RequestMapping(value = "banner/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得首页banner分页列表")
    @RequestMapping(value = "banner", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(Banner banner, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = bannerService.page(page, banner);
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增首页banner")
	@RequestMapping(value = "banner", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody Banner banner) {
		if (banner != null) {
			bannerService.save(banner);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载首页banner")
    @RequestMapping(value = "banner/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<BannerVo> load(@PathVariable Long id) {
		BannerVo bannerVo = bannerService.load(new Banner(id));
        return new Result(Result.SUCCESS,bannerVo);
    }

	@ApiOperation(value = "根据ID更新首页banner")
	@RequestMapping(value = "banner/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody Banner banner) {
		if (id != null && banner != null) {
			banner.setId(id);
			bannerService.updateIgnoreNull(banner);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除首页banner")
	@RequestMapping(value = "banner/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		bannerService.delete(new Banner(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setBannerService(
			@Qualifier("bannerService") IBannerService bannerService) {
		this.bannerService = bannerService;
	}


}
