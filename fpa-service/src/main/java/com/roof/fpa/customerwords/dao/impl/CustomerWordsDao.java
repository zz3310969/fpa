package com.roof.fpa.customerwords.dao.impl;

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

import com.roof.fpa.customerwords.dao.api.ICustomerWordsDao;
import com.roof.fpa.customerwords.entity.CustomerWords;
@Service
public class CustomerWordsDao extends AbstractDao implements ICustomerWordsDao {
	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, CustomerWords customerWords) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCustomerWordsPage", "selectCustomerWordsCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCustomerWordsPage", null);
		return pageQuery.select(customerWords);
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