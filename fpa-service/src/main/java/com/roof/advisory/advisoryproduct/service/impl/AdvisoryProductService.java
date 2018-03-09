package com.roof.advisory.advisoryproduct.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.advisory.advisoryproduct.dao.api.IAdvisoryProductDao;
import com.roof.advisory.advisoryproduct.entity.AdvisoryProduct;
import com.roof.advisory.advisoryproduct.entity.AdvisoryProductVo;
import com.roof.advisory.advisoryproduct.service.api.IAdvisoryProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AdvisoryProductService implements IAdvisoryProductService {
	private IAdvisoryProductDao advisoryProductDao;

	public Serializable save(AdvisoryProduct advisoryProduct){
		advisoryProduct.setState(1);
		return advisoryProductDao.save(advisoryProduct);
	}

	public void delete(AdvisoryProduct advisoryProduct){
		advisoryProductDao.delete(advisoryProduct);
	}
	
	public void deleteByExample(AdvisoryProduct advisoryProduct){
		advisoryProductDao.deleteByExample(advisoryProduct);
	}

	public void update(AdvisoryProduct advisoryProduct){
		advisoryProductDao.update(advisoryProduct);
	}
	
	public void updateIgnoreNull(AdvisoryProduct advisoryProduct){
		advisoryProductDao.updateIgnoreNull(advisoryProduct);
	}
		
	public void updateByExample(AdvisoryProduct advisoryProduct){
		advisoryProductDao.update("updateByExampleAdvisoryProduct", advisoryProduct);
	}

	public AdvisoryProductVo load(AdvisoryProduct advisoryProduct){
		return (AdvisoryProductVo)advisoryProductDao.reload(advisoryProduct);
	}
	
	public AdvisoryProductVo selectForObject(AdvisoryProduct advisoryProduct){
		return (AdvisoryProductVo)advisoryProductDao.selectForObject("selectAdvisoryProduct",advisoryProduct);
	}
	
	public List<AdvisoryProductVo> selectForList(AdvisoryProduct advisoryProduct){
		return (List<AdvisoryProductVo>)advisoryProductDao.selectForList("selectAdvisoryProduct",advisoryProduct);
	}
	
	public Page page(Page page, AdvisoryProduct advisoryProduct) {
		return advisoryProductDao.page(page, advisoryProduct);
	}

	@Autowired
	public void setIAdvisoryProductDao(
			@Qualifier("advisoryProductDao") IAdvisoryProductDao  advisoryProductDao) {
		this.advisoryProductDao = advisoryProductDao;
	}
	

}
