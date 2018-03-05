package com.roof.advisory.commentrecord.controller;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import javax.servlet.http.HttpServletRequest;

import com.roof.advisory.DefaultStatusEnum;
import com.roof.advisory.application.entity.Application;
import com.roof.advisory.application.entity.ApplicationVo;
import com.roof.advisory.application.service.api.IApplicationService;
import com.roof.advisory.commentitems.entity.CommentItems;
import com.roof.advisory.commentitems.entity.CommentItemsVo;
import com.roof.advisory.commentitems.service.api.ICommentItemsService;
import com.roof.advisory.commenttemplate.entity.CommentTemplate;
import com.roof.advisory.commenttemplate.entity.CommentTemplateVo;
import com.roof.advisory.consultant.entity.Consultant;
import com.roof.advisory.consultant.entity.ConsultantVo;
import com.roof.advisory.consultant.service.api.IConsultantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.advisory.commentrecord.entity.CommentRecord;
import com.roof.advisory.commentrecord.entity.CommentRecordVo;
import com.roof.advisory.commentrecord.service.api.ICommentRecordService;
import org.roof.web.dictionary.entity.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "commentrecord", description = "评价记录表管理")
@Controller
@RequestMapping("advisory")
public class CommentRecordController {
    private ICommentRecordService commentRecordService;

    @Autowired
    private ICommentItemsService commentItemsService;

    @Autowired
    private IConsultantService consultantService;

    @Autowired
    private IApplicationService applicationService;

    @ApiOperation(value = "获得评价记录表基础信息")
    @RequestMapping(value = "commentrecord/base", method = {RequestMethod.GET})
    public @ResponseBody
    Result<Map<String, Object>> base(HttpServletRequest request) {
        Map<String, Object> map = Maps.newHashMap();
        //在用模版
        CommentItems commentItems = new CommentItems();
        commentItems.setState(1);
        List<CommentItemsVo> list = commentItemsService.selectForList(commentItems);
        map.put("items", list);
        //apps
        List<ApplicationVo> apps = applicationService.selectForList(new Application());
        map.put("apps", apps);
        //状态
        DefaultStatusEnum[] status = DefaultStatusEnum.values();
        map.put("status", status);
        //咨询师
        Consultant consultant = new Consultant();
        consultant.setState(1);
        List<ConsultantVo> consultants = consultantService.selectForList(consultant);
        map.put("consultants", consultants);
        return new Result(Result.SUCCESS, map);
    }

    @ApiOperation(value = "获得评价记录表分页列表")
    @RequestMapping(value = "commentrecord", method = {RequestMethod.GET})
    public @ResponseBody
    Result<Page> list(CommentRecordVo commentRecordVo, HttpServletRequest request) {
        Page page = PageUtils.createPage(request);
        page = commentRecordService.pageByVo(page, commentRecordVo);
        return new Result(Result.SUCCESS, page);
    }


    @ApiOperation(value = "新增评价记录表")
    @RequestMapping(value = "commentrecord", method = {RequestMethod.POST})
    public @ResponseBody
    Result create(@RequestBody CommentRecord commentRecord) {
        if (commentRecord != null) {
            commentRecordService.save(commentRecord);
            return new Result("保存成功!");
        } else {
            return new Result(Result.FAIL, "数据传输失败!");
        }
    }

    @ApiOperation(value = "根据ID加载评价记录表")
    @RequestMapping(value = "commentrecord/{id}", method = {RequestMethod.GET})
    public @ResponseBody
    Result<CommentRecordVo> load(@PathVariable Long id) {
        CommentRecordVo commentRecordVo = commentRecordService.load(new CommentRecord(id));
        return new Result(Result.SUCCESS, commentRecordVo);
    }

    @ApiOperation(value = "根据ID更新评价记录表")
    @RequestMapping(value = "commentrecord/{id}", method = {RequestMethod.PUT})
    public @ResponseBody
    Result update(@PathVariable Long id, @RequestBody CommentRecord commentRecord) {
        if (id != null && commentRecord != null) {
            commentRecord.setId(id);
            commentRecordService.updateIgnoreNull(commentRecord);
            return new Result("保存成功!");
        } else {
            return new Result(Result.FAIL, "数据传输失败!");
        }
    }

    @ApiOperation(value = "根据ID删除评价记录表")
    @RequestMapping(value = "commentrecord/{id}", method = {RequestMethod.DELETE})
    public @ResponseBody
    Result delete(@PathVariable Long id) {
        CommentRecord commentRecord = new CommentRecord(id);
        commentRecord.setState(0);
        commentRecordService.updateIgnoreNull(commentRecord);
        return new Result("删除成功!");
    }

    @Autowired(required = true)
    public void setCommentRecordService(
            @Qualifier("commentRecordService") ICommentRecordService commentRecordService
    ) {
        this.commentRecordService = commentRecordService;
    }


}
