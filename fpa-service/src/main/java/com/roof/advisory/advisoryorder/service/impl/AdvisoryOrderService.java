package com.roof.advisory.advisoryorder.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.advisory.advisoryorder.dao.api.IAdvisoryOrderDao;
import com.roof.advisory.advisoryorder.entity.AdvisoryOrder;
import com.roof.advisory.advisoryorder.entity.AdvisoryOrderVo;
import com.roof.advisory.advisoryorder.service.api.IAdvisoryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AdvisoryOrderService implements IAdvisoryOrderService {
	private IAdvisoryOrderDao advisoryOrderDao;

	public Serializable save(AdvisoryOrder advisoryOrder){
		return advisoryOrderDao.save(advisoryOrder);
	}

	public void delete(AdvisoryOrder advisoryOrder){
		advisoryOrderDao.delete(advisoryOrder);
	}
	
	public void deleteByExample(AdvisoryOrder advisoryOrder){
		advisoryOrderDao.deleteByExample(advisoryOrder);
	}

	public void update(AdvisoryOrder advisoryOrder){
		advisoryOrderDao.update(advisoryOrder);
	}
	
	public void updateIgnoreNull(AdvisoryOrder advisoryOrder){
		advisoryOrderDao.updateIgnoreNull(advisoryOrder);
	}
		
	public void updateByExample(AdvisoryOrder advisoryOrder){
		advisoryOrderDao.update("updateByExampleAdvisoryOrder", advisoryOrder);
	}

	public AdvisoryOrderVo load(AdvisoryOrder advisoryOrder){
		return (AdvisoryOrderVo)advisoryOrderDao.reload(advisoryOrder);
	}
	
	public AdvisoryOrderVo selectForObject(AdvisoryOrder advisoryOrder){
		return (AdvisoryOrderVo)advisoryOrderDao.selectForObject("selectAdvisoryOrder",advisoryOrder);
	}
	
	public List<AdvisoryOrderVo> selectForList(AdvisoryOrder advisoryOrder){
		return (List<AdvisoryOrderVo>)advisoryOrderDao.selectForList("selectAdvisoryOrder",advisoryOrder);
	}
	
	public Page page(Page page, AdvisoryOrder advisoryOrder) {
		return advisoryOrderDao.page(page, advisoryOrder);
	}

	@Autowired
	public void setIAdvisoryOrderDao(
			@Qualifier("advisoryOrderDao") IAdvisoryOrderDao  advisoryOrderDao) {
		this.advisoryOrderDao = advisoryOrderDao;
	}
	

}
