package com.roof.advisory.commenttemplate.controller;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import javax.servlet.http.HttpServletRequest;

import com.roof.advisory.DefaultStatusEnum;
import com.roof.advisory.advisorymodes.entity.AdvisoryModes;
import com.roof.advisory.advisorymodes.entity.AdvisoryModesVo;
import com.roof.advisory.application.entity.Application;
import com.roof.advisory.application.entity.ApplicationVo;
import com.roof.advisory.application.service.api.IApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.advisory.commenttemplate.entity.CommentTemplate;
import com.roof.advisory.commenttemplate.entity.CommentTemplateVo;
import com.roof.advisory.commenttemplate.service.api.ICommentTemplateService;
import org.roof.web.dictionary.entity.Dictionary;
import org.roof.web.dictionary.service.api.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "commenttemplate", description = "评价模版表管理")
@Controller
@RequestMapping("advisory")
public class CommentTemplateController {
    private ICommentTemplateService commentTemplateService;

    @Autowired
    private IApplicationService applicationService;

    @Autowired
    private IDictionaryService dictionaryService;


    @ApiOperation(value = "获得评价模版表基础信息")
    @RequestMapping(value = "commenttemplate/base", method = {RequestMethod.GET})
    public @ResponseBody
    Result<Map<String, Object>> base(HttpServletRequest request) {
        Map<String, Object> map = Maps.newHashMap();
        List<ApplicationVo> apps = applicationService.selectForList(new Application());
        map.put("apps", apps);
        DefaultStatusEnum[] status = DefaultStatusEnum.values();
        map.put("status", status);
        return new Result(Result.SUCCESS, map);
    }

    @ApiOperation(value = "获得评价模版表分页列表")
    @RequestMapping(value = "commenttemplate", method = {RequestMethod.GET})
    public @ResponseBody
    Result<Page> list(CommentTemplate commentTemplate, HttpServletRequest request) {
        Page page = PageUtils.createPage(request);
        page = commentTemplateService.page(page, commentTemplate);
        return new Result(Result.SUCCESS, page);
    }


    @ApiOperation(value = "新增评价模版表")
    @RequestMapping(value = "commenttemplate", method = {RequestMethod.POST})
    public @ResponseBody
    Result create(@RequestBody CommentTemplate commentTemplate) {
        if (commentTemplate != null) {
            commentTemplateService.save(commentTemplate);
            return new Result("保存成功!");
        } else {
            return new Result(Result.FAIL, "数据传输失败!");
        }
    }

    @ApiOperation(value = "根据ID加载评价模版表")
    @RequestMapping(value = "commenttemplate/{id}", method = {RequestMethod.GET})
    public @ResponseBody
    Result<CommentTemplateVo> load(@PathVariable Long id) {
        CommentTemplateVo commentTemplateVo = commentTemplateService.load(new CommentTemplate(id));
        return new Result(Result.SUCCESS, commentTemplateVo);
    }

    @ApiOperation(value = "根据ID更新评价模版表")
    @RequestMapping(value = "commenttemplate/{id}", method = {RequestMethod.PUT})
    public @ResponseBody
    Result update(@PathVariable Long id, @RequestBody CommentTemplate commentTemplate) {
        if (id != null && commentTemplate != null) {
            commentTemplate.setId(id);
            commentTemplateService.updateIgnoreNull(commentTemplate);
            return new Result("保存成功!");
        } else {
            return new Result(Result.FAIL, "数据传输失败!");
        }
    }

    @ApiOperation(value = "根据ID删除评价模版表")
    @RequestMapping(value = "commenttemplate/{id}", method = {RequestMethod.DELETE})
    public @ResponseBody
    Result delete(@PathVariable Long id) {
        CommentTemplate commentTemplate = new CommentTemplate(id);
        commentTemplate.setState(0);
        commentTemplateService.updateIgnoreNull(commentTemplate);
        return new Result("删除成功!");
    }

    @Autowired(required = true)
    public void setCommentTemplateService(
            @Qualifier("commentTemplateService") ICommentTemplateService commentTemplateService
    ) {
        this.commentTemplateService = commentTemplateService;
    }


}
