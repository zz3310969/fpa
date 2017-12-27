package com.roof.fpa.account.dao.impl;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

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

import com.roof.fpa.account.dao.api.IAccountDao;
import com.roof.fpa.account.entity.Account;
@Service
public class AccountDao extends AbstractDao implements IAccountDao {
	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, Account account) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectAccountPage", "selectAccountCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectAccountPage", null);
		return pageQuery.select(account);
	}

	@Override
	public int updateAccount(Long id, Integer amount, Integer his_amount) {
		Map<String,Object> map = new HashMap<>();
		map.put("id",id);
		map.put("amount",amount);
		map.put("his_amount",his_amount);
		return daoSupport.update("updateAccountAmount",map);
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