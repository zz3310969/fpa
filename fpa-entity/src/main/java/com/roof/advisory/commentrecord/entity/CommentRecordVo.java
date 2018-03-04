package com.roof.advisory.commentrecord.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： z_comment_record <br/>
 *         描述：评价记录表 <br/>
 */
public class CommentRecordVo extends CommentRecord {

	private List<CommentRecordVo> commentRecordList;

	public CommentRecordVo() {
		super();
	}

	public CommentRecordVo(Long id) {
		super();
		this.id = id;
	}

	public List<CommentRecordVo> getCommentRecordList() {
		return commentRecordList;
	}

	public void setCommentRecordList(List<CommentRecordVo> commentRecordList) {
		this.commentRecordList = commentRecordList;
	}

}
