package com.roof.fpa.banner.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.fpa.banner.entity.Banner;

public interface IBannerDao extends IDaoSupport {
	Page page(Page page, Banner banner);
}