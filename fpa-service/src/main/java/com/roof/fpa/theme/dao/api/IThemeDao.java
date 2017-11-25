package com.roof.fpa.theme.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.fpa.theme.entity.Theme;

public interface IThemeDao extends IDaoSupport {
	Page page(Page page, Theme theme);
}