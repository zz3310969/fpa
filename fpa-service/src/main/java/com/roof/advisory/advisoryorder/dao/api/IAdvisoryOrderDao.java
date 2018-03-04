package com.roof.advisory.advisoryorder.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.advisory.advisoryorder.entity.AdvisoryOrder;

public interface IAdvisoryOrderDao extends IDaoSupport {
	Page page(Page page, AdvisoryOrder advisoryOrder);
}