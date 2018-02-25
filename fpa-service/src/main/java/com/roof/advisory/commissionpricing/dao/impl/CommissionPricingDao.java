package com.roof.advisory.commissionpricing.dao.impl;

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

import com.roof.advisory.commissionpricing.dao.api.ICommissionPricingDao;
import com.roof.advisory.commissionpricing.entity.CommissionPricing;
@Service
public class CommissionPricingDao extends AbstractDao implements ICommissionPricingDao {
	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, CommissionPricing commissionPricing) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCommissionPricingPage", "selectCommissionPricingCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCommissionPricingPage", null);
		return pageQuery.select(commissionPricing);
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