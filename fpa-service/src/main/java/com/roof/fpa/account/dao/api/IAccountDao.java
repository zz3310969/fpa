package com.roof.fpa.account.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.fpa.account.entity.Account;

public interface IAccountDao extends IDaoSupport {
	Page page(Page page, Account account);
}