package com.roof.advisory.advisoryorderrecord.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.advisory.advisoryorderrecord.entity.AdvisoryOrderRecord;

public interface IAdvisoryOrderRecordDao extends IDaoSupport {
	Page page(Page page, AdvisoryOrderRecord advisoryOrderRecord);
}