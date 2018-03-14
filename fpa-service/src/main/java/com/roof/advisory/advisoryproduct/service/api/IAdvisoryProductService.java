package com.roof.advisory.advisoryproduct.service.api;

import java.util.List;
import java.io.Serializable;

import com.roof.advisory.consultant.entity.Consultant;
import org.roof.roof.dataaccess.api.Page;
import com.roof.advisory.advisoryproduct.entity.AdvisoryProduct;
import com.roof.advisory.advisoryproduct.entity.AdvisoryProductVo;

public interface IAdvisoryProductService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(AdvisoryProduct advisoryProduct);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(AdvisoryProduct advisoryProduct);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(AdvisoryProduct advisoryProduct);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(AdvisoryProduct advisoryProduct);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(AdvisoryProduct advisoryProduct);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(AdvisoryProduct advisoryProduct);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract AdvisoryProductVo load(AdvisoryProduct advisoryProduct);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract AdvisoryProductVo selectForObject(AdvisoryProduct advisoryProduct);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<AdvisoryProductVo> selectForList(AdvisoryProduct advisoryProduct);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, AdvisoryProduct advisoryProduct);

	public Serializable findAndCreate(Long consultantId);

}