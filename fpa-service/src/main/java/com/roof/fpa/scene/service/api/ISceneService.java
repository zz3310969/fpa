package com.roof.fpa.scene.service.api;

import java.util.List;
import java.io.Serializable;

import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.scene.entity.Scene;
import com.roof.fpa.scene.entity.SceneVo;

public interface ISceneService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Scene scene);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Scene scene);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(Scene scene);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Scene scene);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(Scene scene);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(Scene scene);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract SceneVo load(Scene scene);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract SceneVo selectForObject(Scene scene);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<SceneVo> selectForList(Scene scene);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Scene scene);

	public SceneVo loadByCache(Long id);


	}