package com.roof.fpa.wechat.share.controller;

import com.google.common.collect.Maps;
import com.roof.fpa.DefaultStateEnum;
import com.roof.fpa.scene.entity.Scene;
import com.roof.fpa.scene.entity.SceneVo;
import com.roof.fpa.scene.service.api.ISceneService;
import com.roof.fpa.share.entity.Share;
import com.roof.fpa.share.entity.ShareVo;
import com.roof.fpa.share.service.api.IShareService;
import com.roof.fpa.transmittemplate.entity.TransmitTemplate;
import com.roof.fpa.transmittemplate.entity.TransmitTemplateVo;
import com.roof.fpa.transmittemplate.service.api.ITransmitTemplateService;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

@Api(value = "share", description = "分享记录管理")
@Controller
@RequestMapping("fpa/wechat")
public class ShareWechatController {
	private IShareService shareService;



    @ApiOperation(value = "新增分享记录")
	@RequestMapping(value = "share", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody Share share) {
		if (share != null) {
			share.setShareTime(new Date());
			Long id = (Long) shareService.save(share);
			return new Result(Result.SUCCESS,id);
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}


	@Autowired(required = true)
	public void setShareService(
			@Qualifier("shareService") IShareService shareService) {
		this.shareService = shareService;
	}


}
