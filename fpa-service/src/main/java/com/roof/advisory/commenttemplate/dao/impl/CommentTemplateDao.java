package com.roof.advisory.commenttemplate.dao.impl;

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

import com.roof.advisory.commenttemplate.dao.api.ICommentTemplateDao;
import com.roof.advisory.commenttemplate.entity.CommentTemplate;
@Service
public class CommentTemplateDao extends AbstractDao implements ICommentTemplateDao {
	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, CommentTemplate commentTemplate) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCommentTemplatePage", "selectCommentTemplateCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCommentTemplatePage", null);
		return pageQuery.select(commentTemplate);
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