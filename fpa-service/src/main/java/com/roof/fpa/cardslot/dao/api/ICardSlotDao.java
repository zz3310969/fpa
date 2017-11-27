package com.roof.fpa.cardslot.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.fpa.cardslot.entity.CardSlot;

public interface ICardSlotDao extends IDaoSupport {
	Page page(Page page, CardSlot cardSlot);
}