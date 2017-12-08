package com.roof.fpa.servicerecord.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.fpa.servicerecord.entity.ServiceRecord;

public interface IServiceRecordDao extends IDaoSupport {
	Page page(Page page, ServiceRecord serviceRecord);
}