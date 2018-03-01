package com.roof.advisory.applicationuser.dao.impl;

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

import com.roof.advisory.applicationuser.dao.api.IApplicationUserDao;
import com.roof.advisory.applicationuser.entity.ApplicationUser;
@Service
public class ApplicationUserDao extends AbstractDao implements IApplicationUserDao {
	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, ApplicationUser applicationUser) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectApplicationUserPage", "selectApplicationUserCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectApplicationUserPage", null);
		return pageQuery.select(applicationUser);
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