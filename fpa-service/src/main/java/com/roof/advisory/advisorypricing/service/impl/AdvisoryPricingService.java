package com.roof.advisory.advisorypricing.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.advisory.advisorypricing.dao.api.IAdvisoryPricingDao;
import com.roof.advisory.advisorypricing.entity.AdvisoryPricing;
import com.roof.advisory.advisorypricing.entity.AdvisoryPricingVo;
import com.roof.advisory.advisorypricing.service.api.IAdvisoryPricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AdvisoryPricingService implements IAdvisoryPricingService {
	private IAdvisoryPricingDao advisoryPricingDao;

	public Serializable save(AdvisoryPricing advisoryPricing){
		return advisoryPricingDao.save(advisoryPricing);
	}

	public void delete(AdvisoryPricing advisoryPricing){
		advisoryPricingDao.delete(advisoryPricing);
	}
	
	public void deleteByExample(AdvisoryPricing advisoryPricing){
		advisoryPricingDao.deleteByExample(advisoryPricing);
	}

	public void update(AdvisoryPricing advisoryPricing){
		advisoryPricingDao.update(advisoryPricing);
	}
	
	public void updateIgnoreNull(AdvisoryPricing advisoryPricing){
		advisoryPricingDao.updateIgnoreNull(advisoryPricing);
	}
		
	public void updateByExample(AdvisoryPricing advisoryPricing){
		advisoryPricingDao.update("updateByExampleAdvisoryPricing", advisoryPricing);
	}

	public AdvisoryPricingVo load(AdvisoryPricing advisoryPricing){
		return (AdvisoryPricingVo)advisoryPricingDao.reload(advisoryPricing);
	}
	
	public AdvisoryPricingVo selectForObject(AdvisoryPricing advisoryPricing){
		return (AdvisoryPricingVo)advisoryPricingDao.selectForObject("selectAdvisoryPricing",advisoryPricing);
	}
	
	public List<AdvisoryPricingVo> selectForList(AdvisoryPricing advisoryPricing){
		return (List<AdvisoryPricingVo>)advisoryPricingDao.selectForList("selectAdvisoryPricing",advisoryPricing);
	}
	
	public Page page(Page page, AdvisoryPricing advisoryPricing) {
		return advisoryPricingDao.page(page, advisoryPricing);
	}

	@Autowired
	public void setIAdvisoryPricingDao(
			@Qualifier("advisoryPricingDao") IAdvisoryPricingDao  advisoryPricingDao) {
		this.advisoryPricingDao = advisoryPricingDao;
	}
	

}
