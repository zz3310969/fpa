package com.roof.fpa.cardgroup.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.fpa.cardgroup.entity.CardGroup;

public interface ICardGroupDao extends IDaoSupport {
	Page page(Page page, CardGroup cardGroup);
}