package com.roof.fpa.transmittemplate.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.fpa.transmittemplate.entity.TransmitTemplate;

public interface ITransmitTemplateDao extends IDaoSupport {
	Page page(Page page, TransmitTemplate transmitTemplate);
}