package com.roof.advisory.advisoryproduct.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.advisory.advisoryproduct.entity.AdvisoryProduct;

public interface IAdvisoryProductDao extends IDaoSupport {
	Page page(Page page, AdvisoryProduct advisoryProduct);
}