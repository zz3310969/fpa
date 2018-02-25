package com.roof.advisory.commissionpricing.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.advisory.commissionpricing.dao.api.ICommissionPricingDao;
import com.roof.advisory.commissionpricing.entity.CommissionPricing;
import com.roof.advisory.commissionpricing.entity.CommissionPricingVo;
import com.roof.advisory.commissionpricing.service.api.ICommissionPricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CommissionPricingService implements ICommissionPricingService {
	private ICommissionPricingDao commissionPricingDao;

	public Serializable save(CommissionPricing commissionPricing){
		return commissionPricingDao.save(commissionPricing);
	}

	public void delete(CommissionPricing commissionPricing){
		commissionPricingDao.delete(commissionPricing);
	}
	
	public void deleteByExample(CommissionPricing commissionPricing){
		commissionPricingDao.deleteByExample(commissionPricing);
	}

	public void update(CommissionPricing commissionPricing){
		commissionPricingDao.update(commissionPricing);
	}
	
	public void updateIgnoreNull(CommissionPricing commissionPricing){
		commissionPricingDao.updateIgnoreNull(commissionPricing);
	}
		
	public void updateByExample(CommissionPricing commissionPricing){
		commissionPricingDao.update("updateByExampleCommissionPricing", commissionPricing);
	}

	public CommissionPricingVo load(CommissionPricing commissionPricing){
		return (CommissionPricingVo)commissionPricingDao.reload(commissionPricing);
	}
	
	public CommissionPricingVo selectForObject(CommissionPricing commissionPricing){
		return (CommissionPricingVo)commissionPricingDao.selectForObject("selectCommissionPricing",commissionPricing);
	}
	
	public List<CommissionPricingVo> selectForList(CommissionPricing commissionPricing){
		return (List<CommissionPricingVo>)commissionPricingDao.selectForList("selectCommissionPricing",commissionPricing);
	}
	
	public Page page(Page page, CommissionPricing commissionPricing) {
		return commissionPricingDao.page(page, commissionPricing);
	}

	@Autowired
	public void setICommissionPricingDao(
			@Qualifier("commissionPricingDao") ICommissionPricingDao  commissionPricingDao) {
		this.commissionPricingDao = commissionPricingDao;
	}
	

}
