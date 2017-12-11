package com.roof.fpa.accountdetail.dao.impl;

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

import com.roof.fpa.accountdetail.dao.api.IAccountDetailDao;
import com.roof.fpa.accountdetail.entity.AccountDetail;
@Service
public class AccountDetailDao extends AbstractDao implements IAccountDetailDao {
	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, AccountDetail accountDetail) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectAccountDetailPage", "selectAccountDetailCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectAccountDetailPage", null);
		return pageQuery.select(accountDetail);
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