package com.roof.fpa.withdraw.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.fpa.withdraw.entity.Withdraw;

public interface IWithdrawDao extends IDaoSupport {
	Page page(Page page, Withdraw withdraw);
}