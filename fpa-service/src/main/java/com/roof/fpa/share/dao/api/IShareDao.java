package com.roof.fpa.share.dao.api;

import com.roof.fpa.share.entity.ShareVo;
import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.fpa.share.entity.Share;

public interface IShareDao extends IDaoSupport {
	Page page(Page page, Share share);

	Page pageVo(Page page, ShareVo shareVo);
}