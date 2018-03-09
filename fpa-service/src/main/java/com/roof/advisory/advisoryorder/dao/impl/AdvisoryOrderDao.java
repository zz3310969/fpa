package com.roof.advisory.advisoryorder.dao.impl;

import java.util.Comparator;
import java.util.HashMap;

import com.roof.advisory.advisoryorder.entity.AdvisoryOrderVo;
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

import com.roof.advisory.advisoryorder.dao.api.IAdvisoryOrderDao;
import com.roof.advisory.advisoryorder.entity.AdvisoryOrder;
@Service
public class AdvisoryOrderDao extends AbstractDao implements IAdvisoryOrderDao {
	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, AdvisoryOrder advisoryOrder) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectAdvisoryOrderPage", "selectAdvisoryOrderCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectAdvisoryOrderPage", null);
		return pageQuery.select(advisoryOrder);
	}

	@Override
	public Page pageVo(Page page, AdvisoryOrderVo advisoryOrderVo) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectAdvisoryOrderVoPage", "selectAdvisoryOrderVoCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectAdvisoryOrderPage", null);
		return pageQuery.select(advisoryOrderVo);
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