package com.roof.fpa.charactercolor.dao.impl;

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

import com.roof.fpa.charactercolor.dao.api.ICharacterColorDao;
import com.roof.fpa.charactercolor.entity.CharacterColor;
@Service
public class CharacterColorDao extends AbstractDao implements ICharacterColorDao {
	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, CharacterColor characterColor) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCharacterColorPage", "selectCharacterColorCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCharacterColorPage", null);
		return pageQuery.select(characterColor);
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