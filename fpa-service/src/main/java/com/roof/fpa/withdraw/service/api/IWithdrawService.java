package com.roof.fpa.withdraw.service.api;

import java.util.List;
import java.io.Serializable;

import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.withdraw.entity.Withdraw;
import com.roof.fpa.withdraw.entity.WithdrawVo;

public interface IWithdrawService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Withdraw withdraw);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Withdraw withdraw);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(Withdraw withdraw);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Withdraw withdraw);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(Withdraw withdraw);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(Withdraw withdraw);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract WithdrawVo load(Withdraw withdraw);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract WithdrawVo selectForObject(Withdraw withdraw);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<WithdrawVo> selectForList(Withdraw withdraw);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Withdraw withdraw);

}