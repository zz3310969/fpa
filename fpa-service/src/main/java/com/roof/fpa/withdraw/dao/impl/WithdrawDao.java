package com.roof.fpa.withdraw.dao.impl;

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

import com.roof.fpa.withdraw.dao.api.IWithdrawDao;
import com.roof.fpa.withdraw.entity.Withdraw;
@Service
public class WithdrawDao extends AbstractDao implements IWithdrawDao {
	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, Withdraw withdraw) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectWithdrawPage", "selectWithdrawCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectWithdrawPage", null);
		return pageQuery.select(withdraw);
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