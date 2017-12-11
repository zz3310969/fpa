package com.roof.fpa.accountdetail.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.fpa.accountdetail.entity.AccountDetail;

public interface IAccountDetailDao extends IDaoSupport {
	Page page(Page page, AccountDetail accountDetail);
}