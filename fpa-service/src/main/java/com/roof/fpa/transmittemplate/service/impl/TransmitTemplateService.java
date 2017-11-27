package com.roof.fpa.transmittemplate.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.transmittemplate.dao.api.ITransmitTemplateDao;
import com.roof.fpa.transmittemplate.entity.TransmitTemplate;
import com.roof.fpa.transmittemplate.entity.TransmitTemplateVo;
import com.roof.fpa.transmittemplate.service.api.ITransmitTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TransmitTemplateService implements ITransmitTemplateService {
	private ITransmitTemplateDao transmitTemplateDao;

	public Serializable save(TransmitTemplate transmitTemplate){
		return transmitTemplateDao.save(transmitTemplate);
	}

	public void delete(TransmitTemplate transmitTemplate){
		transmitTemplateDao.delete(transmitTemplate);
	}
	
	public void deleteByExample(TransmitTemplate transmitTemplate){
		transmitTemplateDao.deleteByExample(transmitTemplate);
	}

	public void update(TransmitTemplate transmitTemplate){
		transmitTemplateDao.update(transmitTemplate);
	}
	
	public void updateIgnoreNull(TransmitTemplate transmitTemplate){
		transmitTemplateDao.updateIgnoreNull(transmitTemplate);
	}
		
	public void updateByExample(TransmitTemplate transmitTemplate){
		transmitTemplateDao.update("updateByExampleTransmitTemplate", transmitTemplate);
	}

	public TransmitTemplateVo load(TransmitTemplate transmitTemplate){
		return (TransmitTemplateVo)transmitTemplateDao.reload(transmitTemplate);
	}
	
	public TransmitTemplateVo selectForObject(TransmitTemplate transmitTemplate){
		return (TransmitTemplateVo)transmitTemplateDao.selectForObject("selectTransmitTemplate",transmitTemplate);
	}
	
	public List<TransmitTemplateVo> selectForList(TransmitTemplate transmitTemplate){
		return (List<TransmitTemplateVo>)transmitTemplateDao.selectForList("selectTransmitTemplate",transmitTemplate);
	}
	
	public Page page(Page page, TransmitTemplate transmitTemplate) {
		return transmitTemplateDao.page(page, transmitTemplate);
	}

	@Autowired
	public void setITransmitTemplateDao(
			@Qualifier("transmitTemplateDao") ITransmitTemplateDao  transmitTemplateDao) {
		this.transmitTemplateDao = transmitTemplateDao;
	}
	

}
