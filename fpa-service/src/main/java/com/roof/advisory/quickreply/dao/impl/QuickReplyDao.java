package com.roof.advisory.quickreply.dao.impl;

import java.util.Comparator;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.roof.dataaccess.PageQuery;
import org.roof.roof.dataaccess.api.AbstractDao;
import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.IPageQuery;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.roof.advisory.quickreply.dao.api.IQuickReplyDao;
import com.roof.advisory.quickreply.entity.QuickReply;
@Service
public class QuickReplyDao extends AbstractDao implements IQuickReplyDao {
	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, QuickReply quickReply) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectQuickReplyPage", "selectQuickReplyCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectQuickReplyPage", null);
		return pageQuery.select(quickReply);
	}
	
	@Autowired
	public void setPageQueryFactory(
			@Qualifier("pageQueryFactory") PageQueryFactory<PageQuery> pageQueryFactory) {
		this.pageQueryFactory = pageQueryFactory;
	}
	
	@Autowired
	public void setDaoSupport(
			@Qualifier("roofDaoSupport") IDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}

}