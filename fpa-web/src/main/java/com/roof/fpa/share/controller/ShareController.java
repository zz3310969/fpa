package com.roof.fpa.share.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Maps;
import com.roof.fpa.DefaultStateEnum;
import com.roof.fpa.scene.entity.Scene;
import com.roof.fpa.scene.entity.SceneVo;
import com.roof.fpa.scene.service.api.ISceneService;
import com.roof.fpa.transmittemplate.entity.TransmitTemplate;
import com.roof.fpa.transmittemplate.entity.TransmitTemplateVo;
import com.roof.fpa.transmittemplate.service.api.ITransmitTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.fpa.share.entity.Share;
import com.roof.fpa.share.entity.ShareVo;
import com.roof.fpa.share.service.api.IShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "share", description = "c_share管理")
@Controller
@RequestMapping("fpa")
public class ShareController {
	private IShareService shareService;

	@Autowired
	private ISceneService sceneService;
	@Autowired
	private ITransmitTemplateService transmitTemplateService;

	@ApiOperation(value = "获得c_share基础信息")
	@RequestMapping(value = "share/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		Scene scene = new Scene();
		scene.setState(DefaultStateEnum.usable.getCode());
		List<SceneVo> sceneVos = sceneService.selectForList(scene);
		map.put("scenes",sceneVos);
		TransmitTemplate transmitTemplate = new TransmitTemplate();
		transmitTemplate.setState(DefaultStateEnum.usable.getCode());
		List<TransmitTemplateVo> transmitTemplateVos = transmitTemplateService.selectForList(transmitTemplate);
		map.put("templates",transmitTemplateVos);
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得c_share分页列表")
    @RequestMapping(value = "share", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(ShareVo share, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = shareService.page(page, share);
	    return new Result(Result.SUCCESS, page);
	}

	@ApiOperation(value = "获得c_share分页列表Vo")
	@RequestMapping(value = "sharevo", method = {RequestMethod.GET})
	public @ResponseBody Result<Page> listvo(ShareVo shareVo, HttpServletRequest request) {
		Page page = PageUtils.createPage(request);
		page = shareService.pageByVo(page, shareVo);
		return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增c_share")
	@RequestMapping(value = "share", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody Share share) {
		if (share != null) {
			shareService.save(share);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载c_share")
    @RequestMapping(value = "share/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<ShareVo> load(@PathVariable Long id) {
		ShareVo shareVo = shareService.load(new Share(id));
        return new Result(Result.SUCCESS,shareVo);
    }

	@ApiOperation(value = "根据ID更新c_share")
	@RequestMapping(value = "share/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody Share share) {
		if (id != null && share != null) {
			share.setId(id);
			shareService.updateIgnoreNull(share);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除c_share")
	@RequestMapping(value = "share/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		shareService.delete(new Share(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setShareService(
			@Qualifier("shareService") IShareService shareService) {
		this.shareService = shareService;
	}


}
