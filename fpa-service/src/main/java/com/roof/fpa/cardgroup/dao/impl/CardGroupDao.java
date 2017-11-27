package com.roof.fpa.cardgroup.dao.impl;

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

import com.roof.fpa.cardgroup.dao.api.ICardGroupDao;
import com.roof.fpa.cardgroup.entity.CardGroup;
@Service
public class CardGroupDao extends AbstractDao implements ICardGroupDao {
	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, CardGroup cardGroup) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCardGroupPage", "selectCardGroupCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCardGroupPage", null);
		return pageQuery.select(cardGroup);
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