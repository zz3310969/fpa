package com.roof.fpa.counselorrank.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.counselorrank.dao.api.ICounselorRankDao;
import com.roof.fpa.counselorrank.entity.CounselorRank;
import com.roof.fpa.counselorrank.entity.CounselorRankVo;
import com.roof.fpa.counselorrank.service.api.ICounselorRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CounselorRankService implements ICounselorRankService {
	private ICounselorRankDao counselorRankDao;

	public Serializable save(CounselorRank counselorRank){
		return counselorRankDao.save(counselorRank);
	}

	public void delete(CounselorRank counselorRank){
		counselorRankDao.delete(counselorRank);
	}
	
	public void deleteByExample(CounselorRank counselorRank){
		counselorRankDao.deleteByExample(counselorRank);
	}

	public void update(CounselorRank counselorRank){
		counselorRankDao.update(counselorRank);
	}
	
	public void updateIgnoreNull(CounselorRank counselorRank){
		counselorRankDao.updateIgnoreNull(counselorRank);
	}
		
	public void updateByExample(CounselorRank counselorRank){
		counselorRankDao.update("updateByExampleCounselorRank", counselorRank);
	}

	public CounselorRankVo load(CounselorRank counselorRank){
		return (CounselorRankVo)counselorRankDao.reload(counselorRank);
	}
	
	public CounselorRankVo selectForObject(CounselorRank counselorRank){
		return (CounselorRankVo)counselorRankDao.selectForObject("selectCounselorRank",counselorRank);
	}
	
	public List<CounselorRankVo> selectForList(CounselorRank counselorRank){
		return (List<CounselorRankVo>)counselorRankDao.selectForList("selectCounselorRank",counselorRank);
	}
	
	public Page page(Page page, CounselorRank counselorRank) {
		return counselorRankDao.page(page, counselorRank);
	}

	@Autowired
	public void setICounselorRankDao(
			@Qualifier("counselorRankDao") ICounselorRankDao  counselorRankDao) {
		this.counselorRankDao = counselorRankDao;
	}
	

}
