package com.roof.advisory.commentitems.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.advisory.commentitems.entity.CommentItems;

public interface ICommentItemsDao extends IDaoSupport {
	Page page(Page page, CommentItems commentItems);
}