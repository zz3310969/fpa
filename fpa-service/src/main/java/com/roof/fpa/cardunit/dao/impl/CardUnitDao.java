package com.roof.fpa.cardunit.dao.impl;

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

import com.roof.fpa.cardunit.dao.api.ICardUnitDao;
import com.roof.fpa.cardunit.entity.CardUnit;
@Service
public class CardUnitDao extends AbstractDao implements ICardUnitDao {
	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, CardUnit cardUnit) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCardUnitPage", "selectCardUnitCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCardUnitPage", null);
		return pageQuery.select(cardUnit);
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