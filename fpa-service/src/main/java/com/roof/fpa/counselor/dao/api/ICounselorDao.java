package com.roof.fpa.counselor.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.fpa.counselor.entity.Counselor;

public interface ICounselorDao extends IDaoSupport {
	Page page(Page page, Counselor counselor);
}