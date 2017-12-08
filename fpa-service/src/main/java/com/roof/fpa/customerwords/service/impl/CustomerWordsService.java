package com.roof.fpa.customerwords.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.customerwords.dao.api.ICustomerWordsDao;
import com.roof.fpa.customerwords.entity.CustomerWords;
import com.roof.fpa.customerwords.entity.CustomerWordsVo;
import com.roof.fpa.customerwords.service.api.ICustomerWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CustomerWordsService implements ICustomerWordsService {
	private ICustomerWordsDao customerWordsDao;

	public Serializable save(CustomerWords customerWords){
		return customerWordsDao.save(customerWords);
	}

	public void delete(CustomerWords customerWords){
		customerWordsDao.delete(customerWords);
	}
	
	public void deleteByExample(CustomerWords customerWords){
		customerWordsDao.deleteByExample(customerWords);
	}

	public void update(CustomerWords customerWords){
		customerWordsDao.update(customerWords);
	}
	
	public void updateIgnoreNull(CustomerWords customerWords){
		customerWordsDao.updateIgnoreNull(customerWords);
	}
		
	public void updateByExample(CustomerWords customerWords){
		customerWordsDao.update("updateByExampleCustomerWords", customerWords);
	}

	public CustomerWordsVo load(CustomerWords customerWords){
		return (CustomerWordsVo)customerWordsDao.reload(customerWords);
	}
	
	public CustomerWordsVo selectForObject(CustomerWords customerWords){
		return (CustomerWordsVo)customerWordsDao.selectForObject("selectCustomerWords",customerWords);
	}
	
	public List<CustomerWordsVo> selectForList(CustomerWords customerWords){
		return (List<CustomerWordsVo>)customerWordsDao.selectForList("selectCustomerWords",customerWords);
	}
	
	public Page page(Page page, CustomerWords customerWords) {
		return customerWordsDao.page(page, customerWords);
	}

	@Autowired
	public void setICustomerWordsDao(
			@Qualifier("customerWordsDao") ICustomerWordsDao  customerWordsDao) {
		this.customerWordsDao = customerWordsDao;
	}
	

}
