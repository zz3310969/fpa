package com.roof.fpa.order.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.roof.commons.RoofDateUtils;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.order.dao.api.IOrderDao;
import com.roof.fpa.order.entity.Order;
import com.roof.fpa.order.entity.OrderVo;
import com.roof.fpa.order.service.api.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService {
	private IOrderDao orderDao;

	@Autowired
	private RedisTemplate redisTemplate;
	private final String rediskey = "order_number:";

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

	public String createOrderNumber() {
		String redis = rediskey + RoofDateUtils.dateToString(new Date(), "yyyyMMdd");
		BoundValueOperations<String, Long> operations = redisTemplate.boundValueOps(redis);// .increment(1);
		Long l = operations.increment(1);
		operations.expire(3, TimeUnit.DAYS);
		String s = "00000" + l;
		s = s.substring(s.length() - 6, s.length());
		String str = "ORD"+RoofDateUtils.dateToString(new Date(), "yyyyMMdd") + s;
		return str;
	}

	@Autowired
	public void setIOrderDao(
			@Qualifier("orderDao") IOrderDao  orderDao) {
		this.orderDao = orderDao;
	}
	

}
