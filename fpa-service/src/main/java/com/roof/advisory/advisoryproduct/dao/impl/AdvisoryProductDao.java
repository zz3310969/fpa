package com.roof.advisory.advisoryproduct.dao.impl;

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

import com.roof.advisory.advisoryproduct.dao.api.IAdvisoryProductDao;
import com.roof.advisory.advisoryproduct.entity.AdvisoryProduct;
@Service
public class AdvisoryProductDao extends AbstractDao implements IAdvisoryProductDao {
	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, AdvisoryProduct advisoryProduct) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectAdvisoryProductPage", "selectAdvisoryProductCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectAdvisoryProductPage", null);
		return pageQuery.select(advisoryProduct);
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