package com.roof.fpa.refund.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.fpa.refund.entity.Refund;

public interface IRefundDao extends IDaoSupport {
	Page page(Page page, Refund refund);
}