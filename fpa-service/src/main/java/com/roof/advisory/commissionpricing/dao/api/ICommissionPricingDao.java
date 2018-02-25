package com.roof.advisory.commissionpricing.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.advisory.commissionpricing.entity.CommissionPricing;

public interface ICommissionPricingDao extends IDaoSupport {
	Page page(Page page, CommissionPricing commissionPricing);
}