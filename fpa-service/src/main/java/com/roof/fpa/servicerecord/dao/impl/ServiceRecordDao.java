package com.roof.fpa.servicerecord.dao.impl;

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

import com.roof.fpa.servicerecord.dao.api.IServiceRecordDao;
import com.roof.fpa.servicerecord.entity.ServiceRecord;
@Service
public class ServiceRecordDao extends AbstractDao implements IServiceRecordDao {
	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, ServiceRecord serviceRecord) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectServiceRecordPage", "selectServiceRecordCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectServiceRecordPage", null);
		return pageQuery.select(serviceRecord);
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