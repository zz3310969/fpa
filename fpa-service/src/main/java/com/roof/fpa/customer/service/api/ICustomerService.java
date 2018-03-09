package com.roof.fpa.customer.service.api;

import java.util.List;
import java.io.Serializable;

import com.roof.fpa.cardtestresult.entity.SimilerResult;
import com.roof.fpa.weixin.service.impl.WeChatDto;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.customer.entity.Customer;
import com.roof.fpa.customer.entity.CustomerVo;

public interface ICustomerService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Customer customer);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Customer customer);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(Customer customer);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Customer customer);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(Customer customer);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(Customer customer);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract CustomerVo load(Customer customer);
	public abstract CustomerVo loadByUnionid(String unionid);

	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract CustomerVo selectForObject(Customer customer);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<CustomerVo> selectForList(Customer customer);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Customer customer);

	public abstract Page friendsPage(Page page, Customer customer);

	/**
	 * 根据openId查询
	 */
	public CustomerVo loadByOpenid(String openId);

	/**
	 * 存在openid就更新，没有就保存
	 * @param customer
	 * @return
	 */
	public WeChatDto saveOrUpdate(CustomerVo customer);


	SimilerResult similer(Long userId, Long friendId);

	}