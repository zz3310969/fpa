package com.roof.fpa.order.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.order.dao.api.IOrderDao;
import com.roof.fpa.order.entity.Order;
import com.roof.fpa.order.entity.OrderVo;
import com.roof.fpa.order.service.api.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService {
	private IOrderDao orderDao;

	public Serializable save(Order order){
		return orderDao.save(order);
	}

	public void delete(Order order){
		orderDao.delete(order);
	}
	
	public void deleteByExample(Order order){
		orderDao.deleteByExample(order);
	}

	public void update(Order order){
		orderDao.update(order);
	}
	
	public void updateIgnoreNull(Order order){
		orderDao.updateIgnoreNull(order);
	}
		
	public void updateByExample(Order order){
		orderDao.update("updateByExampleOrder", order);
	}

	public OrderVo load(Order order){
		return (OrderVo)orderDao.reload(order);
	}
	
	public OrderVo selectForObject(Order order){
		return (OrderVo)orderDao.selectForObject("selectOrder",order);
	}
	
	public List<OrderVo> selectForList(Order order){
		return (List<OrderVo>)orderDao.selectForList("selectOrder",order);
	}
	
	public Page page(Page page, Order order) {
		return orderDao.page(page, order);
	}

	@Autowired
	public void setIOrderDao(
			@Qualifier("orderDao") IOrderDao  orderDao) {
		this.orderDao = orderDao;
	}
	

}
