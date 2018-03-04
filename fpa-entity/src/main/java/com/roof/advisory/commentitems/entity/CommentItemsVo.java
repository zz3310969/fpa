package com.roof.advisory.commentitems.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： z_comment_items <br/>
 *         描述：评价项表 <br/>
 */
public class CommentItemsVo extends CommentItems {

	private List<CommentItemsVo> commentItemsList;

	public CommentItemsVo() {
		super();
	}

	public CommentItemsVo(Long id) {
		super();
		this.id = id;
	}

	public List<CommentItemsVo> getCommentItemsList() {
		return commentItemsList;
	}

	public void setCommentItemsList(List<CommentItemsVo> commentItemsList) {
		this.commentItemsList = commentItemsList;
	}

}
