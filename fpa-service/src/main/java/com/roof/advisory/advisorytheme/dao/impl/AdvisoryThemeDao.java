package com.roof.advisory.advisorytheme.dao.impl;

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

import com.roof.advisory.advisorytheme.dao.api.IAdvisoryThemeDao;
import com.roof.advisory.advisorytheme.entity.AdvisoryTheme;
@Service
public class AdvisoryThemeDao extends AbstractDao implements IAdvisoryThemeDao {
	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, AdvisoryTheme advisoryTheme) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectAdvisoryThemePage", "selectAdvisoryThemeCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectAdvisoryThemePage", null);
		return pageQuery.select(advisoryTheme);
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