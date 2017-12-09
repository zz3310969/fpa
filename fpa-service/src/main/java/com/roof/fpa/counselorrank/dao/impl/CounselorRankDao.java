package com.roof.fpa.counselorrank.dao.impl;

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

import com.roof.fpa.counselorrank.dao.api.ICounselorRankDao;
import com.roof.fpa.counselorrank.entity.CounselorRank;
@Service
public class CounselorRankDao extends AbstractDao implements ICounselorRankDao {
	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, CounselorRank counselorRank) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCounselorRankPage", "selectCounselorRankCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCounselorRankPage", null);
		return pageQuery.select(counselorRank);
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