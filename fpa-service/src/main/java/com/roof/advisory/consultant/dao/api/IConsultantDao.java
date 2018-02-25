package com.roof.advisory.consultant.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.advisory.consultant.entity.Consultant;

public interface IConsultantDao extends IDaoSupport {
	Page page(Page page, Consultant consultant);
}