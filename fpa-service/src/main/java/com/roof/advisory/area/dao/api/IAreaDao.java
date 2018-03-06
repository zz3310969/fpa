package com.roof.advisory.area.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.advisory.area.entity.Area;

public interface IAreaDao extends IDaoSupport {
	Page page(Page page, Area area);
}