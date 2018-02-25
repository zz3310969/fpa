package com.roof.advisory.advisorytheme.service.api;

import java.util.List;
import java.io.Serializable;

import org.roof.roof.dataaccess.api.Page;
import com.roof.advisory.advisorytheme.entity.AdvisoryTheme;
import com.roof.advisory.advisorytheme.entity.AdvisoryThemeVo;

public interface IAdvisoryThemeService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(AdvisoryTheme advisoryTheme);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(AdvisoryTheme advisoryTheme);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(AdvisoryTheme advisoryTheme);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(AdvisoryTheme advisoryTheme);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(AdvisoryTheme advisoryTheme);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(AdvisoryTheme advisoryTheme);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract AdvisoryThemeVo load(AdvisoryTheme advisoryTheme);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract AdvisoryThemeVo selectForObject(AdvisoryTheme advisoryTheme);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<AdvisoryThemeVo> selectForList(AdvisoryTheme advisoryTheme);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, AdvisoryTheme advisoryTheme);

	//public abstract List<AdvisoryThemeVo> groupByAppForList(AdvisoryTheme advisoryTheme);


}