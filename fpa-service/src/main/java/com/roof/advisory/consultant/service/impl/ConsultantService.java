package com.roof.advisory.consultant.service.impl;

import java.io.Serializable;
import java.util.List;

import com.roof.fpa.DefaultStateEnum;
import org.roof.roof.dataaccess.api.Page;
import com.roof.advisory.consultant.dao.api.IConsultantDao;
import com.roof.advisory.consultant.entity.Consultant;
import com.roof.advisory.consultant.entity.ConsultantVo;
import com.roof.advisory.consultant.service.api.IConsultantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ConsultantService implements IConsultantService {
	private IConsultantDao consultantDao;

	public Serializable save(Consultant consultant){
		consultant.setState(DefaultStateEnum.usable.getCode());
		return consultantDao.save(consultant);
	}

	public void delete(Consultant consultant){
		consultantDao.delete(consultant);
	}
	
	public void deleteByExample(Consultant consultant){
		consultantDao.deleteByExample(consultant);
	}

	public void update(Consultant consultant){
		consultantDao.update(consultant);
	}
	
	public void updateIgnoreNull(Consultant consultant){
		consultantDao.updateIgnoreNull(consultant);
	}
		
	public void updateByExample(Consultant consultant){
		consultantDao.update("updateByExampleConsultant", consultant);
	}

	public ConsultantVo load(Consultant consultant){
		return (ConsultantVo)consultantDao.reload(consultant);
	}
	
	public ConsultantVo selectForObject(Consultant consultant){
		return (ConsultantVo)consultantDao.selectForObject("selectConsultant",consultant);
	}
	
	public List<ConsultantVo> selectForList(Consultant consultant){
		return (List<ConsultantVo>)consultantDao.selectForList("selectConsultant",consultant);
	}
	
	public Page page(Page page, Consultant consultant) {
		return consultantDao.page(page, consultant);
	}

	@Autowired
	public void setIConsultantDao(
			@Qualifier("consultantDao") IConsultantDao  consultantDao) {
		this.consultantDao = consultantDao;
	}
	

}
