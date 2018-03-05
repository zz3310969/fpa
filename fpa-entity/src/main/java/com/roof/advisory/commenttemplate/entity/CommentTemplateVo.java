package com.roof.advisory.commenttemplate.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： z_comment_template <br/>
 *         描述：评价模版表 <br/>
 */
public class CommentTemplateVo extends CommentTemplate {

	private String appName;


	private List<CommentTemplateVo> commentTemplateList;

	public CommentTemplateVo() {
		super();
	}

	public CommentTemplateVo(Long id) {
		super();
		this.id = id;
	}

	public List<CommentTemplateVo> getCommentTemplateList() {
		return commentTemplateList;
	}

	public void setCommentTemplateList(List<CommentTemplateVo> commentTemplateList) {
		this.commentTemplateList = commentTemplateList;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
}
