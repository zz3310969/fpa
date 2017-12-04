package com.roof.fpa.charactercolor.service.api;

import java.util.List;
import java.io.Serializable;

import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.charactercolor.entity.CharacterColor;
import com.roof.fpa.charactercolor.entity.CharacterColorVo;
import org.roof.spring.ApplicationException;

public interface ICharacterColorService {

	public Serializable saveVo (CharacterColorVo characterColorVo) throws ApplicationException;
	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(CharacterColor characterColor);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(CharacterColor characterColor);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(CharacterColor characterColor);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(CharacterColor characterColor);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(CharacterColor characterColor);

	public void updateIgnoreNullVo(CharacterColorVo characterColorVo) throws ApplicationException;
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(CharacterColor characterColor);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract CharacterColorVo load(CharacterColor characterColor);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract CharacterColorVo selectForObject(CharacterColor characterColor);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<CharacterColorVo> selectForList(CharacterColor characterColor);

    CharacterColorVo selectByColorId(Long colorId);

    /**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, CharacterColor characterColor);

	CharacterColorVo selectByColorIdByCache(Long colorId);

}