package com.roof.advisory.applicationuser.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.advisory.applicationuser.entity.ApplicationUser;

public interface IApplicationUserDao extends IDaoSupport {
	Page page(Page page, ApplicationUser applicationUser);
}