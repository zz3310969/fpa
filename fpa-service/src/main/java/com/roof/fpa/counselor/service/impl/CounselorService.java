package com.roof.fpa.counselor.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.counselor.dao.api.ICounselorDao;
import com.roof.fpa.counselor.entity.Counselor;
import com.roof.fpa.counselor.entity.CounselorVo;
import com.roof.fpa.counselor.service.api.ICounselorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CounselorService implements ICounselorService {
	private ICounselorDao counselorDao;

	public Serializable save(Counselor counselor){
		return counselorDao.save(counselor);
	}

	public void delete(Counselor counselor){
		counselorDao.delete(counselor);
	}
	
	public void deleteByExample(Counselor counselor){
		counselorDao.deleteByExample(counselor);
	}

	public void update(Counselor counselor){
		counselorDao.update(counselor);
	}
	
	public void updateIgnoreNull(Counselor counselor){
		counselorDao.updateIgnoreNull(counselor);
	}
		
	public void updateByExample(Counselor counselor){
		counselorDao.update("updateByExampleCounselor", counselor);
	}

	public CounselorVo load(Counselor counselor){
		return (CounselorVo)counselorDao.reload(counselor);
	}
	
	public CounselorVo selectForObject(Counselor counselor){
		return (CounselorVo)counselorDao.selectForObject("selectCounselor",counselor);
	}
	
	public List<CounselorVo> selectForList(Counselor counselor){
		return (List<CounselorVo>)counselorDao.selectForList("selectCounselor",counselor);
	}
	
	public Page page(Page page, Counselor counselor) {
		return counselorDao.page(page, counselor);
	}

	@Autowired
	public void setICounselorDao(
			@Qualifier("counselorDao") ICounselorDao  counselorDao) {
		this.counselorDao = counselorDao;
	}
	

}
