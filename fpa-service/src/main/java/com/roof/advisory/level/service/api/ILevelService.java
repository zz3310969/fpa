package com.roof.advisory.level.service.api;

import java.util.List;
import java.io.Serializable;

import org.roof.roof.dataaccess.api.Page;
import com.roof.advisory.level.entity.Level;
import com.roof.advisory.level.entity.LevelVo;

public interface ILevelService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Level level);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Level level);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(Level level);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Level level);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(Level level);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(Level level);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract LevelVo load(Level level);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract LevelVo selectForObject(Level level);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<LevelVo> selectForList(Level level);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Level level);

}