package com.roof.advisory.advisorymodes.dao.impl;

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

import com.roof.advisory.advisorymodes.dao.api.IAdvisoryModesDao;
import com.roof.advisory.advisorymodes.entity.AdvisoryModes;
@Service
public class AdvisoryModesDao extends AbstractDao implements IAdvisoryModesDao {
	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, AdvisoryModes advisoryModes) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectAdvisoryModesPage", "selectAdvisoryModesCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectAdvisoryModesPage", null);
		return pageQuery.select(advisoryModes);
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