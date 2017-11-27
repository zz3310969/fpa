package com.roof.fpa.cardtestresult.dao.impl;

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

import com.roof.fpa.cardtestresult.dao.api.ICardTestResultDao;
import com.roof.fpa.cardtestresult.entity.CardTestResult;
@Service
public class CardTestResultDao extends AbstractDao implements ICardTestResultDao {
	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, CardTestResult cardTestResult) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCardTestResultPage", "selectCardTestResultCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCardTestResultPage", null);
		return pageQuery.select(cardTestResult);
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