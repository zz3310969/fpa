package com.roof.advisory.advisorymodes.service.impl;

import java.io.Serializable;
import java.util.List;

import com.roof.fpa.DefaultStateEnum;
import org.roof.roof.dataaccess.api.Page;
import com.roof.advisory.advisorymodes.dao.api.IAdvisoryModesDao;
import com.roof.advisory.advisorymodes.entity.AdvisoryModes;
import com.roof.advisory.advisorymodes.entity.AdvisoryModesVo;
import com.roof.advisory.advisorymodes.service.api.IAdvisoryModesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AdvisoryModesService implements IAdvisoryModesService {
	private IAdvisoryModesDao advisoryModesDao;

	public Serializable save(AdvisoryModes advisoryModes){
		advisoryModes.setState(DefaultStateEnum.usable.getCode());
		return advisoryModesDao.save(advisoryModes);
	}

	public void delete(AdvisoryModes advisoryModes){
		advisoryModesDao.delete(advisoryModes);
	}
	
	public void deleteByExample(AdvisoryModes advisoryModes){
		advisoryModesDao.deleteByExample(advisoryModes);
	}

	public void update(AdvisoryModes advisoryModes){
		advisoryModesDao.update(advisoryModes);
	}
	
	public void updateIgnoreNull(AdvisoryModes advisoryModes){
		advisoryModesDao.updateIgnoreNull(advisoryModes);
	}
		
	public void updateByExample(AdvisoryModes advisoryModes){
		advisoryModesDao.update("updateByExampleAdvisoryModes", advisoryModes);
	}

	public AdvisoryModesVo load(AdvisoryModes advisoryModes){
		return (AdvisoryModesVo)advisoryModesDao.reload(advisoryModes);
	}
	
	public AdvisoryModesVo selectForObject(AdvisoryModes advisoryModes){
		return (AdvisoryModesVo)advisoryModesDao.selectForObject("selectAdvisoryModes",advisoryModes);
	}
	
	public List<AdvisoryModesVo> selectForList(AdvisoryModes advisoryModes){
		return (List<AdvisoryModesVo>)advisoryModesDao.selectForList("selectAdvisoryModes",advisoryModes);
	}
	
	public Page page(Page page, AdvisoryModes advisoryModes) {
		return advisoryModesDao.page(page, advisoryModes);
	}

	@Autowired
	public void setIAdvisoryModesDao(
			@Qualifier("advisoryModesDao") IAdvisoryModesDao  advisoryModesDao) {
		this.advisoryModesDao = advisoryModesDao;
	}
	

}
