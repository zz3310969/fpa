package com.roof.fpa.cardtestresult.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.fpa.cardtestresult.entity.CardTestResult;

public interface ICardTestResultDao extends IDaoSupport {
	Page page(Page page, CardTestResult cardTestResult);
}