package com.roof.advisory.advisorymodes.service.api;

import java.util.List;
import java.io.Serializable;

import org.roof.roof.dataaccess.api.Page;
import com.roof.advisory.advisorymodes.entity.AdvisoryModes;
import com.roof.advisory.advisorymodes.entity.AdvisoryModesVo;

public interface IAdvisoryModesService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(AdvisoryModes advisoryModes);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(AdvisoryModes advisoryModes);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(AdvisoryModes advisoryModes);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(AdvisoryModes advisoryModes);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(AdvisoryModes advisoryModes);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(AdvisoryModes advisoryModes);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract AdvisoryModesVo load(AdvisoryModes advisoryModes);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract AdvisoryModesVo selectForObject(AdvisoryModes advisoryModes);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<AdvisoryModesVo> selectForList(AdvisoryModes advisoryModes);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, AdvisoryModes advisoryModes);

}