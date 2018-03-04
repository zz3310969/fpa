package com.roof.advisory.commentrecord.controller;

import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.advisory.commentrecord.entity.CommentRecord;
import com.roof.advisory.commentrecord.entity.CommentRecordVo;
import com.roof.advisory.commentrecord.service.api.ICommentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "commentrecord", description = "评价记录表管理")
@Controller
@RequestMapping("advisory")
public class CommentRecordController {
	private ICommentRecordService commentRecordService;

	@ApiOperation(value = "获得评价记录表基础信息")
	@RequestMapping(value = "commentrecord/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得评价记录表分页列表")
    @RequestMapping(value = "commentrecord", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(CommentRecord commentRecord, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = commentRecordService.page(page, commentRecord);
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增评价记录表")
	@RequestMapping(value = "commentrecord", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody CommentRecord commentRecord) {
		if (commentRecord != null) {
			commentRecordService.save(commentRecord);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载评价记录表")
    @RequestMapping(value = "commentrecord/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<CommentRecordVo> load(@PathVariable Long id) {
		CommentRecordVo commentRecordVo = commentRecordService.load(new CommentRecord(id));
        return new Result(Result.SUCCESS,commentRecordVo);
    }

	@ApiOperation(value = "根据ID更新评价记录表")
	@RequestMapping(value = "commentrecord/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody CommentRecord commentRecord) {
		if (id != null && commentRecord != null) {
			commentRecord.setId(id);
			commentRecordService.updateIgnoreNull(commentRecord);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除评价记录表")
	@RequestMapping(value = "commentrecord/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		commentRecordService.delete(new CommentRecord(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setCommentRecordService(
			@Qualifier("commentRecordService") ICommentRecordService commentRecordService) {
		this.commentRecordService = commentRecordService;
	}


}
