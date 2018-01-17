package com.roof.fpa.customer.service.impl;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.roof.fpa.DefaultUseableEnum;
import com.roof.fpa.weixin.service.api.IWeChatHander;
import com.roof.fpa.weixin.service.impl.WeChatDto;
import org.apache.commons.lang3.StringUtils;
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

	@Autowired
	private IWeChatHander weChatHander;

	public Serializable save(Customer customer){
		Assert.notNull(customer.getWeixinOpenId(),"opid不能为空");
		customer.setUseable(DefaultUseableEnum.usable.getCode());
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

	public Page friendsPage(Page page, Customer customer) {
		return customerDao.friendsPage(page, customer);
	}

	public CustomerVo loadByOpenid(String openId){
		Customer customer = new Customer();
		customer.setWeixinOpenId(openId);
		return (CustomerVo)customerDao.selectForObject("loadCustomerByOpenId",customer);
	}


	public Serializable saveOrUpdate(CustomerVo customerVo){
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerVo,customer);
		try {
			WeChatDto weChatDto = weChatHander.getWeChatDto(customerVo.getCode());
			if(weChatDto != null && StringUtils.isEmpty(weChatDto.getErrcode())){
				customer.setWeixinOpenId(weChatDto.getOpenid());
				customer.setUnionid(weChatDto.getUnionid());
			}else{
				logger.error("获取微信Openid出错:",weChatDto.getErrmsg());
			}
		} catch (IOException e) {
			logger.error("获取微信Openid出错:",e.getCause());
		}
		Assert.notNull(customer.getWeixinOpenId(),"openid不能为空");
		CustomerVo vo = loadByOpenid(customer.getWeixinOpenId());
		customer.setUseable(DefaultUseableEnum.usable.getCode());
		if(vo == null){
			customer.setFollowTime(new Date());
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
