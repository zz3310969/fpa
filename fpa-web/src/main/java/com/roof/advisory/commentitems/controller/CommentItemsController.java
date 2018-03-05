package com.roof.advisory.commentitems.controller;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import javax.servlet.http.HttpServletRequest;

import com.roof.advisory.DefaultStatusEnum;
import com.roof.advisory.commenttemplate.entity.CommentTemplate;
import com.roof.advisory.commenttemplate.entity.CommentTemplateVo;
import com.roof.advisory.commenttemplate.service.api.ICommentTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.advisory.commentitems.entity.CommentItems;
import com.roof.advisory.commentitems.entity.CommentItemsVo;
import com.roof.advisory.commentitems.service.api.ICommentItemsService;
import org.roof.web.dictionary.entity.Dictionary;
import org.roof.web.dictionary.service.api.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "commentitems", description = "评价项表管理")
@Controller
@RequestMapping("advisory")
public class CommentItemsController {
    private ICommentItemsService commentItemsService;

    @Autowired
    private IDictionaryService dictionaryService;

    @Autowired
    private ICommentTemplateService commentTemplateService;

    @ApiOperation(value = "获得评价项表基础信息")
    @RequestMapping(value = "commentitems/base", method = {RequestMethod.GET})
    public @ResponseBody
    Result<Map<String, Object>> base(HttpServletRequest request) {
        Map<String, Object> map = Maps.newHashMap();
        //在用模版
        CommentTemplate commentTemplate=  new CommentTemplate();
        commentTemplate.setState(1);
        List<CommentTemplateVo> list = commentTemplateService.selectForList(commentTemplate);
        map.put("templates",list);
        //状态
        DefaultStatusEnum[] status = DefaultStatusEnum.values();
        map.put("status", status);
        //评价方式
        List<Dictionary> eval_modes = dictionaryService.findByType("EVAL_MODE");
        map.put("eval_modes", eval_modes);
        //展现方式
        List<Dictionary> prosetaion_types = dictionaryService.findByType("PROSETAION_TYPE");
        map.put("prosetaion_types", prosetaion_types);
        return new Result(Result.SUCCESS, map);
    }

    @ApiOperation(value = "获得评价项表分页列表")
    @RequestMapping(value = "commentitems", method = {RequestMethod.GET})
    public @ResponseBody
    Result<Page> list(CommentItems commentItems, HttpServletRequest request) {
        Page page = PageUtils.createPage(request);
        page = commentItemsService.page(page, commentItems);
        return new Result(Result.SUCCESS, page);
    }


    @ApiOperation(value = "新增评价项表")
    @RequestMapping(value = "commentitems", method = {RequestMethod.POST})
    public @ResponseBody
    Result create(@RequestBody CommentItems commentItems) {
        if (commentItems != null) {
            commentItemsService.save(commentItems);
            return new Result("保存成功!");
        } else {
            return new Result(Result.FAIL, "数据传输失败!");
        }
    }

    @ApiOperation(value = "根据ID加载评价项表")
    @RequestMapping(value = "commentitems/{id}", method = {RequestMethod.GET})
    public @ResponseBody
    Result<CommentItemsVo> load(@PathVariable Long id) {
        CommentItemsVo commentItemsVo = commentItemsService.load(new CommentItems(id));
        return new Result(Result.SUCCESS, commentItemsVo);
    }

    @ApiOperation(value = "根据ID更新评价项表")
    @RequestMapping(value = "commentitems/{id}", method = {RequestMethod.PUT})
    public @ResponseBody
    Result update(@PathVariable Long id, @RequestBody CommentItems commentItems) {
        if (id != null && commentItems != null) {
            commentItems.setId(id);
            commentItemsService.updateIgnoreNull(commentItems);
            return new Result("保存成功!");
        } else {
            return new Result(Result.FAIL, "数据传输失败!");
        }
    }

    @ApiOperation(value = "根据ID删除评价项表")
    @RequestMapping(value = "commentitems/{id}", method = {RequestMethod.DELETE})
    public @ResponseBody
    Result delete(@PathVariable Long id) {
        CommentItems commentItems = new CommentItems(id);
        commentItems.setState(0);
        commentItemsService.updateIgnoreNull(commentItems);
        return new Result("删除成功!");
    }

    @Autowired(required = true)
    public void setCommentItemsService(
            @Qualifier("commentItemsService") ICommentItemsService commentItemsService
    ) {
        this.commentItemsService = commentItemsService;
    }


}
