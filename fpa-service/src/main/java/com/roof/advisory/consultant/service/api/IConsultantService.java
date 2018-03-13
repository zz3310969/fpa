package com.roof.advisory.consultant.service.api;

import java.util.List;
import java.io.Serializable;

import com.roof.advisory.consultant.entity.ConsultantWechatVo;
import org.roof.roof.dataaccess.api.Page;
import com.roof.advisory.consultant.entity.Consultant;
import com.roof.advisory.consultant.entity.ConsultantVo;

public interface IConsultantService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Consultant consultant);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Consultant consultant);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(Consultant consultant);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Consultant consultant);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(Consultant consultant);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(Consultant consultant);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract ConsultantVo load(Consultant consultant);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract ConsultantVo selectForObject(Consultant consultant);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<ConsultantVo> selectForList(Consultant consultant);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Consultant consultant);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page pageWechat(Page page, ConsultantWechatVo consultantWechatVo);

	public abstract ConsultantWechatVo loadForWechat(ConsultantWechatVo consultant);

}