package com.roof.fpa.cardtestresultdetail.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.fpa.cardtestresultdetail.entity.CardTestResultDetail;

public interface ICardTestResultDetailDao extends IDaoSupport {
	Page page(Page page, CardTestResultDetail cardTestResultDetail);
}