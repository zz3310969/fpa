package com.roof.fpa.cardslot.dao.impl;

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

import com.roof.fpa.cardslot.dao.api.ICardSlotDao;
import com.roof.fpa.cardslot.entity.CardSlot;
@Service
public class CardSlotDao extends AbstractDao implements ICardSlotDao {
	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, CardSlot cardSlot) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCardSlotPage", "selectCardSlotCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCardSlotPage", null);
		return pageQuery.select(cardSlot);
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