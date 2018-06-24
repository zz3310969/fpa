package com.roof.advisory.advisorypayrecord.dao.impl;

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

import com.roof.advisory.advisorypayrecord.dao.api.IAdvisoryPayRecordDao;
import com.roof.advisory.advisorypayrecord.entity.AdvisoryPayRecord;
@Service
public class AdvisoryPayRecordDao extends AbstractDao implements IAdvisoryPayRecordDao {
	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, AdvisoryPayRecord advisoryPayRecord) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectAdvisoryPayRecordPage", "selectAdvisoryPayRecordCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectAdvisoryPayRecordPage", null);
		return pageQuery.select(advisoryPayRecord);
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