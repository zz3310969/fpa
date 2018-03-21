package com.roof.advisory.quickreply.controller;

import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;

import com.roof.advisory.DefaultStatusEnum;
import com.roof.advisory.application.entity.Application;
import com.roof.advisory.application.entity.ApplicationVo;
import com.roof.advisory.application.service.api.IApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.advisory.quickreply.entity.QuickReply;
import com.roof.advisory.quickreply.entity.QuickReplyVo;
import com.roof.advisory.quickreply.service.api.IQuickReplyService;
import org.roof.web.dictionary.entity.Dictionary;
import org.roof.web.dictionary.service.api.IDictionaryService;
import org.roof.web.user.entity.User;
import org.roof.web.user.service.api.BaseUserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "quickreply", description = "快捷回复管理")
@Controller
@RequestMapping("advisory")
public class QuickReplyController {
	private IQuickReplyService quickReplyService;
	@Autowired
	private IApplicationService applicationService;
	@Autowired
	private IDictionaryService dictionaryService;

	@ApiOperation(value = "获得快捷回复基础信息")
	@RequestMapping(value = "quickreply/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		List<ApplicationVo> apps = applicationService.selectForList(new Application());
		map.put("apps",apps);
		List<Dictionary> quickreply_types = dictionaryService.findByType("QUICKREPLY_TYPE");
		map.put("quickreply_types",quickreply_types);
		DefaultStatusEnum[] status = DefaultStatusEnum.values();
		map.put("status",status);
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得快捷回复分页列表")
    @RequestMapping(value = "quickreply", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(QuickReply quickReply, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = quickReplyService.page(page, quickReply);
	    return new Result(Result.SUCCESS, page);
	}

	@RequestMapping(value = "quickreply/my", method = {RequestMethod.GET})
	public @ResponseBody Result<List<QuickReplyVo>> my_list(QuickReply quickReply, HttpServletRequest request) {
		Page page = PageUtils.createPage(request);
		quickReply.setUserId(null);
		quickReply.setType("PUBLIC");
		List<QuickReplyVo>  publics  =  quickReplyService.selectForList(quickReply);

		User user = (User) BaseUserContext.getCurrentUser(request);
		//quickReply.setUserId(user.getId());
		quickReply.setType("PRIVATE");
		List<QuickReplyVo> privates =  quickReplyService.selectForList(quickReply);
		publics.addAll(privates);

		return new Result(Result.SUCCESS, publics);
	}


    @ApiOperation(value = "新增快捷回复")
	@RequestMapping(value = "quickreply", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody QuickReply quickReply) {
		if (quickReply != null) {
			quickReplyService.save(quickReply);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载快捷回复")
    @RequestMapping(value = "quickreply/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<QuickReplyVo> load(@PathVariable Long id) {
		QuickReplyVo quickReplyVo = quickReplyService.load(new QuickReply(id));
        return new Result(Result.SUCCESS,quickReplyVo);
    }

	@ApiOperation(value = "根据ID更新快捷回复")
	@RequestMapping(value = "quickreply/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody QuickReply quickReply) {
		if (id != null && quickReply != null) {
			quickReply.setId(id);
			quickReplyService.updateIgnoreNull(quickReply);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除快捷回复")
	@RequestMapping(value = "quickreply/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		quickReplyService.delete(new QuickReply(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setQuickReplyService(
			@Qualifier("quickReplyService") IQuickReplyService quickReplyService) {
		this.quickReplyService = quickReplyService;
	}


}
