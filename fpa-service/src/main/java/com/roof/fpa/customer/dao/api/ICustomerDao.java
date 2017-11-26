package com.roof.fpa.customer.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.fpa.customer.entity.Customer;

public interface ICustomerDao extends IDaoSupport {
	Page page(Page page, Customer customer);
}