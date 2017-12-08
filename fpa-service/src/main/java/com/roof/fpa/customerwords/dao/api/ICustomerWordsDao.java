package com.roof.fpa.customerwords.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.fpa.customerwords.entity.CustomerWords;

public interface ICustomerWordsDao extends IDaoSupport {
	Page page(Page page, CustomerWords customerWords);
}