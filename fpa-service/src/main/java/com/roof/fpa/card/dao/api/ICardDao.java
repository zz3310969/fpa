package com.roof.fpa.card.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.fpa.card.entity.Card;

public interface ICardDao extends IDaoSupport {
	Page page(Page page, Card card);
}