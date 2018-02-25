package com.roof.advisory.application.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.advisory.application.entity.Application;

public interface IApplicationDao extends IDaoSupport {
	Page page(Page page, Application application);
}