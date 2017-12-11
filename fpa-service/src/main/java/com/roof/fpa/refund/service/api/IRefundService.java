package com.roof.fpa.refund.service.api;

import java.util.List;
import java.io.Serializable;

import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.refund.entity.Refund;
import com.roof.fpa.refund.entity.RefundVo;

public interface IRefundService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Refund refund);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Refund refund);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(Refund refund);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Refund refund);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(Refund refund);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(Refund refund);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract RefundVo load(Refund refund);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract RefundVo selectForObject(Refund refund);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<RefundVo> selectForList(Refund refund);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Refund refund);

}