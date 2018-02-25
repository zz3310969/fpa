package com.roof.advisory.level.dao.impl;

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

import com.roof.advisory.level.dao.api.ILevelDao;
import com.roof.advisory.level.entity.Level;
@Service
public class LevelDao extends AbstractDao implements ILevelDao {
	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, Level level) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectLevelPage", "selectLevelCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectLevelPage", null);
		return pageQuery.select(level);
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