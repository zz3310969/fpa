package com.roof.fpa.cardunit.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.fpa.cardunit.entity.CardUnit;

public interface ICardUnitDao extends IDaoSupport {
	Page page(Page page, CardUnit cardUnit);
}