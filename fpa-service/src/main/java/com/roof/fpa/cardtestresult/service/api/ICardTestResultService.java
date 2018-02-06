package com.roof.fpa.cardtestresult.service.api;

import java.util.List;
import java.io.Serializable;

import com.roof.fpa.cardtestresult.entity.GeneralCardTestCustomerResult;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.cardtestresult.entity.CardTestResult;
import com.roof.fpa.cardtestresult.entity.CardTestResultVo;

public interface ICardTestResultService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(CardTestResult cardTestResult);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(CardTestResult cardTestResult);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(CardTestResult cardTestResult);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(CardTestResult cardTestResult);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(CardTestResult cardTestResult);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(CardTestResult cardTestResult);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract CardTestResultVo load(CardTestResult cardTestResult);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract CardTestResultVo selectForObject(CardTestResult cardTestResult);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<CardTestResultVo> selectForList(CardTestResult cardTestResult);

    GeneralCardTestCustomerResult calculate(CardTestResultVo cardTestResultVo);

    /**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, CardTestResult cardTestResult);

	public abstract CardTestResultVo selectForLastByUserId(Long userId,Long sceneId);

}