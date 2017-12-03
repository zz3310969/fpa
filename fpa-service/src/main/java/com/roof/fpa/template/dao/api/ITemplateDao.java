package com.roof.fpa.template.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.fpa.template.entity.Template;

public interface ITemplateDao extends IDaoSupport {
	Page page(Page page, Template template);
}