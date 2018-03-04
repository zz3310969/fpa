package com.roof.advisory.commenttemplate.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.advisory.commenttemplate.entity.CommentTemplate;

public interface ICommentTemplateDao extends IDaoSupport {
	Page page(Page page, CommentTemplate commentTemplate);
}