package com.roof.advisory.level.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.advisory.level.entity.Level;

public interface ILevelDao extends IDaoSupport {
	Page page(Page page, Level level);
}