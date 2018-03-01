package com.roof.advisory.advisorypricing.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.advisory.advisorypricing.entity.AdvisoryPricing;

public interface IAdvisoryPricingDao extends IDaoSupport {
	Page page(Page page, AdvisoryPricing advisoryPricing);
}