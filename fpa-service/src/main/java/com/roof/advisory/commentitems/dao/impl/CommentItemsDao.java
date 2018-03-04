package com.roof.advisory.commentitems.dao.impl;

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

import com.roof.advisory.commentitems.dao.api.ICommentItemsDao;
import com.roof.advisory.commentitems.entity.CommentItems;
@Service
public class CommentItemsDao extends AbstractDao implements ICommentItemsDao {
	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, CommentItems commentItems) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCommentItemsPage", "selectCommentItemsCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCommentItemsPage", null);
		return pageQuery.select(commentItems);
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