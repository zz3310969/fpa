package com.roof.fpa.cardtestresultdetail.dao.impl;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.roof.fpa.cardtestresult.entity.CardTestResultDto;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.roof.dataaccess.PageQuery;
import org.roof.roof.dataaccess.api.AbstractDao;
import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.IPageQuery;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.roof.fpa.cardtestresultdetail.dao.api.ICardTestResultDetailDao;
import com.roof.fpa.cardtestresultdetail.entity.CardTestResultDetail;
@Service
public class CardTestResultDetailDao extends AbstractDao implements ICardTestResultDetailDao {

	@Autowired
	protected SqlSessionTemplate sqlSessionTemplate;

	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, CardTestResultDetail cardTestResultDetail) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCardTestResultDetailPage", "selectCardTestResultDetailCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCardTestResultDetailPage", null);
		return pageQuery.select(cardTestResultDetail);
	}

	@Override
	public void batchinsert(List<CardTestResultDetail> cardTestResultDtoList) {
		sqlSessionTemplate.insert("batchsaveCardTestResultDetail", cardTestResultDtoList);
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