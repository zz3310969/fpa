package com.roof.fpa.counselorrank.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.fpa.counselorrank.entity.CounselorRank;

public interface ICounselorRankDao extends IDaoSupport {
	Page page(Page page, CounselorRank counselorRank);
}