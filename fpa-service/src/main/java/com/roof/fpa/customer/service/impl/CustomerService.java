package com.roof.fpa.customer.service.impl;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import com.roof.fpa.weixin.service.api.IWeChatHander;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.customer.dao.api.ICustomerDao;
import com.roof.fpa.customer.entity.Customer;
import com.roof.fpa.customer.entity.CustomerVo;
import com.roof.fpa.customer.service.api.ICustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CustomerService implements ICustomerService {

	private static Logger logger = LoggerFactory.getLogger(CustomerService.class);

	private ICustomerDao customerDao;

	private IWeChatHander weChatHander;

	public Serializable save(Customer customer){
		Assert.notNull(customer.getWeixinOpenId(),"opid不能为空");
		return customerDao.save(customer);
	}

	public void delete(Customer customer){
		customerDao.delete(customer);
	}
	
	public void deleteByExample(Customer customer){
		customerDao.deleteByExample(customer);
	}

	public void update(Customer customer){
		customerDao.update(customer);
	}
	
	public void updateIgnoreNull(Customer customer){
		customerDao.updateIgnoreNull(customer);
	}
		
	public void updateByExample(Customer customer){
		customerDao.update("updateByExampleCustomer", customer);
	}

	public CustomerVo load(Customer customer){
		return (CustomerVo)customerDao.reload(customer);
	}
	
	public CustomerVo selectForObject(Customer customer){
		return (CustomerVo)customerDao.selectForObject("selectCustomer",customer);
	}
	
	public List<CustomerVo> selectForList(Customer customer){
		return (List<CustomerVo>)customerDao.selectForList("selectCustomer",customer);
	}
	
	public Page page(Page page, Customer customer) {
		return customerDao.page(page, customer);
	}

	public CustomerVo loadByOpenid(String openId){
		Customer customer = new Customer();
		customer.setWeixinOpenId(openId);
		return (CustomerVo)customerDao.selectForObject("",customer);
	}


	public Serializable saveOrUpdate(CustomerVo customerVo){
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerVo,customer);
		try {
			customer.setWeixinOpenId(weChatHander.getOpenid(customerVo.getCode()));
		} catch (IOException e) {
			logger.error("获取微信Openid出错:",e.getCause());
		}
		Assert.notNull(customer.getWeixinOpenId(),"openid不能为空");
		CustomerVo vo =null;// loadByOpenid(customer.getWeixinOpenId());
		if(vo == null){
			return customerDao.save(customer);
		}else{
			customer.setId(vo.getId());
			updateIgnoreNull(customer);
			return vo.getId();
		}
	}

	@Autowired
	public void setICustomerDao(
			@Qualifier("customerDao") ICustomerDao  customerDao) {
		this.customerDao = customerDao;
	}
	

}
