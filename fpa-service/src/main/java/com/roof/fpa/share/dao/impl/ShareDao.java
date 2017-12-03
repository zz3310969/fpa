package com.roof.fpa.share.dao.impl;

import java.util.Comparator;
import java.util.HashMap;

import com.roof.fpa.share.entity.ShareVo;
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

import com.roof.fpa.share.dao.api.IShareDao;
import com.roof.fpa.share.entity.Share;
@Service
public class ShareDao extends AbstractDao implements IShareDao {
	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, Share share) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectSharePage", "selectShareCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectSharePage", null);
		return pageQuery.select(share);
	}

	public Page pageVo(Page page, ShareVo shareVo) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectSharePage", "selectShareCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectSharePage", null);
		return pageQuery.select(shareVo);
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