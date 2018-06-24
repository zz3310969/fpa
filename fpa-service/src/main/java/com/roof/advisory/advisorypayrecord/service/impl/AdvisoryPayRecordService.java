package com.roof.advisory.advisorypayrecord.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.advisory.advisorypayrecord.dao.api.IAdvisoryPayRecordDao;
import com.roof.advisory.advisorypayrecord.entity.AdvisoryPayRecord;
import com.roof.advisory.advisorypayrecord.entity.AdvisoryPayRecordVo;
import com.roof.advisory.advisorypayrecord.service.api.IAdvisoryPayRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AdvisoryPayRecordService implements IAdvisoryPayRecordService {
	private IAdvisoryPayRecordDao advisoryPayRecordDao;

	public Serializable save(AdvisoryPayRecord advisoryPayRecord){
		return advisoryPayRecordDao.save(advisoryPayRecord);
	}

	public void delete(AdvisoryPayRecord advisoryPayRecord){
		advisoryPayRecordDao.delete(advisoryPayRecord);
	}
	
	public void deleteByExample(AdvisoryPayRecord advisoryPayRecord){
		advisoryPayRecordDao.deleteByExample(advisoryPayRecord);
	}

	public void update(AdvisoryPayRecord advisoryPayRecord){
		advisoryPayRecordDao.update(advisoryPayRecord);
	}
	
	public void updateIgnoreNull(AdvisoryPayRecord advisoryPayRecord){
		advisoryPayRecordDao.updateIgnoreNull(advisoryPayRecord);
	}
		
	public void updateByExample(AdvisoryPayRecord advisoryPayRecord){
		advisoryPayRecordDao.update("updateByExampleAdvisoryPayRecord", advisoryPayRecord);
	}

	public AdvisoryPayRecordVo load(AdvisoryPayRecord advisoryPayRecord){
		return (AdvisoryPayRecordVo)advisoryPayRecordDao.reload(advisoryPayRecord);
	}
	
	public AdvisoryPayRecordVo selectForObject(AdvisoryPayRecord advisoryPayRecord){
		return (AdvisoryPayRecordVo)advisoryPayRecordDao.selectForObject("selectAdvisoryPayRecord",advisoryPayRecord);
	}
	
	public List<AdvisoryPayRecordVo> selectForList(AdvisoryPayRecord advisoryPayRecord){
		return (List<AdvisoryPayRecordVo>)advisoryPayRecordDao.selectForList("selectAdvisoryPayRecord",advisoryPayRecord);
	}
	
	public Page page(Page page, AdvisoryPayRecord advisoryPayRecord) {
		return advisoryPayRecordDao.page(page, advisoryPayRecord);
	}

	@Autowired
	public void setIAdvisoryPayRecordDao(
			@Qualifier("advisoryPayRecordDao") IAdvisoryPayRecordDao  advisoryPayRecordDao) {
		this.advisoryPayRecordDao = advisoryPayRecordDao;
	}
	

}
