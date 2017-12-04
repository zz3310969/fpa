package com.roof.fpa.cardtestresultdetail.dao.api;

import com.roof.fpa.cardtestresult.entity.CardTestResultDto;
import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.fpa.cardtestresultdetail.entity.CardTestResultDetail;

import java.util.List;

public interface ICardTestResultDetailDao extends IDaoSupport {
	Page page(Page page, CardTestResultDetail cardTestResultDetail);

	void batchinsert(List<CardTestResultDetail> cardTestResultDtoList);
}