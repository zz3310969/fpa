package com.roof.advisory.wechat.consultant.controller;

import com.roof.advisory.commentrecord.entity.CommentRecord;
import com.roof.advisory.commentrecord.service.api.ICommentRecordService;
import com.roof.advisory.consultant.entity.Consultant;
import com.roof.advisory.consultant.entity.ConsultantVo;
import com.roof.advisory.consultant.entity.ConsultantWechatVo;
import com.roof.advisory.consultant.service.api.IConsultantService;
import com.roof.advisory.cos.service.api.ICosService;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/fpa/wechat")
public class ConsultantController {
    @Autowired
    private ICosService cosService;

    private Logger logger = LoggerFactory.getLogger(ConsultantController.class);

    @Autowired
    private IConsultantService consultantService;

    @Autowired
    private ICommentRecordService commentRecordService;

    @ApiOperation(value = "获得咨询师列表")
    @RequestMapping(value = "consultant", method = {RequestMethod.GET})
    public @ResponseBody
    Result<Page> list(ConsultantWechatVo consultantWechatVo, HttpServletResponse response, HttpServletRequest request) {
        Page page = PageUtils.createPage(request);
        page = consultantService.pageWechat(page, consultantWechatVo);
        return new Result(Result.SUCCESS, page);
    }

    @ApiOperation(value = "根据ID加载咨询师")
    @RequestMapping(value = "consultant/{id}", method = {RequestMethod.GET})
    public @ResponseBody
    Result<ConsultantWechatVo> load(@PathVariable Long id) {
        ConsultantWechatVo consultantWechatVo = consultantService.loadForWechat(new ConsultantWechatVo(id));
        return new Result(Result.SUCCESS, consultantWechatVo);
    }

    @ApiOperation(value = "根据咨询师id获取用户评价列表")
    @RequestMapping(value = "consultant/commentRecord/{id}", method = {RequestMethod.GET})
    public @ResponseBody
    Result<Page> commentRecordlist(@PathVariable Long id, HttpServletResponse response, HttpServletRequest request) {
        Page page = PageUtils.createPage(request);
        CommentRecord commentRecord = new CommentRecord();
        commentRecord.setConsultantId(id);
        page = commentRecordService.page(page, commentRecord);
        return new Result(Result.SUCCESS, page);
    }


}
