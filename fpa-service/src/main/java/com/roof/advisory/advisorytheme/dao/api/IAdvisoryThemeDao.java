package com.roof.advisory.advisorytheme.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.advisory.advisorytheme.entity.AdvisoryTheme;

public interface IAdvisoryThemeDao extends IDaoSupport {
	Page page(Page page, AdvisoryTheme advisoryTheme);
}