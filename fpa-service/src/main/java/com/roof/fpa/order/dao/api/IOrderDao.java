package com.roof.fpa.order.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.fpa.order.entity.Order;

public interface IOrderDao extends IDaoSupport {
	Page page(Page page, Order order);
}