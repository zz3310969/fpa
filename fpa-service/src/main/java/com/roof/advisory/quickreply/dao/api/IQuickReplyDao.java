package com.roof.advisory.quickreply.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.advisory.quickreply.entity.QuickReply;

public interface IQuickReplyDao extends IDaoSupport {
	Page page(Page page, QuickReply quickReply);
}